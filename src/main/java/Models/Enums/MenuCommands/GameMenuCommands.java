package Models.Enums.MenuCommands;

public enum GameMenuCommands implements Command {

    // game commands

    SHOW_CURRENT_MENU("show\\s*current\\s*menu"),
    EXIT_GAME("exit\\s*game"),
    DELETE_GAME("delete\\s*game"),
    NEXT_TURN("next\\s*turn"),
    GAME_NEW("^game\\s*new\\s*-u\\s*(?<username1>\\S*)(?:\\s+(?<username2>\\S*))?(?:\\s+(?<username3>\\S*))?(?:\\s+(?<extraInvalid>\\S*))?$"),
    GAME_MAP("game\\s*map\\s*(?<mapNumber>.+)"),
    LOAD_GAME("load\\s*game"),

    // date and time

    TIME("time"),
    DATE("date"),
    DATE_TIME("datetime"),
    DAY_OF_WEEK("day\\s*of\\s*the\\s*week"),

    // CHEAT CODE
    TIME_CHEAT("cheat\\s*advance\\s*time\\s*(?<X>.+)h"),
    DATE_CHEAT("cheat\\s*advance\\s*date\\s*(?<X>.+)d"),

    // season

    SEASON("Season"),

    // weather

    WEATHER("weather"),
    WEATHER_FORECAST("weather\\s*forecast"),

    // CHEAT CODE
    CHEAT_THOR("cheat\\s*Thor\\s*-l\\s*(?<x>.+)\\s*,\\s*(?<y>.+)"),
    CHEAT_WEATHER_SET("cheat\\s*weather\\s*set\\s*(?<Type>.+)"),

    // greenhouse

    GREEN_HOUSE_BUILD("greenhouse\\s*build"),

    // map

    WALK("walk\\s*-l\\s*(?<x>.+)\\s*,\\s*(?<y>.+)"),
    PRINT_MAP("print\\s*map\\s*-l\\s*(?<x>.+)\\s*,\\s*(?<y>.+)\\s*-s\\s*(?<size>.+)"),
    HELP_READING_MAP("help\\s*reading\\s*map"),

    // energy

    ENERGY_SHOW("energy\\s*show"),

    // CHEAT CODE
    CHEAT_ENERGY_SET("energy\\s*set\\s*-v\\s*(?<value>.+)"),
    CHEAT_ENERGY_UNLIMITED("energy\\s*unlimited"),

    // inventory

    INVENTORY_SHOW("inventory\\s*show"),
    INVENTORY_TRASH_FULL("inventory\\s*trash\\s*-i\\s*(?<ItemName>.+)\\s*"),
    INVENTORY_TRASH_NOT_FULL("inventory\\s*trash\\s*-i\\s*(?<ItemName>.+)\\s*-n\\s*(?<number>.+)"),

    // Tools

    TOOLS_EQUIP("tools\\s*equip\\s*(?<toolName>.+)"),
    TOOLS_SHOW_CURRENT("tools\\s*show\\s*current"),
    TOOLS_SHOW_AVAILABLE("tools\\s*show\\s*available"),
    TOOLS_UPGRADE("tools\\s*upgrade\\s*(?<toolName>.+)"),
    TOOLS_USE("tools\\s*use\\s*-d\\s*(?<direction>.+)"),

    // plants and farming

    PLOW("plow\\s*(?<direction>.+)"),
    PLANT("plant\\s*-s\\s*(?<seed>.+)\\s*-d\\s*(?<direction>.+)"),
    SHOW_PLANT("showPlant\\s*-l\\s*(?<x>.+)\\s*,\\s*?(<y>.+)"),
    FERTILIZE("fertilize\\s*-f\\s*(?<fertilizer>.+)\\s*-d\\s*(?<direction>.+)"),
    HOW_MUCH_WATER("howMuch\\s*water"),
    HARVEST("harvest\\s*(?<direction>.+)"),

    // crafting

    CRAFT_INFO("craftInfo\\s*-n\\s*(?<craftName>.+)"),
    CRAFTING_SHOW_RECIPES("crafting\\s*show\\s*recipes"),
    CRAFTING_CRAFT("crafting\\s*craft\\s*<itemName>"),
    PLACE_ITEM("place\\s*item\\s*-n\\s*(?<itemName>.+)\\s*-d\\s*(?<direction>.+)"),

    // CHEAT CODE
    CHEAT_ADD_ITEM("cheat\\s*add\\s*item\\s*-n\\s*(?<itemName>.+)\\s*-c\\s*(?<count>.+)"),

    //cooking

