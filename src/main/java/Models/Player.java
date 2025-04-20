package Models;

import Models.Enums.Types.TrashcanType;
import Models.Items.Tool;
import Models.NPCs.NPCsFriendship;
import Models.Skills.Skill;

import java.util.ArrayList;

public class Player {
    private Position position;
    private Farm farm;
    private ArrayList<FriendShip> friendShips;
    private ArrayList<Quest> quests;
    private TrashcanType trashcanType;
    private ArrayList<Animals> animals;
    private int energy;
    private ArrayList<NPCsFriendship> NPCsFriendships;
    private BackPack inventory;
    private int money;
    private ArrayList<Skill> skills;
    private User user;

    public Player(Position position, int money ,Farm farm, User user, int energy) {
        this.position = position;
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

    public TrashcanType getTrashcanType() {
        return trashcanType;
    }

    public void setTrashcanType(TrashcanType trashcanType) {
        this.trashcanType = trashcanType;
    }

    public ArrayList<Animals> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animals> animals) {
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

}
