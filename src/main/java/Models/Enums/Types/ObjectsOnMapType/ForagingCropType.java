package Models.Enums.Types.ObjectsOnMapType;

import Models.Enums.Others.Season;

public enum ForagingCropType {

    Blue_JAZZ("Blue Jazz", "Jazz Seeds", true, -1, 50, true, 45, new Season[]{Season.SPRING}, false, 1, 2 , 2, 2, -1, 7),
    CARROT("Carrot", "Carrot Seeds",true, -1, 35,  true, 75,new Season[]{Season.SPRING},true,1,1,1,-1,-1,3),
    CAULIFLOWER("Cauliflower", "Cauliflower Seeds",true,-1, 175,true,75, new Season[]{Season.SPRING},false,1,2,4,4,1,12),
    COFFEE_BEAN("Coffee Bean", "Coffee Bean",false,2, 15,false,-1,new Season[]{Season.SPRING, Season.SUMMER}, false,1,2,2,3,2,10),
    GARLIC("Garlic", "Garlic Seeds",true,-1,  60,true, 20,new Season[]{Season.SPRING},false,1,1,1,1,-1,4),
    GREEN_BEAN("Green Bean", "Bean Starter",false,3, 40,true, 25,new Season[]{Season.SPRING},false,1,1,1,3,4,10),
    KALE("Kale", "Kale Seeds",true,  -1,110,true,50, new Season[]{Season.SPRING},false,1,2,2,1,-1,6),
    PARSNIP("Parsnip", "Parsnip Seeds",true, -1, 35,true, 25,new Season[]{Season.SPRING},false,1,1,1,1,-1,4),
    POTATO("Potato", "Potato Seeds",true,  -1,80, true,25, new Season[]{Season.SPRING},false,1,1,1,2,1,6),
    RHUBARB("Rhubarb", "Rhubarb Seeds",true, -1, 220,false,-1,new Season[]{Season.SPRING},false,2,2,2,3,4,13),
    STRAWBERRY("Strawberry", "Strawberry Seeds",false,  4,120,true, 50,new Season[]{Season.SPRING},false,1,1,2,2,2,8),
    TULIP("Tulip", "Tulip Bulb",true, -1, 30,true, 45,new Season[]{Season.SPRING},false,1,1,2,2,-1,6),
    UNMILLED_RICE("Unmilled Rice", "Rice Shoot",true, -1,30,true, 3,new Season[]{Season.SPRING}, false,1,2,2,3,-1,8),
    BLUEBERRY("BlueBerry", "BlueBerry Seeds",false, 4,50,true, 25,new Season[]{Season.SUMMER},false,1,3,3,4,2,13),
    CORN("Corn", "Corn Seeds", false,4,50,true, 25,new Season[]{Season.SUMMER, Season.FALL},false,2,3,3,3,3,14),
    HOPS("Hops", "Hops Starter", false,1,25,true, 45,new Season[]{Season.SUMMER},false,1,1,2,3,4, 11),
    HOT_PEPPER("Hot Pepper", "Pepper Seeds", false,3,40,true,13, new Season[]{Season.SUMMER},false,1,1,1,1,1,5),
    MELON("Melon", "Melon Seeds",true, -1, 250,true, 113,new Season[]{Season.SUMMER},true,1,2,3,3,3,12),
    POPPY("Poppy", "Poppy Seeds",true, -1, 140,true,45,new Season[]{Season.SUMMER},false,1,2,2,2,-1,7 ),
    RADISH("Radish", "Radish Seeds", true, -1,90,true,45, new Season[]{Season.SUMMER},false,2,1,2,1,-1,6),
    RED_CABBAGE("Red Cabbage", "Red Cabbage Seeds",true,-1,  260,true,75, new Season[]{Season.SUMMER},false,2,1,2,2,2,9),
    STARFRUIT("Starfruit", "Starfruit Seeds", true,-1, 750,true,125, new Season[]{Season.SUMMER},false,2,3,2,3,3,13),
    SUMMER_SPANGLE("Summer Spangle", "Spangle Seeds", true, -1,90,true, 45,new Season[]{Season.SUMMER}, false,1,2,3,1,-1, 8),
    SUMMER_SQUASH("Summer Squash", "Summer Squash Seeds",false,3, 45,true,63,new Season[]{Season.SUMMER},false, 1,1,1,2,1, 6),
    SUNFLOWER("Sunflower", "Sunflower Seeds",true, -1, 80,true, 45,new Season[]{Season.SUMMER, Season.FALL},false,1,2,3,2,-1,8),
    TOMATO("Tomato", "Tomato Seeds",false,4, 60,true, 20,new Season[]{Season.SUMMER},false,2,2,2,2,3,11),
    WHEAT("Wheat", "Wheat Seeds",true,  -1,25,false,-1,new Season[]{Season.SUMMER, Season.FALL},false,1,1,1,1,-1,4),
    AMARANTH("Amaranth", "Amaranth Seeds",true,  -1,150,true, 50,new Season[]{Season.FALL},false,1,2,2,2,-1, 7),
    ARTICHOKE("Artichoke", "Artichoke Seeds",true, -1, 160,true, 30,new Season[]{Season.FALL},false,2,2,1,2,1,8),
    BEET("Beet", "Beet Seeds",true, -1, 100,true, 30,new Season[]{Season.FALL},false,1,1,2,2,-2,6),
    BOK_CHOY("Bok Choy", "Bok Choy Seeds", true, -1,80,true, 25,new Season[]{Season.FALL},false,1,1,1,1,-1,4),
    BROCCOLI("Broccoli", "Broccoli Seeds", false,4,70,true,63,new Season[]{Season.FALL}, false,2,2,2,2,-1,8),
    CRANBERRIES("Cranberries", "Cranberry seeds", false,5,75,true,38,new Season[]{Season.FALL},false,1,2,1,1,2,7 ),
    EGGPLANT("Eggplant", "Eggplant Seeds", false,5,60,true, 20,new Season[]{Season.FALL},false,1,1,1,1,1,5),
    FAIRY_ROSE("Fairy Rose", "Fairy Seeds",true, -1, 290,true, 45,new Season[]{Season.FALL},false,1,4,4,3,-1,12),
    GRAPE("Grape", "Grape Starter", false,3,80,true, 38,new Season[]{Season.FALL},false,1,1,2,3,3,10),
    PUMPKIN("Pumpkin", "Pumpkin Seeds",true, -1, 320,false,-1,new Season[]{Season.FALL},true,1,2,3,4,3,13),
    YAM("Yam", "Yam Seeds",true, -1, 160,true, 45,new Season[]{Season.FALL},false,1,3,3,3,-1, 10),
    SWEET_GEM_BERRY("Sweet Gem Berry", "Rare Seeds",true,  -1,3000,false,-1,new Season[]{Season.FALL},false,2,4,6,6,6,24),
    POWDERMELON("Powdermelon", "Powdermelon Seeds",true, -1, 60,true,63, new Season[]{Season.WINTER},true,1,2,1,2,1,7),
    ANCIENT_FRUIT("Ancient Fruit", "Ancient Seeds", false,7,550,false,-1,new Season[]{Season.SPRING, Season.SUMMER,Season.FALL},false,2,7,7,7,5,28);

