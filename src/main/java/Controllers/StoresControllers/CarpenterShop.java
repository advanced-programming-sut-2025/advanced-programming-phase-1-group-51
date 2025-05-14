package Controllers.StoresControllers;

import Models.*;
import Models.Enums.MenuCommands.Menu;
import Models.Enums.Others.Quality;
import Models.Enums.Recipes.CookingRecipes;
import Models.Enums.Recipes.CraftingRecipes;
import Models.Enums.Types.ItemTypes.*;
import Models.Enums.Types.ObjectsOnMapType.CropType;
import Models.Enums.Types.ObjectsOnMapType.TreeType;
import Models.Enums.Types.StoresProductsTypes.BlackSmithProducts;
import Models.Enums.Types.StoresProductsTypes.FishShopProducts;
import Models.Enums.Types.TrashcanType;
import Models.Items.*;

import static Controllers.StoresControllers.BlackSmithShop.toolUpgrade;

public class CarpenterShop {

    public Result ShowAllProducts() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Store store = game.getMap().getVillage().getStore("Blacksmith");

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
        Store store = game.getMap().getVillage().getStore("Blacksmith");

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

    public Result Purchase(String productName, int count) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Store store = game.getMap().getVillage().getStore("Blacksmith");
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

        ItemType type = product.getType().getItemType();

        if (type == null && product.getType().getIngredient() == null) {
            CraftingRecipes craft = CraftingRecipes.getCraftingRecipe(productName.split(" ")[0]);
            CookingRecipes cook = CookingRecipes.getCookingRecipe(productName.split(" ")[0]);
            if (craft != null) {
                player.getCraftingRecipes().add(craft);
                return new Result(true, productName + " successfully added to recipes");
            }
            if (cook != null) {
                player.getCookingRecipes().add(cook);
                return new Result(true, productName + " successfully added to recipes");
            }
            return new Result(false, "Recipe not found");

        }
        else if (type == null && product.getType().getIngredient() != null) {
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
        }
        else {
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
            product.setRemainingCount(product.getRemainingCount() - count);
            player.setMoney((int) (player.getMoney() - product.getType().getProductPrice(game.getSeason()) * count));
            return new Result(true, "You have purchased " + count + " of " + productName);
        }
    }



    public static Result toolUpgrade(String name, StoreProduct p, Player player) {
        BlackSmithProducts trashCan = BlackSmithProducts.findTrashCanUpgrade(name);
        BlackSmithProducts tool = BlackSmithProducts.findSteelToolUpgrade(name);
        if (trashCan != null) {
            TrashcanType type = trashCan.getTrashcan();
            player.setTrashcanType(type);
            return new Result(true, "Trashcan successfully updated");
        }
        else if (tool != null) {
            Quality q = tool.getTool();
            if (player.getItemInHand() instanceof Tool t) {
                t.setQuality(q);
                return new Result(true, "Tool successfully updated");
            }
            else {
                return new Result(false, "Your equipped item must be a tool");
            }
        }
        return new Result(false, "Upgrade option not found");
    }

    public Result exitStore() {
        String name = App.getCurrentMenu().name();
        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "You leaved " + name);
    }
}
