package Models.NPCs;

import Models.Items.Item;

public class NPCReward {

    public int money;
    public Item[] rewardItems;
    public NPCReward(int money, Item[] rewardItems) {
        this.money = money;
        this.rewardItems = rewardItems;
    }

}
