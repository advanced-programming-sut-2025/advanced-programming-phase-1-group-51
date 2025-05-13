package Models.Enums.Types.ItemTypes;


import Models.Enums.Others.Quality;
import Models.Loot;

public interface ItemType {
    Loot createAmountOfItem(int amount, Quality quality);
    String getName();
    String name();
}
