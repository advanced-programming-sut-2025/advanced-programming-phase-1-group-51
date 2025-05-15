package Controllers.Others;
import Controllers.BaseController;
import Models.*;
import Models.Maps.Position;

public class MovementAndMapController  extends BaseController {

    public Result Walking(int x,int y) {
        return null;
    }


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
}
