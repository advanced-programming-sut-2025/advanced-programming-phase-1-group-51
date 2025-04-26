package Models.Items;

import Models.Enums.Others.Quality;

public class Food extends Item{
    public boolean isEdible;

    public Food(Quality quality, int maxStackSize, int value, int energyCost, String name, boolean isEdible) {
        // Call the parent (Item) constructor first
        super(quality, maxStackSize, value, energyCost, name);
        // Then initialize Food-specific fields
        this.isEdible = isEdible;
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
}
