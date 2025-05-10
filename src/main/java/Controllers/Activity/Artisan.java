package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Types.ObjectsOnMapType.ArtisanBlockType;

public class Artisan extends Controller {

    public Result ArtisanUse(String artisanName, String itemName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        BackPack backPack = player.getInventory();

         ArtisanBlockType ArtisanBlock = ArtisanBlockType.getArtisanBlockTypeByName(artisanName);

        if()


        return new Result(true,"www");
    }

    public Result ArtisanGet(String artisanName) {
        return null;
    }



}
