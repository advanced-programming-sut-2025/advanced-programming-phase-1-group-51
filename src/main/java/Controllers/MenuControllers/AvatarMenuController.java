package Controllers.MenuControllers;

import Controllers.Controller;
import Models.Game;
import Models.Result;

public class AvatarMenuController extends Controller {


    public static Result showCurrentMenu(){
        return new Result(true, Game.getCurrentMenu().name());
    }
}
