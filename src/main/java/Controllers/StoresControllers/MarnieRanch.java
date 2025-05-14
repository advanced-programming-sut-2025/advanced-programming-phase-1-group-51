package Controllers.StoresControllers;

import Models.*;
import Models.Buildings.Barn;
import Models.Buildings.Building;
import Models.Buildings.Coop;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Enums.Types.AnimalType;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.CropType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Enums.Types.StoresProductsTypes.FishShopProducts;
import Models.Items.*;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.AnimalCell;

import static Controllers.StoresControllers.BlackSmithShop.toolUpgrade;

public class MarnieRanch {

    public Result ShowAllProducts() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Store store = game.getMap().getVillage().getStore("Marnie's Ranch");

        StringBuilder output = new StringBuilder();

        for (StoreProduct product : store.getProducts()) {
            output.append("name : ").append(product.getType().getName()).append("\n");
            output.append("price : ");
            if(product.getType().isInSeason(game.getSeason())){
                output.append(product.getType().getPrice());
            }else {
                output.append(product.getType().getOutOfSeasonPrice());
            }
            output.append("\n");
        }
        return new Result(true, output.toString());
    }

    public Result ShowAllAvailableProducts() {
        Game game = App.getCurrentUser().getCurrentGame();
        Store store = game.getMap().getVillage().getStore("Marnie's Ranch");

        StringBuilder output = new StringBuilder();

        for (StoreProduct product : store.getProducts()) {
            if(product.getRemainingCount() > 0){
                output.append("name :").append(product.getType().getName()).append("\n");
                output.append("Remaining count : ").append(product.getRemainingCount()).append("\n");
                output.append("price : ");
                if(product.getType().isInSeason(game.getSeason())){
                    output.append(product.getType().getPrice());
                }else {
                    output.append(product.getType().getOutOfSeasonPrice());
                }
                output.append("\n");
            }
        }

        return new Result(true, output.toString());
    }



    public static Result BuyAnimal(String animalType, String animalName) {

        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = player.getFarm();


        AnimalType type;
        int price;
        if (animalType.equals("Hen")) {
            type = AnimalType.HEN;
            price = 800;
        }
        else if (animalType.equals("Cow")) {
            type = AnimalType.COW;
            price = 1500;
        }
        else if (animalType.equals("Goat")) {
            type = AnimalType.GOAT;
            price = 4000;
        }
        else if (animalType.equals("Duck")) {
            type = AnimalType.DUCK;
            price = 1200;
        }
        else if (animalType.equals("Sheep")) {
            type = AnimalType.SHEEP;
            price = 8000;
        }
        else if (animalType.equals("Rabbit")) {
            type = AnimalType.RABBIT;
            price = 8000;
        }
        else if (animalType.equals("Dinosaur")) {
            type = AnimalType.DINOSAUR;
            price = 14000;
        }
        else if (animalType.equals("Pig")) {
            type = AnimalType.PIG;
            price = 16000;
        }
        else {
            return new Result(false, "Invalid animal name");
        }

        Animal animal = new Animal(price, animalName, type);

        boolean repeatedName = false;
        for (Building b : farm.getBuildings()) {
            if (b instanceof Coop) {
                for (Animal a : ((Coop) b).animals) {
                    if (a.getName().equals(animalName)) {
                        repeatedName = true;
                    }
                }
            }
            else if (b instanceof Barn) {
                for (Animal a : ((Barn) b).animals) {
                    if (a.getName().equals(animalName)) {
                        repeatedName = true;
                    }
                }
            }
        }

        if (repeatedName) {
            return new Result(false, "Name taken before");
        }

        if (player.getMoney() < price) {
            return new Result(false, "You don't have enough money");
        }

        Store store = game.getMap().getVillage().getStore("Marnie's Ranch");
        StoreProduct storeProduct = store.getProduct(animalType);
        if (storeProduct.getRemainingCount() <= 0) {
            return new Result(false, "this animal is not available now!");
        }

        if (animalType.equals("Hen")) {
            return buyHen(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Duck")) {
            return buyDuck(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Rabbit")) {
            return buyRabbit(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Dinosaur")) {
            return buyDinosaur(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Cow")) {
            return buyCow(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Goat")) {
            return buyGoat(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Sheep")) {
            return buySheep(farm, player, price, animal, animalName, storeProduct);
        }
        else if (animalType.equals("Pig")) {
            return buyPig(farm, player, price, animal, animalName, storeProduct);
        }
        return new Result(false, "invalid animal");
    }

    // Coop Animals

    private static Result buyHen(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Coop) {
                if (((Coop) building).animals.size() < ((Coop) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Coop) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);
                   
                    return new Result(true, "you have bought " + name);
                }
            }
        }
       
        return new Result(false, "you need to build another Coop");
    }

    private static Result buyDuck(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Coop && (((Coop) building).coopType.equals("Big Coop") || ((Coop) building).coopType.equals("Deluxe Coop"))) {
                if (((Coop) building).animals.size() < ((Coop) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Coop) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Big Coop");
    }

    private static Result buyRabbit(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Coop && ((Coop) building).coopType.equals("Deluxe Coop")) {
                if (((Coop) building).animals.size() < ((Coop) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Coop) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Deluxe Coop");
    }


    private static Result buyDinosaur(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Coop && (((Coop) building).coopType.equals("Big Coop") || ((Coop) building).coopType.equals("Deluxe Coop"))) {
                if (((Coop) building).animals.size() < ((Coop) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Coop) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Big Coop");
    }


    // Barn Animals

    private static Result buyCow(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Barn) {
                if (((Barn) building).animals.size() < ((Barn) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Barn) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Barn");
    }

    private static Result buyGoat(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Barn && (((Barn) building).barnType.equals("Big Barn") || ((Barn) building).barnType.equals("Deluxe Barn"))) {
                if (((Barn) building).animals.size() < ((Barn) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Barn) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Big Barn");
    }

    private static Result buySheep(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Barn && ((Barn) building).barnType.equals("Deluxe Barn")) {
                if (((Barn) building).animals.size() < ((Barn) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Barn) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);

                    return new Result(true, "you have bought " + name);
                }
            }
        }

        return new Result(false, "you need to build another Deluxe Barn");
    }


    private static Result buyPig(Farm farm, Player player, int cost, Animal animal, String name, StoreProduct storeProduct) {
        for (Building building : farm.getBuildings()) {
            if (building instanceof Barn && ((Barn) building).barnType.equals("Deluxe Barn")) {
                if (((Barn) building).animals.size() < ((Barn) building).capacity) {
                    player.setMoney(player.getMoney() - cost);
                    ((Barn) building).animals.add(animal);
                    player.getAnimals().add(animal);
                    addAnimalToBuilding(animal, building);
                    storeProduct.setRemainingCount(storeProduct.getRemainingCount() - 1);
                   
                    return new Result(true, "you have bought " + name);
                }
            }
        }
       
        return new Result(false, "you need to build another Deluxe Barn");
    }

    


    private static void addAnimalToBuilding(Animal animal, Building building) {
        for(Cells cell : building.buildingCells){
            if(!(cell.getObjectOnCell() instanceof AnimalCell)){
                cell.setObjectOnCell(new AnimalCell(animal));
                break;
            }
        }
    }


    public Result Purchase(String productName, int count) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        Store store = game.getMap().getVillage().getStore("Marnie's Ranch");

        StoreProduct product = store.getProduct(productName);

        if (product == null) {
            return new Result(false, "Store doesn't have this product");
        }

        if (product.getRemainingCount() < count) {
            return new Result(false, "Not enough available products");
        }

        if (product.getType().getProductPrice(game.getSeason()) * count > player.getMoney()) {
            return new Result(false, "Not enough money");
        }

        product.setRemainingCount(product.getRemainingCount() - count);
        ItemType type = product.getType().getItemType();

        if (type == null && product.getType().getIngredient() == null) {
            Result res = handleBuyRecipe(productName, player);
            if (res.isSuccessful()) {
                player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(game.getSeason()) * count));
            }
            return res;
        } else if (type == null && product.getType().getIngredient() != null) {
            Result res = toolUpgrade(productName, product, player);
            if (res.isSuccessful()) {
                Loot Loot = player.getInventory().findItemLoot(product.getType().getIngredient().getName());
                if (Loot == null || Loot.getCount() < 5) {
                    return new Result(false, "you don't have enough ingredients");
                }
                Loot.setCount(Loot.getCount() - 5);
                if (Loot.getCount() == 0) {
                    player.getInventory().removeLoot(Loot);
                }
                player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(game.getSeason())));
            }
            return res;
        } else {
            Item item = null;
            if (type instanceof FoodType) {
                item = new Food((FoodType) type);
            } else if (type instanceof CropType) {
                item = new CropSeed((CropType) type);
            }
//            else if (type instanceof TreeType) {
//                item = new TreeSeed((TreeType) type);
//            }
            else if (type instanceof FishType) {
                item = new Fish(Quality.DEFAULT, (FishType) type);
            } else if (type instanceof ElseType) {
                item = new Else((ElseType) type);
            } else if (type instanceof ForagingMineralType) {
                item = new Mineral(Quality.DEFAULT, (ForagingMineralType) type);
            } else if (type instanceof ToolType) {
                Quality q = Quality.DEFAULT;
                if (product.getType().getName().equals(FishShopProducts.BAMBOO_POLE.getName())) {
                    q = Quality.SILVER;
                } else if (product.getType().getName().equals(FishShopProducts.TRAINING_ROD.getName())) {
                    q = Quality.COPPER;
                } else if (product.getType().getName().equals(FishShopProducts.FIBERGLASS_ROD.getName())) {
                    q = Quality.GOLD;
                } else if (product.getType().getName().equals(FishShopProducts.IRIDIUM_ROD.getName())) {
                    q = Quality.IRIDIUM;
                }
                item = new Tool(q, (ToolType) type, (int) product.getType().getProductPrice(game.getSeason()));
            }
            if (item == null) {
                return new Result(false, "No such item");
            }
            BackPack backpack = player.getInventory();
            Loot loot = backpack.findItemLoot(item.getName());
            if (loot == null) {
                if (backpack.getLoots().size() == backpack.getType().getCapacity()) {
                    return new Result(false, "You don't have enough space in your backpack.");
                }
                Loot newLoot = new Loot(item, count);
                backpack.addLoot(newLoot);
            } else {
                loot.setCount(loot.getCount() + 1);
            }

            player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(game.getSeason()) * count));
            return new Result(true, "You have purchased " + count + " of " + productName);
        }
    }

    public static Result handleBuyRecipe(String name, Player player) {
        CraftingRecipes craft = CraftingRecipes.getCraftingRecipe(name.split(" ")[0]);
        CookingRecipes cook = CookingRecipes.getCookingRecipe(name.split(" ")[0]);
        if (craft != null) {
            player.getCraftingRecipes().add(craft);
            return new Result(true, name + " successfully added to recipes");
        }
        if (cook != null) {
            player.getCookingRecipes().add(cook);
            return new Result(true, name + " successfully added to recipes");
        }
        return new Result(false, "Recipe not found");
    }


    public Result exitStore() {
        String name = App.getCurrentMenu().name();
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You leaved " + name);
    }
}
