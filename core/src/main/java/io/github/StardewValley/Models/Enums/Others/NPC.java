package io.github.StardewValley.Models.Enums.Others;

import io.github.StardewValley.Models.Items.Item;

public enum NPC {
    SEBASTIAN("Sebastian", null),
    ABIGAIL("Abigail", null),
    HARVEY("Harvey", null),
    LYA("Lya", null),
    ROBIN("Robin", null),
    CLINT("Clint", null),
    MORRIS("Morris", null),
    PIERRE("Pierre", null),
    WILLY("Willy", null),
    MARNIE("Marnie", null),
    GUS("Gus", null);
    public final String name;
    public final Item[] items;

    NPC(String name, Item[] items) {
        this.name = name;
        this.items = items;
    }
}
