package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;

public class Misc extends Item{
    private MiscType type;


    public Misc(MiscType ElseType, Quality quality) {
        super(quality, Integer.MAX_VALUE, ElseType.value, 0, ElseType.name);
        this.type = ElseType;
    }

    public Misc(Quality quality, MiscType ElseType, int price) {
        super(quality, Integer.MAX_VALUE, price, 0, ElseType.name);
        this.type = ElseType;
    }

    public Misc(MiscType ElseType) {
        this(ElseType, Quality.DEFAULT);
    }

    public MiscType getType() {
        return type;
    }
}
