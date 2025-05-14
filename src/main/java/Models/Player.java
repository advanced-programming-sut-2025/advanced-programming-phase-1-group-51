package Models;

import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Enums.Types.BackpackType;
import Models.Enums.Types.ItemTypes.ToolType;
import Models.Enums.Types.TrashcanType;
import Models.Items.Item;
import Models.Items.Tool;
import Models.Maps.Farm;
import Models.NPCs.NPCsFriendship;
import Models.Skills.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Player {

        @JsonIgnore
        private User user; // Will be reconstructed from ID

        @JsonProperty("userId")
        public int getUserId() {
            return user != null ? user.getId() : -1;
        }

    private Position position;
    private Farm farm;
    private ArrayList<FriendShip> friendShips;
    private TrashcanType trashcanType;
    private TrashCan trashcan;
    private ArrayList<Animal> animals = new ArrayList<>();;
    private int energy;
    private int maxEnergy;
    private ArrayList<NPCsFriendship> NPCsFriendships;
    private BackPack inventory;
    private int money;
    private ArrayList<Skill> skills = new ArrayList<>();
    private ArrayList<Quest> quests = new ArrayList<>();
    private ArrayList<CookingRecipes> cookingRecipes = new ArrayList<>();
    private ArrayList<CraftingRecipes> craftingRecipes = new ArrayList<>();
    private boolean isPlayerFainted = false;
    private int id;
    private Item itemInHand;
    private ArrayList<Loot> refrigeratorLoots = new ArrayList<>();
    private int currentTurnUsedEnergy;
    private boolean isInVillage;
    private boolean isInFarm;
    private boolean isCloseToLake;
    private boolean isInHouse;
    private int currentPlaceNumber;
    private ArrayList<ActiveBuff> activeBuffs = new ArrayList<>();


    public Player(User user) {
        this.isInVillage = false;
        this.isInHouse = false;
        this.user = user;
        this.id = user.getId();
        this.inventory = new BackPack(BackpackType.DEFAULT);
        this.trashcanType = TrashcanType.DEFAULT;
        this.currentTurnUsedEnergy = 0;
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


    public Loot getRefrigeratorLootByName(String slotName) {
        for (Loot loot : refrigeratorLoots) {
            if (loot.getItem().getName().compareToIgnoreCase(slotName) == 0) {
                return loot;
            }
        }
        return null;
    }



    public Farming getFarmingSkill() {

        return null;
    }

    public Fishing getFishingSkill() {

        return null;
    }

    public Foraging getForagingSkill() {

        return null;
    }

    public Mining getMiningSkill() {

        return null;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Loot> getRefrigeratorLoots() {
        return refrigeratorLoots;
    }

    public void setRefrigeratorLoots(ArrayList<Loot> refrigeratorLoots) {
        this.refrigeratorLoots = refrigeratorLoots;
    }

    public ArrayList<CookingRecipes> getCookingRecipes() {
        return cookingRecipes;
    }

    public void setCookingRecipes(ArrayList<CookingRecipes> cookingRecipes) {
        this.cookingRecipes = cookingRecipes;
    }

    public ArrayList<CraftingRecipes> getCraftingRecipes() {
        return craftingRecipes;
    }

    public void setCraftingRecipes(ArrayList<CraftingRecipes> craftingRecipes) {
        this.craftingRecipes = craftingRecipes;
    }

    public int getCurrentTurnUsedEnergy() {
        return currentTurnUsedEnergy;
    }

    public void setCurrentTurnUsedEnergy(int currentTurnCurrentTurnUsedEnergy) {
        this.currentTurnUsedEnergy = currentTurnCurrentTurnUsedEnergy;
    }

    public Item getItemInHand() {
        return itemInHand;
    }

    public void setItemInHand(Item itemInHand) {
        this.itemInHand = itemInHand;
    }

    public boolean isInVillage() {
        return isInVillage;
    }

    public void setInVillage(boolean inVillage) {
        isInVillage = inVillage;
    }

    public boolean isCloseToLake() {
        return isCloseToLake;
    }

    public void setCloseToLake(boolean closeToLake) {
        isCloseToLake = closeToLake;
    }

    public boolean isInFarm() {
        return isInFarm;
    }

    public void setInFarm(boolean inFarm) {
        isInFarm = inFarm;
    }

    public boolean isInHouse() {
        return isInHouse;
    }

    public void setInHouse(boolean inHouse) {
        isInHouse = inHouse;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }



    public ArrayList<ActiveBuff> getActiveBuffs() {
        return activeBuffs;
    }



    public Animal findAnimal(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    public int getMaxEnergy() {
        int temp = maxEnergy;
        for (ActiveBuff activeBuff : activeBuffs) {
            if (activeBuff.getFoodBuff().getAffectedSkill().equalsIgnoreCase("maxEnergy")) {
                temp += activeBuff.getFoodBuff().getMaxEnergyIncrease();
            }
        }
        return temp;
    }

    public Farm getCurrentFarm(Game game){
        return game.getFarmByNumber(currentPlaceNumber);
    }


    private void initializeSkills() {
        this.skills.add(new Farming());
        this.skills.add(new Fishing());
        this.skills.add(new Foraging());
        this.skills.add(new Mining());
    }

    private void initializeRecipes() {
        this.craftingRecipes.add(CraftingRecipes.FURNACE);
        this.craftingRecipes.add(CraftingRecipes.SCARE_CROW);
        this.craftingRecipes.add(CraftingRecipes.MAYONNAISE_MACHINE);
        this.cookingRecipes.add(CookingRecipes.FRIED_EGG);
        this.cookingRecipes.add(CookingRecipes.BAKED_FISH);
        this.cookingRecipes.add(CookingRecipes.SALAD);
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
