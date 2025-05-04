package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ToolType;

public class Tool extends Item{
    private ToolType type;
    private int waterReserve;

    public Tool(Quality quality, int value, double energyCost, String name, ToolType type, int waterReserve) {
        super(quality, 1, value, (int)energyCost, name);
        this.type = type;
        this.waterReserve = waterReserve;
    }

    /// Only in blacksmith shop.
    public void upgradeTool() {

    }

    @Override
    public void useItem() {

    }

    @Override
    public void deleteItem() {

    }

    @Override
    public void dropItem() {

    }

    public ToolType getType() {
        return type;
    }

    public int getWaterReserve() {
        return waterReserve;
    }

    public void setWaterReserve(int waterReserve) {
        this.waterReserve = waterReserve;
    }

}
