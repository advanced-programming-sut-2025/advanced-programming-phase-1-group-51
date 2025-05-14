package Controllers.StoresControllers;

import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.StoresProductsTypes.FishShopProducts;
import Models.Items.*;

import static Controllers.StoresControllers.BlackSmithShop.toolUpgrade;

public class FishShop {

    public Result ShowAllProducts() {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Store store = Game.getMap().getVillage().getStore("Fish Shop");

        StringBuilder output = new StringBuilder();

        for (StoreProduct product : store.getProducts()) {
            output.append("name : ").append(product.getType().getName()).append("\n");
            output.append("price : ");
            if(product.getType().isInSeason(Game.getSeason())){
                output.append(product.getType().getPrice());
            }else {
                output.append(product.getType().getOutOfSeasonPrice());
            }
            output.append("\n");
        }
        return new Result(true, output.toString());
    }

    public Result ShowAllAvailableProducts() {
        Game game = Game.getCurrentUser().getCurrentGame();
        Store store = Game.getMap().getVillage().getStore("Fish Shop");

        StringBuilder output = new StringBuilder();

        for (StoreProduct product : store.getProducts()) {
            if(product.getRemainingCount() > 0){
                output.append("name :").append(product.getType().getName()).append("\n");
                output.append("Remaining count : ").append(product.getRemainingCount()).append("\n");
                output.append("price : ");
                if(product.getType().isInSeason(Game.getSeason())){
                    output.append(product.getType().getPrice());
                }else {
                    output.append(product.getType().getOutOfSeasonPrice());
                }
                output.append("\n");
            }
        }

        return new Result(true, output.toString());
    }

    public Result Purchase(String productName, int count) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();

        Store store = Game.getMap().getVillage().getStore("Fish Shop");
        StoreProduct product = store.getProduct(productName);

        if (product == null) {
            return new Result(false, "Store doesn't have this product");
        }

        if (product.getRemainingCount() < count) {
            return new Result(false, "Not enough available products");
        }

        if (product.getType().getProductPrice(Game.getSeason()) * count > player.getMoney()) {
            return new Result(false, "Not enough money");
        }

        product.setRemainingCount(product.getRemainingCount() - count);
        ItemType type = product.getType().getItemType();

        if (type == null && product.getType().getIngredient() == null) {
            Result res = handleBuyRecipe(productName, player);
            if (res.isSuccessful()) {
                player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(Game.getSeason()) * count));
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
                player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(Game.getSeason())));
            }
            return res;
        } else {
            Item item = null;
            if (type instanceof FoodType) {
                item = new Food((FoodType) type);
            } else if (type instanceof CropSeedsType) {
                item = new CropSeed((CropSeedsType) type);
            } else if (type instanceof TreeSeedsType) {
                item = new TreeSeed((TreeSeedsType) type);
            } else if (type instanceof FishType) {
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
                item = new Tool(q, (ToolType) type, (int) product.getType().getProductPrice(Game.getSeason()));
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
        String name = Game.getCurrentMenu().name();
        Game.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You leaved " + name);
    }
}
