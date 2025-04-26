package Models.NPCs;

import Models.Items.Item;

public class NPCRequest {

    private Item[] requestedItems;

    public NPCRequest(Item[] requestedItems) {
        this.requestedItems = requestedItems;
    }

    public Item[] getRequestedItems() {
        return requestedItems;
    }

    public void setRequestedItems(Item[] requestedItems) {
        this.requestedItems = requestedItems;
    }
}
