package Controllers.Activity;

import Models.*;
import Models.Enums.Others.Season;
import Models.Enums.Types.ObjectsOnMapType.CropType;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.*;

public class Farming {

    public Result PlantSeed(String seed, String direction) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        CropType cropSeedsType = CropType.findCropBySeed(seed);
        if (cropSeedsType == null) {
            return new Result(false, "crop not found");
        }
        if (!(direction.equals("up") || direction.equals("down") || direction.equals("left") || direction.equals("right") ||
                direction.equals("up left") || direction.equals("up right") || direction.equals("down left") || direction.equals("down right") )) {
            return new Result(false, "wrong direction.");
        }

        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = Direction.directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCellFarm(targetCellX, targetCellY);

        if(targetCell == null) {
            return new Result(false, "Cell not found");
        }

        if (!(targetCell.getObjectOnCell() instanceof BurntCell)) {
            return new Result(false, "You can't plant on any cell other than empty ones");
        }

        if(!targetCell.isHasBeenPlowed()){
            return new Result(false,"You have to first plow this cell");
        }

        Loot loot = player.getInventory().findItemLoot(seed);
        if (loot == null) {
            return new Result(false, "You don't have this seed in your inventory");
        }
        loot.setCount(loot.getCount() - 1);

        if (loot.getCount() <= 0) {
            player.getInventory().getLoots().remove(loot);
        }
        boolean check = false;
        for (Season season : cropSeedsType.season) {
            if (season == game.getSeason()) {
                check = true;
                break;
            }
        }
        if (!check) {
            return new Result(false, "This crop can not be planted in this season");
        }

        Crop crop = new Crop(cropSeedsType);
        targetCell.setObjectOnCell(crop);


        return new Result(true,seed + " planted successfully in direction " + direction);
    }

    public Result showPlants(int x, int y) {
        Game game = App.getCurrentUser().getCurrentGame();
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        Cells targetCell = farm.findCellFarm(x,y);

        if (targetCell == null) {
            return new Result(false, "Cell not found");
        }

        ObjectOnMap cellObject = (ObjectOnMap) targetCell.getObjectOnCell();

        if (cellObject == null) {
            return new Result(false, "Cell is empty");
        }

        if (!cellObject.getType().equals("plant")) {
            return new Result(false, "Cell is not a plant");
        }

        Crop plant = (Crop) cellObject;
        return new Result(true, plant.plantInformation());
    }

    public Result fertilization(String fertilizer, String direction) {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (!(direction.equals("up") || direction.equals("down") || direction.equals("left") || direction.equals("right") ||
                direction.equals("up left") || direction.equals("up right") || direction.equals("down left") || direction.equals("down right") )) {
            return new Result(false, "wrong direction.");
        }

        Farm farm = player.getCurrentFarm(game);
        int playerX = player.getPosition().getX();
        int playerY = player.getPosition().getY();
        Direction newDirection = Direction.directionManaging(direction, playerX, playerY);
        int targetCellX = newDirection.getX();
        int targetCellY = newDirection.getY();
        Cells targetCell = farm.findCellFarm(targetCellX, targetCellY);

        if (targetCell == null) {
            return new Result(false, "Cell not found");
        }
        if (!(targetCell.getObjectOnCell() instanceof Crop) && !(targetCell.getObjectOnCell() instanceof Tree)) {
            return new Result(false, "Target cell is not a crop or tree");
        }
        Loot miscSlot = player.getInventory().findItemLoot(fertilizer);
        if (miscSlot == null) {
            return new Result(false, "Fertilizer not in your inventory");
        }
        miscSlot.setCount(miscSlot.getCount() - 1);
        if (miscSlot.getCount() <= 0) {
            player.getInventory().getLoots().remove(miscSlot);
        }

        Crop crop = (Crop) targetCell.getObjectOnCell();

//        if (fertilizer.equalsIgnoreCase(ElseType.BASIC_FERTILIZER.name)) {
//            crop.pushBackDeadlines(-1);
//        }
//        else if (fertilizer.equalsIgnoreCase(ElseType.QUALITY_FERTILIZER.name)) {
//            crop.setHasBeenDeluxeFertilized(true);
//        }

        return new Result(true, "Fertilization was successful");
    }

    public Result howMuchWater() {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int currentWater = player.getInventory().getWateringCan().getWaterReserve();
        return new Result(true, "You have " +  currentWater + " water in your watering can");
    }

    public Result cropInfo(String name){
        CropType cropType =  CropType.findCropByName(name);

        if(cropType == null){
            return new Result(false, "Crop not found!");
        }
        return new Result(true, CropType.CropInfo(cropType));
    }





}
