package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Enums.Types.ItemTypes.FoodType;
import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;
import Models.Items.Else;

public class Artisan extends Controller {

    public Result ArtisanUse(String artisanName, String itemName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        BackPack backPack = player.getInventory();
        ElseType elseType = ElseType.getMiscTypeByName(itemName);

        ArtisanBlockType artisanBlock = ArtisanBlockType.getArtisanBlockTypeByName(artisanName);


        if(!artisanBlock.ingridients.contains(elseType)){

        }

        Loot backpackLoot = backPack.getLootByItemName(itemName);

        if(backpackLoot == null){
            return new Result()
        }


        return new Result(true,"www");
    }


    public Result ArtisanGet(String artisanName) {
        return null;
    }



}
