package io.github.StardewValley.Models;

import io.github.StardewValley.Models.Enums.Others.Quality;
import io.github.StardewValley.Models.Enums.Types.ItemTypes.*;
import io.github.StardewValley.Models.Items.*;

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
            item = new Mineral((ForagingMineralType) type);
        } else if (type instanceof ForagingCropType) {
            item = new ForagingCrop((ForagingCropType) type);
        } else if (type instanceof CropType) {
            item = new Crop((CropType) type);
        } else if (type instanceof ForagingCropSeedType) {
            item = new CropSeed((ForagingCropSeedType) type);
        } else if (type instanceof FishType) {
            item = new Fish((FishType) type);
        } else if (type instanceof ForagingTreeSeedType) {
            item = new TreeSeed((ForagingTreeSeedType) type);
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
