package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Types.ItemTypes.CropSeedsType;
import Models.Enums.Types.ItemTypes.ToolType;
import Models.Items.Tool;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.*;

public class Farming extends Controller {

    public Result PlantSeed(String seed, String direction) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        CropSeedsType cropSeedsType = CropSeedsType.findCropBySeed(seed);
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

        Tool toolInHand = (Tool) player.getItemInHand();
        ToolType toolType = toolInHand.getType();

        if(targetCell == null) {
            return new Result(false, "Cell not found");
        }

        if(!targetCell.isHasBeenPlowed()){
            return new Result(false,"You have to first plow this cell");
        }





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
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        BackPack backpack = player.getInventory();

        return new Result(true, "You fertilized " );
    }

    public Result howMuchWater() {
        Game game = App.getCurrentUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int currentWater = player.getInventory().getWateringCan().getWaterReserve();
        return new Result(true, "You have " +  currentWater + " water in your watering can");
    }

    public Result cropInfo(String name){
        CropSeedsType cropType =  CropSeedsType.findCropByName(name);

        if(cropType == null){
            return new Result(false, "Crop not found!");
        }
        return new Result(true, CropSeedsType.CropInfo(cropType));
    }





}
