package Models.NPCs;

import Models.Items.Item;
import Models.Quest;

import java.util.ArrayList;

public abstract class NPC {

    private ArrayList<NPCsFriendship> friends;
    private String name;
    private ArrayList<NPCReward> rewards;
    private ArrayList<NPCsSpeak>speak ;
    private ArrayList<NPCRequest> requests;
    private ArrayList<Item> FavoriteItems ;
    private ArrayList<Quest> Quests;
    Position position;
}
