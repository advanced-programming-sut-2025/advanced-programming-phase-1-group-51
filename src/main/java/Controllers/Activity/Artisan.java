package Controllers.Activity;

import Models.*;
import Models.Enums.Others.Quality;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ItemTypes.ForagingMineralType;
import Models.Items.Else;
import Models.Items.Food;
import Models.Items.Mineral;
import Models.Maps.Farm;
import Models.Maps.Village;
import Models.ObjectsShownOnMap.ArtisanCell;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Artisan {

    public   Result ArtisanUse(String artisanName, String itemName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Village village = game.getMap().getVillage();
        Player player = game.getCurrentPlayer();
        BackPack backpack = player.getInventory();
        ArtisanCell cell = village.getArtisanCell(artisanName);
        if (cell == null) {
            return new Result(false, "Artisan does not exist");
        }
        if(cell.beingUsed){
            return new Result(false, "Artisan already used");
        }
        if (artisanName.equals("Bee House")) {
            return honeyHandle(player, game, cell);
        } else if (artisanName.equals("Cheese Press")) {
            return cheesePress( itemName, player, game, cell, backpack);
        } else if (artisanName.equals("Keg")) {
            return keg( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Dehydrator")) {
            return Dehydrator( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Charcoal Klin")) {
            return charCoalKiln( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Loom")) {
            loom( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Mayonnaise Machine")) {
            return mayonnaiseMachine( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Oil Maker")) {
            return oilMaker( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Preserves Jar")) {
            return preservesJar( itemName, player, game, backpack, cell);
        } else if (artisanName.equals("Fish Smoker")) {
            return fishSmoker( itemName, player, game, backpack, cell);
        }
        return furnace( itemName, player, game, backpack, cell);
    }

    private Result furnace(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals(ForagingMineralType.COPPER_ORE.name)) {
            return artisanMiscHandleCoal(player, game, backpack,  itemName, block, 0, 4, ElseType.COPPER_BAR, 5, 1, 10 * ForagingMineralType.getPriceByName( itemName));
        } else if ( itemName.equals(ForagingMineralType.GOLD_ORE.name)) {
            return artisanMiscHandleCoal(player, game, backpack,  itemName, block, 0, 4, ElseType.GOLD_BAR, 5, 1, 10 * ForagingMineralType.getPriceByName( itemName));
        } else if ( itemName.equals(ForagingMineralType.IRON_ORE.name)) {
            return artisanMiscHandleCoal(player, game, backpack,  itemName, block, 0, 4, ElseType.IRON_BAR, 5, 1, 10 * ForagingMineralType.getPriceByName( itemName));
        } else if ( itemName.equals(ForagingMineralType.IRIDIUM_ORE.name)) {
            return artisanMiscHandleCoal(player, game, backpack,  itemName, block, 0, 4, ElseType.IRIDIUM_BAR, 5, 1, 10 * ForagingMineralType.getPriceByName( itemName));
        }
        return wrongItem(game);
    }

    public static boolean isFish(String itemName) {
        for (FishType f : FishType.values()) {
            if (f.name.equals(itemName))
                return true;
        }
        return false;
    }

    private  Result fishSmoker(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if (isFish(itemName)) {
            return artisanFoodHandleCoal(player, game, backpack,  itemName, block, 20, 1, FoodType.SMOKED_FISH, 1, 1, 2 * Objects.requireNonNull(FishType.findFishByName(itemName)).price);
        }
        else {
            wrongItem(game);
        }
        return null;
    }

    private Result preservesJar(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if (isVegetable( itemName)) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 7 * FoodType.getEnergy( itemName) / 4, 6, FoodType.PICKLES, 1, 1, 2 * FoodType.getPrice( itemName) + 50);
        } else if (isFruit( itemName)) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 2 * FoodType.getEnergy( itemName), 72, FoodType.JELLY, 1, 1, 2 * FoodType.getPrice( itemName) + 50);
        } else {
            return wrongItem(game);
        }
    }

    private   Result wrongItem(Game game) {

        return new Result(false, "wrong item selected");
    }

    private   Result oilMaker(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Truffle")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 38, 6, FoodType.TRUFFLE_OIL, 1, 1, 1065);
        } else if ( itemName.equals("Corn")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 13, 6, FoodType.OIL, 1, 1, 100);
        } else if ( itemName.equals("Sunflower Seeds")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 13, 48, FoodType.OIL, 1, 1, 100);
        } else if ( itemName.equals("Sunflower")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 13, 1, FoodType.OIL, 1, 1, 100);
        } else {
            return wrongItem(game);
        }
    }

    private   Result mayonnaiseMachine(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Egg")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 50, 3, FoodType.MAYONNAISE, 1, 1, 190);
        } else if ( itemName.equals("Big Egg")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 50, 3, FoodType.MAYONNAISE, 1, 1, 237);
        } else if ( itemName.equals("Duck Egg")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 75, 3, FoodType.DUCK_MAYONNAISE, 1, 1, 37);
        } else if ( itemName.equals("Dinosaur Egg")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 125, 3, FoodType.DINOSAUR_MAYONNAISE, 1, 1, 800);
        } else {
            return wrongItem(game);
        }
    }

    private   void loom(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Wool")) {
            artisanMiscHandle(player, game, backpack,  itemName, block, 0, 4, ElseType.CLOTH, 1, 1, 470);
        } else {

        }
    }

    private   Result artisanMiscHandle(Player player, Game game, BackPack backpack, String  itemName, ArtisanCell block, int energy, int hours, ElseType ElseType, int itemCount, int productCount, int price) {

        Loot slot = backpack.findItemLoot( itemName);
        if (slot == null || slot.getCount() < itemCount) {

            return new Result(false, "You don't have enough " +  itemName);
        }

        slot.setCount(slot.getCount() - itemCount);
        if (slot.getCount() == 0) {
            backpack.removeLoot(slot);
        }
        block.beingUsed = true;
        block.prepTime = game.getDate().plusHours(hours);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Else(Quality.DEFAULT, ElseType, price), productCount);

        return new Result(true, ElseType.name + " will be ready to collect in " + hours + " hours");
    }

    private     Result charCoalKiln(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Wood")) {
            return artisanForagingMineralHandle(player, game, backpack,  itemName, block, 0, 1, ForagingMineralType.COAL, 10, 1, 50);
        } else {

            return new Result(true, "wrong item selected");
        }
    }

    private     Result Dehydrator(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if (isMushroom( itemName)) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 50, Duration.between(game.getDate(), game.getDate().plusDays(1).with(LocalTime.of(0, 0))).toHoursPart(), FoodType.DRIED_MUSHROOMS, 5, 1, FoodType.getPrice( itemName) * 75 / 100 + 25);
        } else if (isFruit( itemName) && ! itemName.equals("Grapes")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 75, Duration.between(game.getDate(), game.getDate().plusDays(1).with(LocalTime.of(0, 0))).toHoursPart(), FoodType.DRIED_FRUIT, 5, 1, FoodType.getPrice( itemName) * 75 / 100 + 25);
        } else if ( itemName.equals("Grapes")) {
            return artisanFoodHandle(player, game, backpack,  itemName, block, 125, Duration.between(game.getDate(), game.getDate().plusDays(1).with(LocalTime.of(0, 0))).toHoursPart(), FoodType.RAISINS, 5, 1, 600);
        } else {

            return new Result(true, "wrong item selected");
        }
    }

    private   boolean isMushroom(String itemName) {
        return itemName.equals(FoodType.CHANTERELLE.name) || itemName.equals(FoodType.COMMON_MUSHROOM.name) ||
                itemName.equals(FoodType.MOREL.name) || itemName.equals(FoodType.PURPLE_MUSHROOM.name) ||
                itemName.equals(FoodType.RED_MUSHROOM.name);
    }

    private     Result keg(String  itemName, Player player, Game game, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Wheat"))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 50, 24, FoodType.BEER, 1, 1, FoodType.BEER.price);
        else if ( itemName.equals("Rice"))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 13, 10, FoodType.VINEGAR, 1, 1, FoodType.VINEGAR.price);
        else if ( itemName.equals("Coffee Bean"))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 75, 2, FoodType.COFFEE, 5, 1, FoodType.COFFEE.price);
        else if ( itemName.equals("Honey"))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 100, 10, FoodType.MEAD, 1, 1, 300);
        else if ( itemName.equals("Hops"))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 50, 72, FoodType.PALE_ALE, 1, 1, 300);
        else if (isFruit( itemName))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 7 * FoodType.getEnergy( itemName) / 4, 168, FoodType.WINE, 1, 1, FoodType.getPrice( itemName) * 3);
        else if (isVegetable( itemName))
            return artisanFoodHandle(player, game, backpack,  itemName, block, 2 * FoodType.getEnergy( itemName), 168, FoodType.JUICE, 1, 1, 9 * FoodType.getPrice( itemName) / 4);
        else {

            return new Result(true, "wrong item selected");
        }
    }

    private   boolean isFruit(String itemName) {
        return itemName.equals(FoodType.ANCIENT_FRUIT.name) || itemName.equals(FoodType.APPLE.name) ||
                itemName.equals(FoodType.APRICOT.name) || itemName.equals(FoodType.BANANA.name) ||
                itemName.equals(FoodType.BLACKBERRY.name) || itemName.equals(FoodType.CHERRY.name) ||
                itemName.equals(FoodType.CRANBERRIES.name) || itemName.equals(FoodType.CRYSTAL_FRUIT.name) ||
                itemName.equals(FoodType.GRAPE.name) || itemName.equals(FoodType.BLUEBERRY.name) ||
                itemName.equals(FoodType.HOT_PEPPER.name) || itemName.equals(FoodType.MANGO.name) ||
                itemName.equals(FoodType.MELON.name) || itemName.equals(FoodType.ORANGE.name) ||
                itemName.equals(FoodType.PEACH.name) || itemName.equals(FoodType.POMEGRANATE.name) ||
                itemName.equals(FoodType.POWDER_MELON.name) || itemName.equals(FoodType.RHUBARB.name) ||
                itemName.equals(FoodType.SALMON_BERRY.name) || itemName.equals(FoodType.SPICE_BERRY.name) ||
                itemName.equals(FoodType.STARFRUIT.name) || itemName.equals(FoodType.STRAWBERRY.name) ||
                itemName.equals(FoodType.WILD_PLUM.name)
                ;
    }

    private   boolean isVegetable(String itemName) {
        return itemName.equals(FoodType.AMARANTH.name) || itemName.equals(FoodType.ARTICHOKE.name) ||
                itemName.equals(FoodType.BEET.name) || itemName.equals(FoodType.BOK_CHOY.name) ||
                itemName.equals(FoodType.BROCCOLI.name) || itemName.equals(FoodType.CARROT.name) ||
                itemName.equals(FoodType.CAULIFLOWER.name) || itemName.equals(FoodType.CORN.name) ||
                itemName.equals(FoodType.EGGPLANT.name) || itemName.equals(FoodType.FIDDLE_HEAD_FERN.name) ||
                itemName.equals(FoodType.GARLIC.name) || itemName.equals(FoodType.GREEN_BEAN.name) ||
                itemName.equals(FoodType.HOPS.name) || itemName.equals(FoodType.KALE.name) ||
                itemName.equals(FoodType.PUMPKIN.name) || itemName.equals(FoodType.RADISH.name) ||
                itemName.equals(FoodType.RED_CABBAGE.name) || itemName.equals(FoodType.SUMMER_SQUASH.name) ||
                itemName.equals(FoodType.TOMATO.name) || itemName.equals(FoodType.UNMILLED_RICE.name) ||
                itemName.equals(FoodType.WHEAT.name) || itemName.equals(FoodType.YAM.name);
    }

    private     Result artisanForagingMineralHandle(Player player, Game game, BackPack backpack, String  itemName, ArtisanCell block, int energy, int hours, ForagingMineralType ForagingMineralType, int itemCount, int productCount, int price) {

        Loot loot = backpack.findItemLoot( itemName);
        if (loot == null || loot.getCount() < itemCount) {

            return new Result(false, "You don't have enough " +  itemName);
        }
        loot.setCount(loot.getCount() - itemCount);
        if (loot.getCount() == 0) {
            backpack.removeLoot(loot);
        }
        block.beingUsed = true;
        block.prepTime = game.getDate().plusHours(hours);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Mineral(Quality.DEFAULT, ForagingMineralType, price), productCount);

        return new Result(true, ForagingMineralType.name + " will be ready to collect in " + hours + " hours");
    }

    private     Result artisanFoodHandle(Player player, Game game, BackPack backpack, String  itemName, ArtisanCell block, int energy, int hours, FoodType FoodType, int itemCount, int productCount, int price) {

        Loot slot = backpack.findItemLoot( itemName);
        if (slot == null || slot.getCount() < itemCount) {

            return new Result(false, "You don't have enough " +  itemName);
        }
        slot.setCount(slot.getCount() - itemCount);
        if (slot.getCount() == 0) {
            backpack.removeLoot(slot);
        }
        block.beingUsed = true;
        block.prepTime = game.getDate().plusHours(hours);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType, price, energy), productCount);

        return new Result(true, FoodType.name + " will be ready to collect in " + hours + " hours");
    }

    private     Result artisanFoodHandleCoal(Player player, Game game, BackPack backpack, String  itemName, ArtisanCell block, int energy, int hours, FoodType FoodType, int itemCount, int productCount, int price) {

        Loot slot = backpack.findItemLoot( itemName);
        if (slot == null || slot.getCount() < itemCount) {

            return new Result(false, "You don't have enough " +  itemName);
        }
        Loot secondSlot = backpack.findItemLoot("Coal");
        if (secondSlot == null || secondSlot.getCount() < itemCount) {

            return new Result(false, "You don't have enough coal");
        }
        slot.setCount(slot.getCount() - itemCount);
        if (slot.getCount() == 0) {
            backpack.removeLoot(slot);
        }
        secondSlot.setCount(secondSlot.getCount() - itemCount);
        if (secondSlot.getCount() == 0) {
            backpack.removeLoot(secondSlot);
        }

        block.beingUsed = true;
        block.prepTime = game.getDate().plusHours(hours);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType, price, energy), productCount);

        return new Result(true, FoodType.name + " will be ready to collect in " + hours + " hours");
    }

    private   Result artisanMiscHandleCoal(Player player, Game game, BackPack backpack, String  itemName, ArtisanCell block, int energy, int hours, ElseType ElseType, int itemCount, int productCount, int price) {

        Loot loot = backpack.findItemLoot( itemName);
        if (loot == null || loot.getCount() < itemCount) {

            return new Result(false, "You don't have enough " +  itemName);
        }
        Loot secondSlot = backpack.findItemLoot("Coal");
        if (secondSlot == null || secondSlot.getCount() < 1) {

            return new Result(false, "You don't have enough coal");
        }
        loot.setCount(loot.getCount() - itemCount);
        if (loot.getCount() == 0) {
            backpack.removeLoot(loot);
        }
        secondSlot.setCount(secondSlot.getCount() - 1);
        if (secondSlot.getCount() == 0) {
            backpack.removeLoot(secondSlot);
        }

        block.beingUsed = true;
        block.prepTime = game.getDate().plusHours(hours);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Else(Quality.DEFAULT, ElseType, price), productCount);

        return new Result(true, ElseType.name + " will be ready to collect in " + hours + " hours");
    }

    private   Result cheesePress(String  itemName, Player player, Game game, ArtisanCell block, BackPack backpack) {
        if ( itemName.equals("Milk") ||  itemName.equals("Big Milk"))
            return cheeseHandle(player, game,  itemName, block, backpack);
        else if ( itemName.equals("Goat Milk") ||  itemName.equals("Big Goat Milk"))
            return goatCheeseHandle(player, game,  itemName, backpack, block);
        else {

            return new Result(true, "wrong item selected");
        }
    }

    private   Result goatCheeseHandle(Player player, Game game, String  itemName, BackPack backpack, ArtisanCell block) {
        if ( itemName.equals("Goat Milk")) {
            Loot loot = backpack.findItemLoot( itemName);
            if (loot == null) {

                return new Result(false, "You don't have enough Goat Milk");
            }
            loot.setCount(loot.getCount() - 1);
            if (loot.getCount() == 0) {
                backpack.removeLoot(loot);
            }
            block.beingUsed = true;
            block.prepTime = game.getDate().plusHours(3);

            if (block.prepTime.getDayOfMonth() >= 29) {
                block.prepTime = block.prepTime.minusDays(28);
                block.prepTime = block.prepTime.plusMonths(1);
            }

            block.canBeCollected = false;
            block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType.GOAT_CHEESE, 400), 1);

            return new Result(true, "goat cheese will be ready to collect in 3 hours");
        } else if ( itemName.equals("Big Goat Milk")) {
            Loot slot = backpack.findItemLoot( itemName);
            if (slot == null) {

                return new Result(false, "You don't have enough Big Goat Milk");
            }
            slot.setCount(slot.getCount() - 1);
            if (slot.getCount() == 0) {
                backpack.removeLoot(slot);
            }
            block.beingUsed = true;
            block.prepTime = game.getDate().plusHours(3);

            if (block.prepTime.getDayOfMonth() >= 29) {
                block.prepTime = block.prepTime.minusDays(28);
                block.prepTime = block.prepTime.plusMonths(1);
            }

            block.canBeCollected = false;
            block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType.GOAT_CHEESE, 600), 1);

            return new Result(true, "goat cheese will be ready to collect in 3 hours");
        } else {

            return new Result(true, "wrong item selected");
        }
    }

    private   Result honeyHandle(Player player, Game game, ArtisanCell block) {
        block.beingUsed = true;
        block.prepTime = game.getDate().plusDays(4);

        if (block.prepTime.getDayOfMonth() >= 29) {
            block.prepTime = block.prepTime.minusDays(28);
            block.prepTime = block.prepTime.plusMonths(1);
        }

        block.canBeCollected = false;
        block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType.HONEY), 1);

        return new Result(true, "honey will be ready to collect in 4 days");
    }

    private   Result cheeseHandle(Player player, Game game, String  itemName, ArtisanCell block, BackPack backpack) {
        if ( itemName.equals("Milk")) {
            Loot slot = backpack.findItemLoot( itemName);
            if (slot == null) {

                return new Result(false, "You don't have enough Milk");
            }
            slot.setCount(slot.getCount() - 1);
            if (slot.getCount() == 0) {
                backpack.removeLoot(slot);
            }
            block.beingUsed = true;
            block.prepTime = game.getDate().plusHours(3);

            if (block.prepTime.getDayOfMonth() >= 29) {
                block.prepTime = block.prepTime.minusDays(28);
                block.prepTime = block.prepTime.plusMonths(1);
            }

            block.canBeCollected = false;
            block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType.CHEESE, 230), 1);

            return new Result(true, "cheese will be ready to collect in 3 hours");
        } else if ( itemName.equals("Big Milk")) {
            Loot loot = backpack.findItemLoot( itemName);
            if (loot == null) {

                return new Result(false, "You don't have enough Big Milk");
            }
            loot.setCount(loot.getCount() - 1);
            if (loot.getCount() == 0) {
                backpack.removeLoot(loot);
            }
            block.beingUsed = true;
            block.prepTime = game.getDate().plusHours(3);

            if (block.prepTime.getDayOfMonth() >= 29) {
                block.prepTime = block.prepTime.minusDays(28);
                block.prepTime = block.prepTime.plusMonths(1);
            }

            block.canBeCollected = false;
            block.productSlot = new Loot(new Food(Quality.DEFAULT, FoodType.CHEESE, 345), 1);

            return new Result(true, "cheese will be ready to collect in 3 hours");
        } else {

            return new Result(true, "wrong item selected");
        }
    }

    public   Result ArtisanGet(String artisanName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Village village = game.getMap().getVillage();
        BackPack backpack = player.getInventory();
        ArtisanCell block = village.getArtisanCell(artisanName);
        if (block == null) {

            return new Result(true, "artisan not found");
        }
        if (!block.beingUsed) {

            return new Result(true, "no product found");
        }
        if (!block.canBeCollected) {

            return new Result(true, "The product is not ready for collection");
        }
        Loot slot = block.productSlot;
        Loot backPackSlot = backpack.findItemLoot(artisanName);
        if (backPackSlot == null) {
            if (backpack.getLoots().size() == backpack.getType().getCapacity()) {

                return new Result(true, "your backpack is full");
            }
            Loot slotToAdd = new Loot(slot.getItem(), slot.getCount());
            backpack.addLoot(slotToAdd);
            block.beingUsed = false;
            block.canBeCollected = false;
            block.productSlot = null;

            return new Result(true, "you have collected " + slotToAdd.getCount() + " of " + slotToAdd.getItem().getName());
        }
        backPackSlot.setCount(backPackSlot.getCount() + slot.getCount());
        int count = slot.getCount();
        String itemName = backPackSlot.getItem().getName();
        block.beingUsed = false;
        block.canBeCollected = false;
        block.productSlot = null;

        return new Result(true, "you have collected " + count + " of " + itemName);
    }



}
