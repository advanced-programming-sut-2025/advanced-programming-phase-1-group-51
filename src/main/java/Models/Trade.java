package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Trade {
    public enum Status { PENDING, ACCEPTED, REJECTED }
    public enum Type { REQUEST, OFFER }

    private final UUID id;
    private final Player sender;
    private final Player receiver;
    private final String itemName;
    private final int amount;
    //    private final LocalDateTime creationTime;
    private Status status;
    private String responseMessage;

    public Trade(Player sender, Player receiver, String itemName, int amount) {
        this.id = UUID.randomUUID();
        this.sender = sender;
        this.receiver = receiver;
        this.itemName = itemName;
        this.amount = amount;
//        this.creationTime = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    // Getters
    public UUID getId() { return id; }
    public Player getSender() { return sender; }
    public Player getReceiver() { return receiver; }
    public String getItemName() { return itemName; }
    public int getAmount() { return amount; }
    //    public LocalDateTime getCreationTime() { return creationTime; }
    public Status getStatus() { return status; }
    public String getResponseMessage() { return responseMessage; }

    // Setters
    public void setStatus(Status status) { this.status = status; }
    public void setResponseMessage(String message) { this.responseMessage = message; }
}