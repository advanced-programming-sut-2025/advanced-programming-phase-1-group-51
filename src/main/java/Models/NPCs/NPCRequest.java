package Models.NPCs;

import java.util.ArrayList;

public class NPCRequest {


    private ArrayList<String> NPCRequest;

    public ArrayList<String> getNPCRequest() {
        return NPCRequest;
    }

    public void setNPCQuest(ArrayList<String> NPCRequest) {
        this.NPCRequest = NPCRequest;
    }

    public NPCRequest(ArrayList<String> NPCRequest) {
        this.NPCRequest = NPCRequest;


    }
}
