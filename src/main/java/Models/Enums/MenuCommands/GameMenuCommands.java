package Models.Enums.MenuCommands;

public enum GameMenuCommands implements Command {

    // game commands
    GO_TO_MAIN("go\\s+to\\s+main\\s+menu"),
    SHOW_CURRENT_MENU("show\\s+current\\s+menu"),
    EXIT_GAME("exit\\s+game"),
    DELETE_GAME("delete\\s+game"),
    NEXT_TURN("next\\s+turn"),
    GAME_NEW("^game\\s+new\\s+-u\\s+(?<username1>\\S+)(?:\\s+(?<username2>\\S+))?(?:\\s+(?<username3>\\S+))?(?:\\s+(?<extraInvalid>\\S+))?$"),
    GAME_MAP("game\\s+map\\s+(?<mapNumber>\\S+)"),
    LOAD_GAME("load\\s+game"),
    SHOW_MONEY("show\\s+money"),
    // date and time

    TIME("time"),
    DATE("date"),
    DATE_TIME("datetime"),
    DAY_OF_WEEK("day\\s+of\\s+the\\s+week"),

    // CHEAT CODE
    TIME_CHEAT("cheat\\s+advance\\s+time\\s+(?<X>\\S+)h"),
    DATE_CHEAT("cheat\\s+advance\\s+date\\s+(?<X>\\S+)d"),

    // season

    SEASON("Season"),

    // weather

    WEATHER("weather"),
    WEATHER_FORECAST("weather\\s+forecast"),

