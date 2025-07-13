package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ToolType;

public class Tool extends Item{
    private ToolType type;

    public Tool() {
        super();
    }

    public Tool(Quality quality, int value, double energyCost, String name, ToolType type) {
        super(quality, 1, value, energyCost, name);
        this.type = type;
    }

    public Tool(Quality quality, ToolType type, int price) {
        super(quality, 1, 0, 5, type.name);
        this.type = type;;
    }

    public ToolType getType() {
        return type;
    }
}
