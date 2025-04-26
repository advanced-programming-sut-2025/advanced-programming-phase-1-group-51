package Models;

import Models.NPCs.NPCsFriendship;
import Models.Skills.Skill;
import Models.Map.Farm;
import Models.Map.Position;

import java.util.ArrayList;

public class Player {
    private Position position;
    private Farm farm;
    private ArrayList<FriendShip> friendShips;
    private ArrayList<Quest> quests;
    private TrashCan trashCan;
    private ArrayList<Animal> animals;
    private int energy;
    private final int MaxEnergy = 200;
    private ArrayList<NPCsFriendship> NPCsFriendships;
    private BackPack inventory;
    private int money;
    private ArrayList<Skill> skills;
    private User user;
    private DateAndTime dateAndTime;
    private boolean isPlayerFainted = false;




    public Player(Position position, int money ,Farm farm, User user, int energy) {
        this.position = position;
    }

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(DateAndTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public ArrayList<FriendShip> getFriendShips() {
        return friendShips;
    }

    public void setFriendShips(ArrayList<FriendShip> friendShips) {
        this.friendShips = friendShips;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public ArrayList<NPCsFriendship> getNpCsFriendships() {
        return NPCsFriendships;
    }

    public void setNpCsFriendships(ArrayList<NPCsFriendship> npCsFriendships) {
        this.NPCsFriendships = npCsFriendships;
    }

    public BackPack getInventory() {
        return inventory;
    }

    public void setInventory(BackPack inventory) {
        this.inventory = inventory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void increaseEnergy(int energy){

    }

    public static void decreaseEnergy(int energy){

    }

    public boolean isPlayerFainted() {
        return isPlayerFainted;
    }

    public void setPlayerFainted(boolean playerFainted) {
        isPlayerFainted = playerFainted;
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public void setTrashCan(TrashCan trashCan) {
        this.trashCan = trashCan;
    }


    public void increaseWood(int wood){

    }

    public void increaseGold(int Gold){

    }
}
