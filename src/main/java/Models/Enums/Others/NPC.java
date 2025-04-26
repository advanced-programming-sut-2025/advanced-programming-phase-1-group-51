package Models.Enums.Others;

import Models.Items.Item;
import Models.NPCs.NPCRequest;
import Models.NPCs.NPCReward;

public enum NPC {

    SEBASTIAN("Sebastian", new Item[]{}, new NPCRequest(new Item[]{}), new NPCReward(5000, new Item[]{})),
    ABIGAIL("Abigail", new Item[]{}, new NPCRequest(new Item[]{}), new NPCReward(500, new Item[]{})),
    HARVEY("Harvey", new Item[]{}, new NPCRequest(new Item[]{}), new NPCReward(750, new Item[]{})),
    LYA("Lya", new Item[]{}, new NPCRequest(new Item[]{}), new NPCReward(500, new Item[]{})),
    ROBIN("Robin", new Item[]{}, new NPCRequest(new Item[]{}), new NPCReward(25000, new Item[]{})),
    CLINT("Clint", null, null, null),
    MORRIS("Morris", null, null, null),
    PIERRE("Pierre", null, null, null),
    WILLY("Willy", null, null, null),
    MARNIE("Marnie", null, null, null),
    GUS("Gus", null, null, null),
    ;
    public final String name;
    public final Item[] items;
    public final NPCRequest request;
    public final NPCReward reward;

    NPC(String name, Item[] items, NPCRequest request, NPCReward reward) {
        this.name = name;
        this.items = items;
        this.request = request;
        this.reward = reward;
    }
}
