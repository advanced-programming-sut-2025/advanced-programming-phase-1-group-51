package Models.Items;

import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.ToolType;

public class Tool extends Item{
    private ToolType type;
    private int waterReserve;

    public Tool() {
        super();
    }

    public Tool(Quality quality, int value, double energyCost, String name, ToolType type, int waterReserve) {
        super(quality, 1, value, energyCost, name);
        this.type = type;
        this.waterReserve = waterReserve;
    }

    public Tool(Quality quality, ToolType type, int price) {
        super(quality, 1, 0, 5, type.name);
        this.type = type;
        this.waterReserve = type.waterCapacity;
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

    @Override
    public String toString() {
        return this.quality.toString() + " " + this.name;
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

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    @Override
    public String getName() {
        return this.toString();
    }

}
