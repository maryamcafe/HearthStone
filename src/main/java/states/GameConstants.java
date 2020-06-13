package states;

public class GameConstants {
    public static final int numOfNeutralMinions = 5;
    public static final int numOfNeutralSpells = 5;
    public static final int numOfAllCards = 20;
    public static final int numOfDefaultCards = 10;

    public static final int numOfallHerosCards = 15;
    public static final int numOfHeros = 3;
    public static final int numOfDefaultHeros = 1;
    public static final int allCards = 20;

    public static final int defaultCoins = 50;


    public static final String gameMainMenu = "Enter:\n" +
            "\"collections\" to view all of your cards\n" +
            "\"store\" to go to the store\n" +
            "\"exit\" to exit the game and logout your account,\n" +
            "\"exit -a\" to exit the game\n" +
            "You can Always Enter \"help\" to view your options";

    public static final String collectionMenuHelp = "First select your hero.\n";


    public static final String heroMenuHelp = "Enter :\n" + //if one enters help in HeroMenu, this will show
            "\"ls -a -heroes\" to view all of your heros\n" + //This will lead you to heroSelectionMenu
            "\"ls -m -heroes\" to view your current hero\n" +
            "\"back\" to go back to  previous menu"; //This will lead you back to heroSelection menu

    public static final String heroSelectionHelp = "Enter:\n" +
            "\"select heroName\" to select the hero with name \"heroName\"";

    public static final String CardMenuHelp = "Enter:\n" +
            "\"ls -a -cards\" to view all of your hero's cards\n" +
            "\"ls -m -cards\" to view your current deck's cards\n" + //this will lead to removeCardMenu
            "\"ls -n -cards\" to show cards that are not present in current deck, but can be added to it\n" +
            //this will lead to addCardMenu
            "\"back\" to go back to  previous menu"; //This will lead you back to heroSelection menu

    public static final String addCardHelp = "Enter \"add cardName\" to add card with name cardName to your deck";

    public static final String removeCardHelp = "Enter \"remove cardName\" to remove card with name cardName from your deck";

    public static final String storeHelp = "Enter:\n" +
            "\"ls -b\" to view cards which you can buy\n" +
            "\"ls -s\" to view cards which you can sell\n" +
            "\"wallet\" to view how much money you have in your wallet\n";

    public static final String buyHelp = "Enter \"buy cardName\" to buy card with name cardName\n" +
            "\"wallet\" to view how much money you have in your wallet\n";

    public static final String sellHelp = "Enter \"sell cardName\" to sell card with name cardName\n" +
            " \"wallet\" to view how much money you have in your wallet\n\"";


}
