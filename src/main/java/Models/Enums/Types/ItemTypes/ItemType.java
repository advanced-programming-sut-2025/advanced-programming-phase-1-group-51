package Models.Enums.Types.ItemTypes;


import Models.Loot;

public interface ItemType {
    Loot createAmountOfItem(int amount);
    String getName();
    String name();
}
