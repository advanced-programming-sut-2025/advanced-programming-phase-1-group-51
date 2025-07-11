package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.MiscType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.FoodType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ForagingMineralType;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.ItemType;
import io.github.StardewValley.Models.Items.Misc;
import io.github.StardewValley.Models.Items.Food;
import io.github.StardewValley.Models.Items.Item;
import io.github.StardewValley.Models.Items.Mineral;

public class Slot {
    private Item item;
    private int count;

    public Slot(ItemType type, int count) {
        Item item = null;
        if (type instanceof FoodType) {
            item = new Food((FoodType) type);
        } else if (type instanceof MiscType) {
            item = new Misc((MiscType) type);
        } else if (type instanceof ForagingMineralType) {
            item = new Mineral(Quality.DEFAULT, (ForagingMineralType) type);
        }
        this.item = item;
        this.count = count;
    }

    public Slot(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Loot [header=" + item.getName() + ", count=" + count + "]";
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