    REFRIGERATOR_PICK("cooking\\s*refrigerator\\s*pick\\s*(?<item>.+)"),
    REFRIGERATOR_PUT("cooking\\s*refrigerator\\s*put\\s*(?<item>.+)"),
    SHOW_RECIPES("cooking\\s*show\\s*recipes"),
    PREPARE("cooking\\s*prepare\\s*(?<recipeName>.+)"),

    //eating

    EAT("eat\\s*(?<foodName>.+)"),

    // Animal husbandry

    BUILD("build\\s*-a\\s*(?<buildingName>.+)\\s*-l\\s*(?<x>.+)\\s*,\\s*(?<y>.+)"),
    BUY_ANIMALS("buy\\s*animal\\s*-a\\s*(?<animal>.+)\\s*-n\\s*(?<name>.+)"),
    PET("pet\\s*-n\\s*(?<name>.+)"),
    ANIMALS("animals"),
    SHEPHERD("shepherd\\s*animals\\s*-n\\s*(?<animalName>.+>\\s*-l\\s*(?<x>.+)\\s*,\\s*(?<y>.+)"),
    FEED_HAY("feed\\s*hay\\s*-n\\s*(?<animalName>.+)"),
    PRODUCES("produces"),
    COLLECT_PRODUCE("collect\\s*produce\\s*-n\\s*(?<name>.+)"),
    SELL_ANIMAL("sell\\s*animal\\s*-n\\s*(?<name>.+)"),
    FISHING("fishing\\s*-p\\s*(?<fishingPole>.+)"),

    // CHEAT CODE
    CHEAT_SET_FRIENDSHIP("cheat\\s*set\\s*friendship\\s*-n\\s*(?<animalName>.+)\\s*-c\\s*(?<amount>.+)"),

    // Artisan

    ARTISAN_USE("artisan\\s*use\\s*(?<artisanName>.+)\\s*(?<itemName>.+)"),
    ARTISAN_GET("artisan\\s*get\\s*(?<artisanName>.+)"),

    // Dealing

    SHOW_ALL_PRODUCTS("show\\s*all\\s*products"),
    SHOW_ALL_AVAILABLE_PRODUCTS("show\\s*all\\s*available\\s*products"),
    PURCHASE("purchase\\s*(?<productName>.+)\\s*-n\\s*(?<count>.+)"),
    SELL("sell\\s*(?<productName>.+)\\s*-n\\s*(?<count>.+)"),

    // CHEAT CODE
    CHEAT_ADD("cheat\\s*add\\s*(?<count>.+)\\s*dollars"),

    // Friendship

    FRIENDSHIPS("friendships"),
    TALK("talk\\s*-u\\s*(?<username>.+)\\s*-m\\s*(?<message>.+)"),
    TALK_HISTORY("talk\\s*history\\s*-u\\s*(?<username>.+)"),
    GIFT("gift\\s*-u\\s*(?<username>.+)\\s*-i\\s*(?<item>.+)\\s*-a\\s*(?<amount>.+)"),
    GIFT_LIST("gift\\s*list"),
    GIFT_RATE("gift\\s*rate\\s*-i\\s*(?<giftNumber>.+)\\s*-r\\s*(?<rate>.+)"),
    GIFT_HISTORY("gift\\s*history\\s*-u\\s*(?<username>.+)"),
    HUG("hug\\s*-u\\s*(?<username>.+)"),
    FLOWER("flower\\s*-u\\s*(?<username>.+)"),
    ASK_MARRIAGE("ask\\s*marriage\\s*-u\\s*(?<username>.+)\\s*-r\\s*(?<ring>.+)"),
    RESPOND("respond\\s*(–accept|–reject)\\s*-u\\s*(?<username>.+)"),

    // Trade

    START_TRADE("start\\s*trade"),
    TRADE("trade\\s*-u\\s*(?<username>.+)\\s*-t\\s*(?<type>.+)\\s*-i\\s*(?<item>.+)\\s*-a\\s*(?<amount>.+)\\s*\\[-p\\s*(?<price>.+)\\]\\s*\\[-ti\\s*(?<targetItem>.+)\\s*-ta\\s*(?<targetAmount>.+)\\]"),
    TRADE_LIST("trade\\s*list"),
    TRADE_RESPONSE("trade\\s*response\\s*(–accept|–reject)\\s*-i\\s*<id>"),
    TRADE_HISTORY("trade\\s*history"),

    // NPCs

    MEET_NPC("meet\\s*NPC\\s*(?<npcName>.+)"),
    GIFT_NPC("gift\\s*NPC\\s*(?<npcName>.+)\\s*-i\\s*(?<item>.+)"),
    FRIENDSHIP_NPC_LIST("friendship\\s*NPC\\s*list"),
    QUESTS_LIST("quests\\s*list"),
    QUESTS_FINISH("quests\\s*finish\\s*-i\\s*(?<index>.+)");



    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