    // CHEAT CODE
    CHEAT_THOR("cheat\\s+Thor\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    CHEAT_WEATHER_SET("cheat\\s+weather\\s+set\\s+(?<Type>\\S+)"),

    // greenhouse

    GREEN_HOUSE_BUILD("greenhouse\\s+build"),

    // map

    WALK("walk\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    PRINT_MAP("print\\s+map\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)\\s+-s\\s+(?<size>\\S+)"),
    HELP_READING_MAP("help\\s+reading\\s+map"),

    // energy

    ENERGY_SHOW("energy\\s+show"),

    // CHEAT CODE
    CHEAT_ENERGY_SET("energy\\s+set\\s+-v\\s+(?<value>\\S+)"),
    CHEAT_ENERGY_UNLIMITED("energy\\s+unlimited"),

    // inventory

    INVENTORY_SHOW("inventory\\s+show"),
    INVENTORY_TRASH_FULL("inventory\\s+trash\\s+-i\\s+(?<ItemName>\\S+)\\s+"),
    INVENTORY_TRASH_NOT_FULL("inventory\\s+trash\\s+-i\\s+(?<ItemName>\\S+)\\s+-n\\s+(?<number>\\S+)"),

    // Tools

    TOOLS_EQUIP("tools\\s+equip\\s+(?<toolName>\\S+)"),
    TOOLS_SHOW_CURRENT("tools\\s+show\\s+current"),
    TOOLS_SHOW_AVAILABLE("tools\\s+show\\s+available"),
    TOOLS_UPGRADE("tools\\s+upgrade\\s+(?<toolName>\\S+)"),
    TOOLS_USE("tools\\s+use\\s+-d\\s+(?<direction>\\S+)"),

    // plants and farming

    PLANT("plant\\s+-s\\s+(?<seed>\\S+)\\s+-d\\s+(?<direction>\\S+)"),
    SHOW_PLANT("showPlant\\s+-l\\s+(?<x>\\S+)\\s+,\\s+?(<y>\\S+)"),
    FERTILIZE("fertilize\\s+-f\\s+(?<fertilizer>\\S+)\\s+-d\\s+(?<direction>\\S+)"),
    HOW_MUCH_WATER("howMuch\\s+water"),
    CROP_INFO("cropInfo\\s+-n\\s+(?<cropName>\\S+)"),

    // crafting

    CRAFTING_SHOW_RECIPES("crafting\\s+show\\s+recipes"),
    CRAFTING_CRAFT("crafting\\s+craft\\s+<itemName>"),
    PLACE_ITEM("place\\s+item\\s+-n\\s+(?<itemName>\\S+)\\s+-d\\s+(?<direction>\\S+)"),

    // CHEAT CODE
    CHEAT_ADD_ITEM("cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>\\S+)\\s+-c\\s+(?<count>\\S+)"),

    //cooking

    REFRIGERATOR_PICK("cooking\\s+refrigerator\\s+pick\\s+(?<item>\\S+)"),
    REFRIGERATOR_PUT("cooking\\s+refrigerator\\s+put\\s+(?<item>\\S+)"),
    PREPARE("cooking\\s+prepare\\s+(?<recipeName>\\S+)"),
    SHOW_COOKING_RECIPES("cooking\\s+show\\s+recipes"),

    //eating

    EAT("eat\\s+(?<foodName>\\S+)"),

    // Animal husbandry

    BUILD("build\\s+-a\\s+(?<buildingName>\\S+)\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    BUY_ANIMALS("buy\\s+animal\\s+-a\\s+(?<animal>\\S+)\\s+-n\\s+(?<name>\\S+)"),
    PET("pet\\s+-n\\s+(?<name>\\S+)"),
    ANIMALS("animals"),
    SHEPHERD("shepherd\\s+animals\\s+-n\\s+(?<animalName>\\S+>\\s+-l\\s+(?<x>\\S+)\\s+,\\s+(?<y>\\S+)"),
    FEED_HAY("feed\\s+hay\\s+-n\\s+(?<animalName>\\S+)"),
    PRODUCES("produces"),
    COLLECT_PRODUCE("collect\\s+produce\\s+-n\\s+(?<name>\\S+)"),
    SELL_ANIMAL("sell\\s+animal\\s+-n\\s+(?<name>\\S+)"),
    FISHING("fishing\\s+-p\\s+(?<fishingPole>\\S+)"),

    // CHEAT CODE
    CHEAT_SET_FRIENDSHIP("cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>\\S+)\\s+-c\\s+(?<amount>\\S+)"),

    // Artisan

    ARTISAN_USE("artisan\\s+use\\s+(?<artisanName>\\S+)\\s+(?<itemName>\\S+)"),
    ARTISAN_GET("artisan\\s+get\\s+(?<artisanName>\\S+)"),

    // Dealing
    ENTER_STORE("go\\s+to\\s+(?<storeName>\\S+)"),
    SHOW_ALL_PRODUCTS("show\\s+all\\s+products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show\\s+all\\s+available\\s+products"),
    PURCHASE("^purchase\\s+\\s+(?<productName>\\S+)(?:\\s+-n\\s+(?<count>\\S+))?\\s+$"),
    SELL("sell\\s+(?<productName>\\S+)\\s+-n\\s+(?<count>\\S+)"),
    GET_OUT("exit\\s+store"),

    // CHEAT CODE
    CHEAT_ADD("cheat\\s+add\\s+(?<count>\\S+)\\s+dollars"),

    // Friendship

    FRIENDSHIPS("friendships"),
    TALK("talk\\s+-u\\s+(?<username>\\S+)\\s+-m\\s+(?<message>\\S+)"),
    TALK_HISTORY("talk\\s+history\\s+-u\\s+(?<username>\\S+)"),
    GIFT("gift\\s+-u\\s+(?<username>\\S+)\\s+-i\\s+(?<item>\\S+)\\s+-a\\s+(?<amount>\\S+)"),
    GIFT_LIST("gift\\s+list"),
    GIFT_RATE("gift\\s+rate\\s+-i\\s+(?<giftNumber>\\S+)\\s+-r\\s+(?<rate>\\S+)"),
    GIFT_HISTORY("gift\\s+history\\s+-u\\s+(?<username>\\S+)"),
    HUG("hug\\s+-u\\s+(?<username>\\S+)"),
    FLOWER("flower\\s+-u\\s+(?<username>\\S+)"),
    ASK_MARRIAGE("ask\\s+marriage\\s+-u\\s+(?<username>\\S+)\\s+-r\\s+(?<ring>\\S+)"),
    RESPOND("respond\\s+(–accept|–reject)\\s+-u\\s+(?<username>\\S+)"),

    // Trade

    START_TRADE("start\\s+trade"),
    TRADE("trade\\s+-u\\s+(?<username>\\S+)\\s+-t\\s+(?<type>\\S+)\\s+-i\\s+(?<item>\\S+)\\s+-a\\s+(?<amount>\\S+)\\s+\\[-p\\s+(?<price>\\S+)\\]\\s+\\[-ti\\s+(?<targetItem>\\S+)\\s+-ta\\s+(?<targetAmount>\\S+)\\]"),
    TRADE_LIST("trade\\s+list"),
    TRADE_RESPONSE("trade\\s+response\\s+(–accept|–reject)\\s+-i\\s+<id>"),
    TRADE_HISTORY("trade\\s+history"),

    // NPCs

    MEET_NPC("meet\\s+NPC\\s+(?<npcName>\\S+)"),
    GIFT_NPC("gift\\s+NPC\\s+(?<npcName>\\S+)\\s+-i\\s+(?<item>\\S+)"),
    FRIENDSHIP_NPC_LIST("friendship\\s+NPC\\s+list"),
    QUESTS_LIST("quests\\s+list"),
    QUESTS_FINISH("quests\\s+finish\\s+-i\\s+(?<index>\\S+)");



    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
