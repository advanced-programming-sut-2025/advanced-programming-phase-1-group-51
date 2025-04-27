package Models;

import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Enums.Types.BackpackType;
import Models.Enums.Types.ToolType;
import Models.Enums.Types.TrashcanType;
import Models.Items.Item;
import Models.Items.Tool;
import Models.Map.Farm;
import Models.NPCs.NPCsFriendship;
import Models.Skills.*;
import java.util.ArrayList;

public class Player {
    private Position position;
    private Farm farm;
    private ArrayList<FriendShip> friendShips;
    private TrashcanType trashcanType;
    private TrashCan trashcan;
    private ArrayList<Animal> animals;
    private int energy;
    private int maxEnergy;
    private ArrayList<NPCsFriendship> NPCsFriendships;
    private BackPack inventory;
    private int money;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Quest> quests = new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<CraftingRecipes> craftingRecipes = new ArrayList<>();
    private User user;
    private DateAndTime dateAndTime;
    private boolean isPlayerFainted = false;
    private int id;
    private Item itemInHand;
    public static ArrayList<Player> players = new ArrayList<>();




    public Player(User user) {
        this.user = user;
        this.id = user.getId();
        this.inventory = new BackPack(BackpackType.DEFAULT);
        this.trashcanType = TrashcanType.DEFAULT;
        this.energy = 200;
        this.maxEnergy = 200;
        this.money = 0;
        this.position = new Position(0, 0);
        this.itemInHand = null;
        this.isPlayerFainted = false;
        initializeInventory();
        initializeSkills();
        initializeRecipes();
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

    public TrashcanType getTrashcanType() {
        return trashcanType;
    }

    public void setTrashcanType(TrashcanType trashcanType) {
        this.trashcanType = trashcanType;
    }

    public TrashCan getTrashcan() {
        return trashcan;
    }

    public void setTrashcan(TrashCan trashcan) {
        this.trashcan = trashcan;
    }

    public void increaseWood(int wood){

    }

    public void increaseGold(int Gold){

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private void initializeRecipes() {
        this.craftingRecipes.add(CraftingRecipes.FURNACE);
        this.craftingRecipes.add(CraftingRecipes.SCARE_CROW);
        this.craftingRecipes.add(CraftingRecipes.MAYONNAISE_MACHINE);
        this.cookingRecipes.add(CookingRecipes.FRIED_EGG);
        this.cookingRecipes.add(CookingRecipes.BAKED_FISH);
        this.cookingRecipes.add(CookingRecipes.SALAD);
    }

    private void initializeSkills() {
        this.skills.add(new Farming());
        this.skills.add(new Fishing());
        this.skills.add(new Foraging());
        this.skills.add(new Mining());
    }

    private void initializeInventory() {
        this.inventory.getLoots().add(
                new Loot(new Tool(Quality.DEFAULT, 0, 5, "Default Hoe", ToolType.HOE, 0), 1));
        this.inventory.getLoots().add(
                new Loot(new Tool(Quality.DEFAULT, 0, 5, "Default Pickaxe", ToolType.PICKAXE, 0), 1));
        this.inventory.getLoots().add(
                new     Loot(new Tool(Quality.DEFAULT, 0, 5, "Default Axe", ToolType.AXE, 0), 1));
        this.inventory.getLoots().add(
                new Loot(new Tool(Quality.DEFAULT, 0, 5, "Default Water Can", ToolType.WATERING_CAN_DEFAULT, 40), 1));
        this.inventory.getLoots().add(
                new Loot(new Tool(Quality.DEFAULT, 0, 5, "Default Scythe", ToolType.SCYTHE, 0), 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return String.valueOf(player.getId()).equals(String.valueOf(this.getId()));
    }
}