    public final String name;
    public final String seed;
    public final boolean oneTime;
    public final int regrowthTime;
    public final int BaseSellPrice;
    public final boolean isEdible;
    public final int energy;
    public final Season[] seasons;
    public final boolean canBecomeGiant;
    public final int StageZero;
    public final int StageOne;
    public final int StageTwo;
    public final int StageThree;
    public final int StageFour;
    public final int totalHarvestTime;

    ForagingCropType(String name, String seed, boolean oneTime, int regrowthTime,
                     int baseSellPrice, boolean isEdible, int energy, Season[] seasons,
                     boolean canBecomeGiant, int stageZero, int stageOne, int stageTwo, int stageThree, int stageFour, int totalHarvestTime) {
        this.name = name;
        this.seed = seed;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.BaseSellPrice = baseSellPrice;
        this.isEdible = isEdible;
        this.energy = energy;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
        StageZero = stageZero;
        StageOne = stageOne;
        StageTwo = stageTwo;
        StageThree = stageThree;
        StageFour = stageFour;
        this.totalHarvestTime = totalHarvestTime;
    }

    public static ForagingCropType findCropBySeed(String seed){
        for (ForagingCropType crop : ForagingCropType.values()){
            if(crop.seed.equals(seed)){
                return crop;
            }
        }
        return null;
    }


}
