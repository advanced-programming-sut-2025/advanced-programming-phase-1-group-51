package io.github.StardewValley.Models.Items;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ElseType;

public class Else extends Item{
    private ElseType elseType;


    public Else(ElseType ElseType, Quality quality) {
        super(quality, Integer.MAX_VALUE, ElseType.value, 0, ElseType.name);
        this.elseType = ElseType;
    }

    public Else(Quality quality, ElseType ElseType, int price) {
        super(quality, Integer.MAX_VALUE, price, 0, ElseType.name);
        this.elseType = ElseType;
    }

    public Else(ElseType ElseType) {
        this(ElseType, Quality.DEFAULT);
    }

    public ElseType getElseType() {
        return elseType;
    }

    public void setElseType(ElseType ElseType) {
        this.elseType = ElseType;
    }
}
