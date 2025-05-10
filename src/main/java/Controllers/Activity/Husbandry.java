package Controllers.Activity;

import Controllers.Controller;
import Models.*;
import Models.Enums.Others.Weather;
import Models.Enums.Types.AnimalType;
import Models.Enums.Types.ItemTypes.ElseType;
import Models.Items.Else;
import Models.Items.Item;
import Models.Maps.Cells;
import Models.Maps.Farm;
import Models.ObjectsShownOnMap.AnimalCell;
import Models.ObjectsShownOnMap.BurntCell;

import java.util.ArrayList;

public class Husbandry extends Controller {


    public Result BuyAnimal(String animalType, String animalName) {
        return null;
    }

    public Result Pet(String animalName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Animal was not found");
        }
        animal.setHasBeenPetToday(true);
        animal.setFriendshipLevel(animal.getFriendshipLevel() + 15);
        return new Result(true, "you have pet " + animalName);
    }

    public Result animals() {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();

        if (player.getAnimals().isEmpty()) {
            return new Result(false, "you don't ave any animal");
        }

        StringBuilder String = new StringBuilder();

        for (Animal animal : player.getAnimals()) {
            String.append("Animal name: ").
                    append(animal.getName()).append("\n").
                    append("Friendship level: ").append(animal.getFriendshipLevel()).append("\n").
                    append("hasBeenPetToDay: ").append(animal.isHasBeenPetToday()).append("\n").
                    append("hasBeenFedToDay: ").append(animal.isHasBeenFedHayToday() || animal.isHasBeenFedGrassToday()).append("\n").
                    append("----------------------------------------").append("\n");
        }
        return new Result(true, String.toString());
    }

    public Result ShepherdAnimals(String animalName, int x, int y) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Farm farm = Game.getCurrentPlayer().getCurrentFarm(game);
        Animal animal = player.findAnimalByName(animalName);
        Cells cell = farm.findCell(x, y);

        if (cell == null || !(cell.getObjectOnCell() instanceof BurntCell) || animal == null) {
            return new Result(false, "cell not found or not empty or no animal found");
        }

        if (game.getWeather() == Weather.SNOW || game.getWeather() == Weather.STORM || game.getWeather() == Weather.RAIN) {
            return new Result(true, "bad weather quality");
        }

        cell.setObjectOnCell(new AnimalCell(animal));
        animal.setHasBeenFedGrassToday(true);
        animal.setFriendshipLevel(animal.getFriendshipLevel() + 8);
        return new Result(true, "you have shepherd " + animalName);
    }

    public Result FeedHay(String animalName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Animal animal = player.findAnimalByName(animalName);

        BackPack BackPack = player.getInventory();
        Loot hayLoot = null;

        if (animal == null) {
            return new Result(false, "Animal not found");
        }

        for (Loot Loot : BackPack.getLoots()) {
            if (Loot.getItem().getName().equals(ElseType.HAY.name))
                hayLoot = Loot;
        }

        if (hayLoot == null) {
            return new Result(false, "You don't have any hay to feed the animals");
        }

        hayLoot.setCount(hayLoot.getCount() - 1);

        if (hayLoot.getCount() == 0) {
            BackPack.removeLoot(hayLoot);
        }
        animal.setHasBeenFedHayToday(true);

        return new Result(true, "You fed " + animalName);
    }

    public Result Produces() {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        ArrayList<Animal> allAnimals = game.getCurrentPlayer().getAnimals();
        ArrayList<Animal> notCollectedAnimals = new ArrayList<>();

        for (Animal animal : allAnimals) {
            if (animal.getProduct() != null) {
                notCollectedAnimals.add(animal);
            }
        }
        if (notCollectedAnimals.isEmpty()) {
            return new Result(false, "No products found");
        }
        StringBuilder animalString = new StringBuilder();
        for (Animal animal : notCollectedAnimals) {
            animalString.append("name: ").append(animal.getName())
                    .append("product: ").append(animal.getProduct().getName()).append(" ").
                    append(animal.getProduct().getQuality()).append("\n");
        }
        return new Result(true, animalString.toString());
    }

    public Result CollectProduce(String animalName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimalByName(animalName);
        BackPack BackPack = player.getInventory();
        Loot productLoot = null;


        if (animal == null) {
            return new Result(false, "no animal found");
        }
        Item product = animal.getProduct();
        if (product == null) {
            return new Result(false, "no product found");
        }
        return CollectProducts(product, BackPack, productLoot, animal, player, game);
    }


    public static Result CollectProducts(Item product, BackPack BackPack, Loot productLoot, Animal animal, Player player, Game game) {
        Item itemInHand = player.getItemInHand();
        Item item = new Else(((Else) product).getElseType(), ((Else) product).getQuality());
        for (Loot Loot : BackPack.getLoots()) {
            if (Loot.getItem().getName().equals(product.getName())) {
                productLoot = Loot;
            }
        }

        // if loot doesn't already exist make a new loot
        if (productLoot == null) {
            if (BackPack.getLoots().size() == BackPack.getType().getCapacity()) {
                return new Result(false, "your BackPack is full");
            }
            Loot newLoot = new Loot(item, animal.getType().productPerDay);
            BackPack.addLoot(newLoot);
            if(animal.getType().equals(AnimalType.SHEEP) ||animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT))
                animal.setHasBeenCollected(true);

            return new Result(true, "you have collected " + animal.getType().productPerDay + " of " + product.getName());
        }

        // add to existing Loot
        productLoot.setCount(productLoot.getCount() + animal.getType().productPerDay);
        if(animal.getType().equals(AnimalType.SHEEP) || animal.getType().equals(AnimalType.COW) || animal.getType().equals(AnimalType.GOAT)){
            animal.setHasBeenCollected(true);
        }
        else {
            animal.setHasBeenCollected(true);
            animal.getProduct() = null;
        }

        return new Result(true, "you have collected " + animal.getType().productPerDay + " of " + product.getName());
    }


    public Result SellAnimal(String animalName) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Animal animal = player.findAnimalByName(animalName);

        if (animal == null) {
            return new Result(false, "no animal found");
        }

        int price = (int) (animal.getType().price * ((double) animal.getFriendshipLevel() / 1000 + 0.3));
        player.setMoney(player.getMoney() + price);
        player.getAnimals().remove(animal);
        return new Result(true, "you have sold " + animalName + " for " + price);
    }

    public Result fishing(String fishingPole) {
        return null;

    }

    public Result cheatSetFriendship(String animalName, int amount) {
        User user = Game.getCurrentUser();
        Game game = user.getCurrentGame();
        Player player = Game.getCurrentPlayer();
        Animal animal = player.findAnimalByName(animalName);

        animal.setFriendshipLevel(amount);
        return new Result(true, "Friendship level  with " + animalName + "has set to " + amount);
    }
}
