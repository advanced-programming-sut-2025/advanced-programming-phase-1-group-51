package Controllers.Activity;

import Controllers.BaseController;
import Models.*;
import Models.Enums.Others.Weather;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Items.Else;
import Models.Items.Item;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.AnimalCell;
import Models.ObjectsShownOnMap.BurntCell;
import java.util.ArrayList;

public class Husbandry extends BaseController {



    public Result Pet(String animalName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimal(animalName);
        if (animal == null) {
            return  Result.failure( "Animal was not found");
        }
        animal.setHasBeenPetToday(true);
        animal.setFriendshipLevel(animal.getFriendshipLevel() + 15);
        return saveGameState(game)
                .combine(Result.success( "you have pet " + animalName));
    }



    public Result animals() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (player.getAnimals().isEmpty()) {
            return  Result.failure( "you don't ave any animal");
        }

        StringBuilder String = new StringBuilder();

        for (Animal animal : player.getAnimals()) {
            String.append("Animal name: ").append(animal.getName()).append("\n").
                    append("Friendship level: ").append(animal.getFriendshipLevel()).append("\n").
                    append("hasBeenPetToDay: ").append(animal.isHasBeenPetToday()).append("\n").
                    append("hasBeenFedToDay: ").append(animal.isHasBeenFedHayToday() || animal.isHasBeenFedGrassToday()).append("\n").
                    append("----------------------------------------").append("\n");
        }
        return saveGameState(game)
                .combine(Result.success( String.toString()));
    }

    public Result ShepherdAnimals(String animalName, int x, int y) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = game.getCurrentPlayer().getCurrentFarm(game);
        Animal animal = player.findAnimal(animalName);
        Cells cell = farm.findCellFarm(x, y);


        if(animal == null){
            return  Result.failure( "animal not found");
        }

       else if (cell == null) {
            return  Result.failure( "cell not found");
        }
        else if(!(cell.getObjectOnCell() instanceof BurntCell)){
            return  Result.failure( "the chosen cell not empty");
        }

        if (game.getWeather() == Weather.SNOW || game.getWeather() == Weather.STORM || game.getWeather() == Weather.RAIN) {
            return saveGameState(game)
                .combine(Result.success( "you can't shepherd in this weather"));
        }

        cell.setObjectOnCell(new AnimalCell(animal));
        animal.setHasBeenFedGrassToday(true);
        animal.setFriendshipLevel(animal.getFriendshipLevel() + 8);
        return saveGameState(game)
                .combine(Result.success( "you have shepherd " + animalName));
    }

    public Result FeedHay(String animalName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimal(animalName);

        BackPack BackPack = player.getInventory();
        Loot loot = null;

        if (animal == null) {
            return  Result.failure( "Animal not found");
        }

        for (Loot Loot : BackPack.getLoots()) {
            if (Loot.getItem().getName().equals(ElseType.HAY.name))
                loot = Loot;
        }

        if (loot == null) {
            return  Result.failure( "You don't have any hay to feed the animals");
        }

        loot.setCount(loot.getCount() - 1);

        if (loot.getCount() == 0) {
            BackPack.removeLoot(loot);
        }
        animal.setHasBeenFedHayToday(true);

        return saveGameState(game)
                .combine(Result.success( "You fed " + animalName));
    }

    public Result Produces() {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        ArrayList<Animal> allAnimals = game.getCurrentPlayer().getAnimals();
        ArrayList<Animal> notCollectedAnimals = new ArrayList<>();

        for (Animal animal : allAnimals) {
            if (animal.getProduct() != null) {
                notCollectedAnimals.add(animal);
            }
        }
        if (notCollectedAnimals.isEmpty()) {
            return  Result.failure( "No products found");
        }
        StringBuilder animalString = new StringBuilder();
        for (Animal animal : notCollectedAnimals) {
            animalString.append("name: ").append(animal.getName())
                    .append("product: ").append(animal.getProduct().getName()).append(" ").
                    append(animal.getProduct().getQuality()).append("\n");
        }
        return saveGameState(game)
                .combine(Result.success( animalString.toString()));
    }

    public Result CollectProduce(String animalName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimal(animalName);
        BackPack BackPack = player.getInventory();
        Loot productLoot = null;

        if (animal == null) {
            return  Result.failure( "no animal found");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return  Result.failure( "no product found");
        }



        Item item = new Else(((Else) product).getElseType(), ((Else) product).getQuality());
        for (Loot Loot : BackPack.getLoots()) {
            if (Loot.getItem().getName().equals(product.getName())) {
                productLoot = Loot;
            }
        }


        if (productLoot == null) {
            if (BackPack.getLoots().size() == BackPack.getType().getCapacity()) {
                return  Result.failure( "your BackPack is full");
            }
            Loot newLoot = new Loot(item, animal.getType().productPerDay);

            BackPack.addLoot(newLoot);
            animal.setHasBeenCollected(true);
            animal.setProduct(null);
            return saveGameState(game)
                .combine(Result.success( "you have collected " + animal.getType().productPerDay + " of " + product.getName()));
        }

        productLoot.setCount(productLoot.getCount() + animal.getType().productPerDay);
        animal.setHasBeenCollected(true);
        animal.setProduct(null);

        return saveGameState(game)
                .combine(Result.success( "you have collected " + animal.getType().productPerDay + " of " + product.getName()));
    }


    public Result SellAnimal(String animalName) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimal(animalName);

        if (animal == null) {
            return  Result.failure( "no animal found");
        }

        int price = (int) (animal.getType().price * ((double) animal.getFriendshipLevel() / 1000 + 0.3));
        player.setMoney(player.getMoney() + price);
        player.getAnimals().remove(animal);
        return saveGameState(game)
                .combine(Result.success( "you have sold " + animalName + " for " + price));
    }

    public Result cheatSetFriendship(String animalName, int amount) {
        User user = App.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimal(animalName);

        animal.setFriendshipLevel(amount);
        return saveGameState(game)
                .combine(Result.success( "Friendship level  with " + animalName + "has set to " + amount));
    }

}
