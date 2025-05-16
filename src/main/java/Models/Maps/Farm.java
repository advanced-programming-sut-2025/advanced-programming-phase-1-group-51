package Models.Maps;

import Models.Buildings.Building;
import Models.ObjectsShownOnMap.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Farm {

    private ArrayList<Building> buildings;
    private ArrayList<Cells> cells;
    private int farmNumber;

    public Farm(ArrayList<Building> buildings, ArrayList<Cells> cells) {
        this.buildings = buildings;
        this.cells = cells;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Cells> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cells> cells) {
        this.cells = cells;
    }



    public Cells findCellFarm(int x, int y) {
        for (Cells cell : cells) {
            if (cell.getPosition().getX() == x && cell.getPosition().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public int getFarmNumber() {
        return farmNumber;
    }

    public void setFarmNumber(int farmNumber) {
        this.farmNumber = farmNumber;
    }

    public void handleNightlyCrowAttacks() {
        List<Cells> vulnerableCrops = getVulnerableCrops();
        int cropCount = vulnerableCrops.size();

        // Calculate number of potential attacks (1 per 16 crops)
        int potentialAttacks = cropCount / 16;

        if (potentialAttacks > 0) {
            Random random = new Random();

            for (int i = 0; i < potentialAttacks; i++) {
                // 25% chance of attack per potential attack
                if (random.nextDouble() <= 0.25) {
                    performCrowAttack(vulnerableCrops);
                }
            }
        }
    }

    private List<Cells> getVulnerableCrops() {
        List<Cells> vulnerableCrops = new ArrayList<>();

        for (Cells cell : this.cells) {
            ObjectOnMap object = (ObjectOnMap) cell.getObjectOnCell();

            if (object instanceof Crop || object instanceof Tree) {
                // Skip greenhouse crops
                if (isInGreenhouse(cell.getPosition())) continue;

                // Check if crop is protected by scarecrow
                if (!isProtectedByScarecrow(cell.getPosition())) {
                    vulnerableCrops.add(cell);
                }
            }
        }
        return vulnerableCrops;
    }

    private boolean isInGreenhouse(Position position) {
        // Implement greenhouse check based on your game's greenhouse location
        // Example: check if position is within greenhouse bounds
        return false;
    }

    private boolean isProtectedByScarecrow(Position position) {
        for (Cells cell : this.cells) {
            if (cell.getObjectOnCell() instanceof Scarecrow) {
                if (calculateDistance(position, cell.getPosition()) <= 8) {
                    return true;
                }
            }
        }
        return false;
    }

    private double calculateDistance(Position pos1, Position pos2) {
        return Math.sqrt(Math.pow(pos1.getX() - pos2.getX(), 2) +
                Math.pow(pos1.getY() - pos2.getY(), 2));
    }

    private void performCrowAttack(List<Cells> vulnerableCrops) {
        if (vulnerableCrops.isEmpty()) return;

        Random random = new Random();
        Cells attackedCell = vulnerableCrops.get(random.nextInt(vulnerableCrops.size()));
        ObjectOnMap attackedObject = (ObjectOnMap) attackedCell.getObjectOnCell();

        if (attackedObject instanceof Crop) {

            attackedCell.setObjectOnCell(null);
            BurntCell burntCell = new BurntCell();
            attackedCell.setObjectOnCell(burntCell);
        }
        else if (attackedObject instanceof Tree) {
            // Prevent tree from bearing fruit next day
            ((Tree) attackedObject).setWillBearFruitToday(false);
        }
    }
}
