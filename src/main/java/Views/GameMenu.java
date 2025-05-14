package Views;

import Controllers.Activity.*;
import Controllers.Others.*;
import Models.App;
import Models.Enums.MenuCommands.GameMenuCommands;
import Models.Enums.MenuCommands.ProfileMenuCommands;
import Models.Game;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements PlayMenu{

    private final TurnAndSaveGameController SavingNextTurnController = new TurnAndSaveGameController();
    private final MovementAndMapController MovementAndMapController = new MovementAndMapController();
    private final EnergyController EnergyController = new EnergyController();
    private final InventoryController InventoryController = new InventoryController();
    private final Farming Farming = new Farming();
    private final Crafting Crafting = new Crafting();
    private final Cooking Cooking = new Cooking();
    private final Husbandry Husbandry = new Husbandry();
    private final Artisan Artisan = new Artisan();
    private final FriendShipController FriendshipController = new FriendShipController();
    private final NPCController NPCController = new NPCController();
    private final OthersController OthersController = new OthersController();
    @Override
    public void check(Scanner scanner){
        String input = App.scanner.nextLine();
        Matcher matcher;

        if((matcher = GameMenuCommands.SHOW_CURRENT_MENU.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.showCurrentMenu());
        }
        else if(GameMenuCommands.GO_TO_MAIN.getMatcher(input) != null){
             System.out.println(SavingNextTurnController.goToMain());
         }
        else if((matcher = GameMenuCommands.GAME_NEW.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.newGame(matcher.group("username1").trim(),matcher.group("username2").trim(),
                    matcher.group("username3").trim(),matcher.group("username4").trim()));
        }
        else if((matcher = GameMenuCommands.GAME_MAP.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.gameMap(Integer.parseInt(matcher.group("mapNumber").trim())));
        }
        else if(GameMenuCommands.LOAD_GAME.getMatcher(input) != null){
            System.out.println(SavingNextTurnController.loadGame());
        }
        else if((matcher = GameMenuCommands.EXIT_GAME.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.exitGame());
        }
        else if((matcher = GameMenuCommands.DELETE_GAME.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.deleteGame());
        }
        else if ((matcher = GameMenuCommands.NEXT_TURN.getMatcher(input)) != null){
            System.out.println(SavingNextTurnController.nextTurn());
        }
        else if ((matcher = GameMenuCommands.TIME.getMatcher(input)) != null){
            System.out.println(OthersController.Time());
        }
        else if ((matcher = GameMenuCommands.DATE.getMatcher(input)) != null){
            System.out.println(OthersController.Date());
        }
        else if ((matcher = GameMenuCommands.DATE_TIME.getMatcher(input)) != null){
            System.out.println(OthersController.DateTime());
        }
        else if ((matcher = GameMenuCommands.DAY_OF_WEEK.getMatcher(input)) != null){
            System.out.println(OthersController.DayOfTheWeek());
        }
        else if ((matcher = GameMenuCommands.TIME_CHEAT.getMatcher(input)) != null){
            System.out.println(OthersController.cheatAdvanceTime(Integer.parseInt(matcher.group("X").trim())));
        }
        else if ((matcher = GameMenuCommands.DATE_CHEAT.getMatcher(input)) != null){
            System.out.println(OthersController.cheatAdvanceDate(Integer.parseInt(matcher.group("X").trim())));
        }
        else if ((matcher = GameMenuCommands.SEASON.getMatcher(input)) != null){
            System.out.println(OthersController.season());
        }
        else if ((matcher = GameMenuCommands.WEATHER.getMatcher(input)) != null){
            System.out.println(OthersController.weather());
        }
        else if ((matcher = GameMenuCommands.WEATHER_FORECAST.getMatcher(input)) != null){
            System.out.println(OthersController.weatherForecast());
        }
        else if ((matcher = GameMenuCommands.CHEAT_THOR.getMatcher(input)) != null){
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            System.out.println(OthersController.CheatThor(x,y));
        }
        else if ((matcher = GameMenuCommands.CHEAT_WEATHER_SET.getMatcher(input)) != null){
            String weather = matcher.group("Type").trim();
            System.out.println(OthersController.setWeatherCheat(weather));
        }
        else if((matcher = GameMenuCommands.GREEN_HOUSE_BUILD.getMatcher(input)) != null){
            System.out.println(OthersController.GreenhouseBuild());
        }
        else if((matcher = GameMenuCommands.WALK.getMatcher(input)) != null){
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            System.out.println(MovementAndMapController.Walking(x, y));
        }
        else if ((matcher = GameMenuCommands.PRINT_MAP.getMatcher(input)) != null){
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            int size = Integer.parseInt(matcher.group("size").trim());
            System.out.println(MovementAndMapController.printMap(x,y,size));
        }
        else if ((matcher = GameMenuCommands.HELP_READING_MAP.getMatcher(input)) != null){
            System.out.println(MovementAndMapController.helpReadingMap());
        }
        else if ((matcher = GameMenuCommands.ENERGY_SHOW.getMatcher(input)) != null){
            System.out.println(EnergyController.energyShow());
        }
        else if ((matcher = GameMenuCommands.CHEAT_ENERGY_SET.getMatcher(input)) != null){
            int energy = Integer.parseInt(matcher.group("value").trim());
            System.out.println(EnergyController.cheatEnergySet(energy));
        }
        else if ((matcher = GameMenuCommands.CHEAT_ENERGY_UNLIMITED.getMatcher(input)) != null){
            System.out.println(EnergyController.cheatEnergyUnlimited());
        }
        else if ((matcher = GameMenuCommands.INVENTORY_SHOW.getMatcher(input)) != null){
            System.out.println(InventoryController.showInventory());
        }
        else if ((matcher = GameMenuCommands.INVENTORY_TRASH_FULL.getMatcher(input)) != null){
            String itemName = matcher.group("ItemName").trim();

            System.out.println(InventoryController.inventoryTrashFull(itemName));
        }
        else if ((matcher = GameMenuCommands.INVENTORY_TRASH_NOT_FULL.getMatcher(input)) != null){
            String itemName = matcher.group("ItemName").trim();
            int number = Integer.parseInt(matcher.group("number").trim());
            System.out.println(InventoryController.inventoryTrashNotFull(itemName, number));
        }
        else if ((matcher = GameMenuCommands.TOOLS_EQUIP.getMatcher(input)) != null){
            String name = matcher.group("toolName").trim();
            System.out.println(InventoryController.toolEquip(name));
        }
        else if ((matcher = GameMenuCommands.TOOLS_SHOW_CURRENT.getMatcher(input)) != null){
            System.out.println(InventoryController.showCurrentTool());
        }
        else if ((matcher = GameMenuCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input)) != null){
            System.out.println(InventoryController.showAvailableTools());
        }
        else if ((matcher = GameMenuCommands.TOOLS_USE.getMatcher(input)) != null){
            String direction = matcher.group("direction").trim();
            System.out.println(InventoryController.toolUse(direction));
        }
        else if ((matcher = GameMenuCommands.PLANT.getMatcher(input)) != null){
            String seed = matcher.group("seed").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(Farming.PlantSeed(seed, direction));
        }
        else if ((matcher = GameMenuCommands.SHOW_PLANT.getMatcher(input)) != null){
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            System.out.println(Farming.showPlants(x,y));
        }
        else if ((matcher = GameMenuCommands.FERTILIZE.getMatcher(input)) != null){
            String fertilizer = matcher.group("fertilizer").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(Farming.fertilization(fertilizer, direction));
        }
        else if ((matcher = GameMenuCommands.HOW_MUCH_WATER.getMatcher(input)) != null){
            System.out.println(Farming.howMuchWater());
        }
        else if ((matcher = GameMenuCommands.CROP_INFO.getMatcher(input)) != null){
            String name = matcher.group("cropName").trim();
            System.out.println(Farming.cropInfo(name));
        }
        else if ((matcher = GameMenuCommands.CRAFTING_SHOW_RECIPES.getMatcher(input)) != null){
            System.out.println(Crafting.showCraftingRecipes());
        }
        else if ((matcher = GameMenuCommands.CRAFTING_CRAFT.getMatcher(input)) != null){
            String name = matcher.group("itemName").trim();
            System.out.println(Crafting.craftingCraft(name));
        }
        else if ((matcher = GameMenuCommands.PLACE_ITEM.getMatcher(input)) != null){
            String name = matcher.group("name").trim();
            String direction = matcher.group("direction").trim();
            System.out.println(Crafting.placeItem(name, direction));
        }
        else if ((matcher = GameMenuCommands.CHEAT_ADD_ITEM.getMatcher(input)) != null){
            String name = matcher.group("itemName").trim();
            int count = Integer.parseInt(matcher.group("count").trim());
            System.out.println(InventoryController.addItem(name, count));
        }
        else if ((matcher = GameMenuCommands.REFRIGERATOR_PICK.getMatcher(input)) != null){
            String name = matcher.group("item").trim();
            System.out.println(Cooking.TakeOutOfRefrigerator(name));
        }
        else if ((matcher = GameMenuCommands.REFRIGERATOR_PUT.getMatcher(input)) != null) {
            String name = matcher.group("item").trim();
            System.out.println(Cooking.PutInRefrigerator(name));
        }
        else if ((matcher = GameMenuCommands.SHOW_COOKING_RECIPES.getMatcher(input)) != null) {
            System.out.println(Cooking.showCookingRecipes());
        }
        else if ((matcher = GameMenuCommands.PREPARE.getMatcher(input)) != null){
            String recipeName = matcher.group("recipeName").trim();
            System.out.println(Cooking.prepareFood(recipeName));
        }
        else if ((matcher = GameMenuCommands.EAT.getMatcher(input)) != null){
            String foodName = matcher.group("foodName").trim();
            System.out.println(Cooking.Eating(foodName));
        }
        else if ((matcher = GameMenuCommands.BUILD.getMatcher(input)) != null){
            String buildingName = matcher.group("buildingName").trim();
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            System.out.println(OthersController.BuildBuilding(buildingName, x, y));
        }
        else if ((matcher = GameMenuCommands.PET.getMatcher(input)) != null){
            String name = matcher.group("name").trim();
            System.out.println(Husbandry.Pet(name));
        }
        else if ((matcher = GameMenuCommands.ANIMALS.getMatcher(input)) != null){
            System.out.println(Husbandry.animals());
        }
        else if ((matcher = GameMenuCommands.SHEPHERD.getMatcher(input)) != null){
            String name = matcher.group("animalName").trim();
            int x = Integer.parseInt(matcher.group("x").trim());
            int y = Integer.parseInt(matcher.group("y").trim());
            System.out.println(Husbandry.ShepherdAnimals(name, x, y));
        }
        else if ((matcher = GameMenuCommands.FEED_HAY.getMatcher(input)) != null){
            String name = matcher.group("animalName").trim();
            System.out.println(Husbandry.FeedHay(name));
        }
        else if ((matcher = GameMenuCommands.PRODUCES.getMatcher(input)) != null){
            System.out.println(Husbandry.Produces());
        }
        else if ((matcher = GameMenuCommands.COLLECT_PRODUCE.getMatcher(input)) != null){
            String name = matcher.group("name").trim();
            System.out.println(Husbandry.CollectProduce(name));
        }
        else if ((matcher = GameMenuCommands.SELL_ANIMAL.getMatcher(input)) != null){
            String name = matcher.group("name").trim();
            System.out.println(Husbandry.SellAnimal(name));
        }
        else if ((matcher = GameMenuCommands.CHEAT_SET_FRIENDSHIP.getMatcher(input)) != null){
            String name = matcher.group("animalName").trim();
            int amount = Integer.parseInt(matcher.group("amount").trim());
            System.out.println(Husbandry.cheatSetFriendship(name, amount));
        }
        else if ((matcher = GameMenuCommands.ARTISAN_USE.getMatcher(input)) != null){
            String artisanName = matcher.group("artisanName").trim();
            String itemName = matcher.group("itemName").trim();
            System.out.println(Artisan.ArtisanUse(artisanName, itemName));
        }
        else if ((matcher = GameMenuCommands.ARTISAN_GET.getMatcher(input)) != null){
            String artisanName = matcher.group("artisanName").trim();
            System.out.println(Artisan.ArtisanGet(artisanName));
        }
        else if ((matcher = GameMenuCommands.FRIENDSHIPS.getMatcher(input)) != null){
            System.out.println(FriendshipController.FriendShip());
        }
        else if ((matcher = GameMenuCommands.TALK.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            String message = matcher.group("message").trim();
            System.out.println(FriendshipController.Talk(username, message));
        }
        else if ((matcher = GameMenuCommands.TALK_HISTORY.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            System.out.println(FriendshipController.TalkHistory(username));
        }
        else if ((matcher = GameMenuCommands.GIFT.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            String item = matcher.group("item").trim();
            int amount = Integer.parseInt(matcher.group("amount").trim());
            System.out.println(FriendshipController.Gift(username, item, amount));
        }
        else if ((matcher = GameMenuCommands.GIFT_LIST.getMatcher(input)) != null){
            System.out.println(FriendshipController.GiftList());
        }
        else if ((matcher = GameMenuCommands.GIFT_RATE.getMatcher(input)) != null){
            int giftNumber = Integer.parseInt(matcher.group("giftNumber").trim());
            int giftRate = Integer.parseInt(matcher.group("giftRate").trim());
            System.out.println(FriendshipController.GiftRate(giftNumber, giftRate));
        }
        else if ((matcher = GameMenuCommands.GIFT_HISTORY.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            System.out.println(FriendshipController.GiftHistory(username));
        }
        else if ((matcher = GameMenuCommands.HUG.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            System.out.println(FriendshipController.Hug(username));
        }
        else if ((matcher = GameMenuCommands.FLOWER.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            System.out.println(FriendshipController.Flower(username));
        }
        else if ((matcher = GameMenuCommands.ASK_MARRIAGE.getMatcher(input)) != null){
            String username = matcher.group("username").trim();
            String ring = matcher.group("ring").trim();
            System.out.println(FriendshipController.AskMarriage(username,ring));
        }
        else if ((matcher = GameMenuCommands.RESPOND.getMatcher(input)) != null){
            String respond = matcher.group("respond").trim();
            String username = matcher.group("username").trim();
            System.out.println(FriendshipController.Respond(respond, username));
        }
        else if ((matcher = GameMenuCommands.MEET_NPC.getMatcher(input)) != null){
            String NPCname = matcher.group("npcName").trim();
            System.out.println(NPCController.NPCMeet(NPCname));
        }
        else if ((matcher = GameMenuCommands.GIFT_NPC.getMatcher(input)) != null){
            String NPCname = matcher.group("npcName").trim();
            String item = matcher.group("item").trim();
            System.out.println(NPCController.NPCGift(NPCname, item));
        }
        else if ((matcher = GameMenuCommands.FRIENDSHIP_NPC_LIST.getMatcher(input)) != null){
            System.out.println(NPCController.FriendShipNPCList());
        }
        else if ((matcher = GameMenuCommands.QUESTS_LIST.getMatcher(input)) != null){
            System.out.println(NPCController.QuestList());
        }
        else if ((matcher = GameMenuCommands.QUESTS_FINISH.getMatcher(input)) != null){
            int index = Integer.parseInt(matcher.group("index").trim());
            System.out.println(NPCController.QuestFinish(index));
        }
        else if ((matcher = GameMenuCommands.ENTER_STORE.getMatcher(input)) != null){
            System.out.println(OthersController.enterStore(matcher.group("storeName")));
        }
        else if ((matcher = GameMenuCommands.CHEAT_ADD.getMatcher(input)) != null){
            System.out.println(OthersController.cheatAddMoney(Integer.parseInt(matcher.group("count"))));
        }
        else if ((matcher = GameMenuCommands.SHOW_MONEY.getMatcher(input)) != null){
            System.out.println(OthersController.showMoney());
        }
        else if ((matcher = GameMenuCommands.START_TRADE.getMatcher(input)) != null){
            System.out.println(OthersController.StartTrade());
        }
        else{
            System.out.println("Invalid Command!");
        }
    }
}
