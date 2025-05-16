package Controllers.Others;
import Controllers.BaseController;
import Models.*;
import Models.Buildings.House;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.Maps.Position;
import Models.ObjectsShownOnMap.Lake;
import Models.ObjectsShownOnMap.ObjectOnMap;

import java.util.*;

public class MovementAndMapController  extends BaseController {

    public void printMap(int x, int y , int size) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        game.getMap().printMapArea(x, y, size);
    }


    public void helpReadingMap() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        game.getMap().helpReadingMap();
    }


    public Result Walking(int x, int y) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        // Check if player is fainted
        if (player.isPlayerFainted()) {
            return Result.failure("You have fainted and cannot move today.");
        }

        Position currentPos = player.getPosition();
        Position destination = new Position(x, y);

        // Check if destination is valid
        if (!isValidDestination(game, player, destination)) {
            return Result.failure("Invalid destination!");
        }

        // Find path and calculate energy cost
        PathResult pathResult = findPath(game, currentPos, destination);
        if (!pathResult.isPathFound()) {
            return Result.failure("No valid path to destination!");
        }

        double energyCost = calculateEnergyCost(pathResult.getPath());

        // Return path info and wait for confirmation
        return Result.success(
                String.format("Path found! Energy required: %.1f. Confirm movement? (yes/no)", energyCost),
                new MovementConfirmation(pathResult.getPath(), energyCost)
        );
    }

    public Result confirmMovement(boolean confirm, MovementConfirmation confirmation) {
        if (!confirm) {
            return Result.success("Movement canceled.");
        }

        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        // Check energy
        if (player.getEnergy() < confirmation.getEnergyCost()) {
            // Player faints
            player.setEnergy(0);
            player.setPlayerFainted(true);
            return Result.success(
                    "You ran out of energy and fainted! Your turn will be skipped for the rest of the day."
            );
        }

        // Deduct energy and move
        player.setEnergy(player.getEnergy() - (int) Math.ceil(confirmation.getEnergyCost()));
        player.setPosition(confirmation.getPath().get(confirmation.getPath().size() - 1));

        // Update player location flags
        updatePlayerLocationStatus(game, player);

        return Result.success("Moved successfully!");
    }

    private boolean isValidDestination(Game game, Player player, Position destination) {
        // Check if destination is within map bounds
        if (!game.getMap().isWithinBounds(destination)) {
            return false;
        }

        // Check if destination is walkable
        Cells destinationCell = game.getMap().findCell(destination.getX(), destination.getY());
        if (destinationCell == null ||
                (destinationCell.getObjectOnCell() != null &&
                        destinationCell.getObjectOnCell() instanceof ObjectOnMap &&
                        !((ObjectOnMap)destinationCell.getObjectOnCell()).isWalkable)) {
            return false;
        }

        // Check if trying to enter another player's farm
        if (isOtherPlayersFarm(game, player, destination)) {
            return false;
        }

        return true;
    }

    private boolean isOtherPlayersFarm(Game game, Player player, Position pos) {
        for (Farm farm : game.getMap().getFarms()) {
            // Skip player's own farm
            if (farm.equals(player.getFarm())) continue;

            // Check if position is in this farm
            if (farm.findCellFarm(pos.getX(), pos.getY()) != null) {
                return true;
            }
        }
        return false;
    }

    private PathResult findPath(Game game, Position start, Position end) {
        // A* pathfinding algorithm implementation
        PriorityQueue<PathNode> openSet = new PriorityQueue<>();
        Map<Position, Position> cameFrom = new HashMap<>();
        Map<Position, Double> gScore = new HashMap<>();
        Map<Position, Double> fScore = new HashMap<>();

        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, end));
        openSet.add(new PathNode(start, 0.0));

        while (!openSet.isEmpty()) {
            Position current = openSet.poll().getPosition();

            if (current.equals(end)) {
                return new PathResult(true, reconstructPath(cameFrom, current));
            }

            for (Position neighbor : getNeighbors(game, current)) {
                double tentativeGScore = gScore.get(current) + 1; // Each step costs 1

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, end));

                    // Check if neighbor is already in openSet
                    if (!openSet.contains(new PathNode(neighbor, 0.0))) {
                        openSet.add(new PathNode(neighbor, fScore.get(neighbor)));
                    }
                }
            }
        }

        return new PathResult(false, null);
    }

    private List<Position> getNeighbors(Game game, Position pos) {
        List<Position> neighbors = new ArrayList<>();
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1}; // 8-directional movement
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

        for (int i = 0; i < dx.length; i++) {
            Position neighbor = new Position(pos.getX() + dx[i], pos.getY() + dy[i]);

            // Check if neighbor is walkable and valid
            Cells cell = game.getMap().findCell(neighbor.getX(), neighbor.getY());
            if (cell != null &&
                    (cell.getObjectOnCell() == null ||
                            (cell.getObjectOnCell() instanceof ObjectOnMap &&
                                    ((ObjectOnMap)cell.getObjectOnCell()).isWalkable))) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }
    private double heuristic(Position a, Position b) {
        // Euclidean distance heuristic
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    private List<Position> reconstructPath(Map<Position, Position> cameFrom, Position current) {
        List<Position> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }

    private double calculateEnergyCost(List<Position> path) {
        // Energy needed = distance in tiles / 20
        return (path.size() - 1) / 20.0;
    }

    private void updatePlayerLocationStatus(Game game, Player player) {
        Position pos = player.getPosition();

        // Reset all location flags
        player.setInHouse(false);
        player.setInFarm(false);
        player.setInVillage(false);
        player.setCloseToLake(false);

        // Check current location
        Cells cell = game.getMap().findCell(pos.getX(), pos.getY());

        if (cell.getObjectOnCell() instanceof House) {
            player.setInHouse(true);
        }
        else if (player.getFarm().findCellFarm(pos.getX(), pos.getY()) != null) {
            player.setInFarm(true);
        }
        else if (game.getMap().getVillage().findCellVillage(pos.getX(), pos.getY()) != null) {
            player.setInVillage(true);
        }

        // Check if near lake (for water bucket)
        if (isAdjacentToLake(game, pos)) {
            player.setCloseToLake(true);
        }
    }

    private boolean isAdjacentToLake(Game game, Position pos) {
        for (Position neighbor : getNeighbors(game, pos)) {
            Cells cell = game.getMap().findCell(neighbor.getX(), neighbor.getY());
            if (cell.getObjectOnCell() instanceof Lake) {
                return true;
            }
        }
        return false;
    }

    // Helper classes
    private static class PathNode implements Comparable<PathNode> {
        private final Position position;
        private final double fScore;

        public PathNode(Position position, double fScore) {
            this.position = position;
            this.fScore = fScore;
        }

        public Position getPosition() {
            return position;
        }

        @Override
        public int compareTo(PathNode other) {
            return Double.compare(this.fScore, other.fScore);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PathNode pathNode = (PathNode) obj;
            return position.equals(pathNode.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }
    }

    private static class PathResult {
        private final boolean pathFound;
        private final List<Position> path;

        public PathResult(boolean pathFound, List<Position> path) {
            this.pathFound = pathFound;
            this.path = path;
        }

        public boolean isPathFound() {
            return pathFound;
        }

        public List<Position> getPath() {
            return path;
        }
    }

    public static class MovementConfirmation {
        private final List<Position> path;
        private final double energyCost;

        public MovementConfirmation(List<Position> path, double energyCost) {
            this.path = path;
            this.energyCost = energyCost;
        }

        public List<Position> getPath() {
            return path;
        }

        public double getEnergyCost() {
            return energyCost;
        }
        }

}
