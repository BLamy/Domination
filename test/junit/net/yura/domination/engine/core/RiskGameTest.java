package net.yura.domination.engine.core;

import java.io.File;
import java.util.Vector;
import junit.framework.TestCase;
import net.yura.domination.engine.RiskUIUtil;
import net.yura.domination.engine.core.StatType;
import net.yura.domination.engine.core.Card;
import static net.yura.domination.engine.core.Card.INFANTRY;
import net.yura.domination.engine.core.RiskGame;
import static net.yura.domination.engine.core.RiskGame.CARD_ITALIANLIKE_SET;
import static net.yura.domination.engine.core.RiskGame.STATE_ATTACKING;
import static net.yura.domination.engine.core.RiskGame.STATE_DEFEND_YOURSELF;
import static net.yura.domination.engine.core.RiskGame.STATE_END_TURN;
import static net.yura.domination.engine.core.RiskGame.STATE_FORTIFYING;
import static net.yura.domination.engine.core.RiskGame.STATE_GAME_OVER;
import static net.yura.domination.engine.core.RiskGame.STATE_PLACE_ARMIES;
import static net.yura.domination.engine.core.RiskGame.STATE_ROLLING;
import static net.yura.domination.engine.core.RiskGame.STATE_TRADE_CARDS;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Yur Mamyrin
 */
public class RiskGameTest extends TestCase {
    
    public RiskGameTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void testWithNullCountry() {
        try {
            Card card = new Card("foo", null);
            assertEquals(card.getName(), "foo");
            assertEquals(card.toString(), "foo");
        assert false;
        } catch (Exception e) {
            assert true;
        }
    }
    
    public void testWithCountry() {
        Country country = new Country(100, "foobar", "foobar", new Continent("sdf", "asdf", 0, 0), 0, 0);
        Card card = new Card(Card.INFANTRY, country);
        assertEquals(card.getCountry(), country);
        assertEquals(card.toString(), "Infantry - foobar (100)");
    }
    //------------------------
    // Command Crud
    //------------------------
    public void testCommandCRUD() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        Vector testVector = new Vector();
        assertEquals(instance.getCommands(), testVector);
        instance.addCommand("foo");
        testVector.add("foo");
        assertEquals(instance.getCommands(), testVector);
        instance.setCommands(new Vector());
        assertEquals(instance.getCommands(), new Vector());
    }
    
    //------------------------
    // Add Player
    //------------------------
    public void testCanAddPlayer() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertEquals(instance.getPlayers().size(), 1);
    }
        
    public void testCanAddMultiplePlayers() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        assertEquals(instance.getNoPlayers(), 2);
    }
    
    public void testCantAddPlayerAfterGameStarts() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        instance.startGame(0, 0, true, true);
        assertFalse(instance.addPlayer(0, "bar", 1, "localhost"));
        assertEquals(instance.getPlayers().size(), 0);
    }
    
    public void testCantAddPlayersOfSameName() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertFalse(instance.addPlayer(0, "foo", 1, "localhost"));
        assertEquals(instance.getPlayers().size(), 1);
    }
    
    public void testCantAddPlayersOfSameColor() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 1, "localhost"));
        assertFalse(instance.addPlayer(0, "bar", 1, "localhost"));
        assertEquals(instance.getPlayers().size(), 1);
    }
    
    //------------------------
    // Delete Player
    //------------------------
    public void testCanDeletePlayer() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertEquals(instance.getPlayers().size(), 1);
        assertTrue(instance.delPlayer("foo"));
        assertEquals(instance.getPlayers().size(), 0);
    }
    
    public void testShouldReturnFalseIfPlayerNotFound() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertFalse(instance.delPlayer("bar"));
        assertEquals(instance.getPlayers().size(), 1);
    }
    
    public void testCantDeletePlayerAfterGameStarts() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertEquals(instance.getPlayers().size(), 1);
        instance.startGame(0, 0, true, true);
        assertFalse(instance.delPlayer("foo"));
        assertEquals(instance.getPlayers().size(), 1);
    }
    
    //------------------------
    // Start Game
    //------------------------
    public void testShouldDoNothingIfGameAlreadyStarted() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        instance.startGame(0, 0, true, true);
        instance.startGame(0, 0, true, true);
    }
    
    public void testNoMap() throws Exception {
        RiskGame instance = new RiskGame();
        instance.setMemoryLoad();
        instance.startGame(0, 0, false, false);
    }
    
    //------------------------
    // setCurrentPlayer
    //------------------------
    public void testSetCurrentPlayer() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player = instance.setCurrentPlayer(0);
        assertEquals(player.getName(), "foo");
        assertEquals(player, instance.getCurrentPlayer());
    }


    
    
    //------------------------
    // TestMap
    //------------------------
    public void testTestMap() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        instance.startGame(0, 0, true, true);
        instance.testMap();
    }
    
    //------------------------
    // getNoContinentsOwned
    //------------------------
    public void testGetNoContinentsOwned() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        Player player = instance.setCurrentPlayer(0);

        instance.startGame(0, 0, true, true);
        instance.testMap();
    }
    
    
 
    //------------------------
    // getDeservedCard
    //------------------------
    public void testPlayerShouldGetCard() throws Exception {
        RiskGame instance = new RiskGame();
        System.out.println(instance.getDesrvedCard());
    }
    
    //------------------------
    // getDeservedCard
    //------------------------
    public void getNewCardState() throws Exception {
        RiskGame instance = new RiskGame();
        assertEquals(instance.getNewCardState(), 4);
        assertEquals(instance.getNewCardState(), 6);
        assertEquals(instance.getNewCardState(), 8);
        assertEquals(instance.getNewCardState(), 10);
                assertEquals(instance.getNewCardState(), 12);

    }
    
    //------------------------
    // getNoAttackDice
    //------------------------
    public void testGetNoAttackDice() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player1 = instance.setCurrentPlayer(0);
        Player player2 = instance.setCurrentPlayer(1);
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        Country country1 = Mockito.spy(instance.getCountryInt(countryId));
        do { countryId = instance.getRandomCountry(); } 
        while (country1.getIdString().equals(countryId+""));
        Country country2 = Mockito.spy(instance.getCountryInt(countryId)); 
        country1.addNeighbour(country2);
        country2.addNeighbour(country1);
        Mockito.doReturn(player1).when(country1).getOwner();
        Mockito.doReturn(player2).when(country2).getOwner();
        Mockito.doReturn(5).when(country2).getArmies();
        Mockito.doReturn(2).when(country1).getArmies();
        instance.startGame(0, 0, true, true);
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country2, country1);
        assertEquals(instance.getNoAttackDice(), 3);
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country1, country2);
        assertEquals(instance.getNoAttackDice(), 3);
    }
    
    public void testGetNoDefendDicee() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player1 = instance.setCurrentPlayer(0);
        Player player2 = instance.setCurrentPlayer(1);
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        Country country1 = Mockito.spy(instance.getCountryInt(countryId));
        do { countryId = instance.getRandomCountry(); } 
        while (country1.getIdString().equals(countryId+""));
        Country country2 = Mockito.spy(instance.getCountryInt(countryId)); 
        country1.addNeighbour(country2);
        country2.addNeighbour(country1);
        Mockito.doReturn(player1).when(country1).getOwner();
        Mockito.doReturn(player2).when(country2).getOwner();
        Mockito.doReturn(5).when(country2).getArmies();
        Mockito.doReturn(2).when(country1).getArmies();
        instance.startGame(0, 0, true, true);
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country2, country1);
        assertEquals(instance.getNoDefendDice(), 2);
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country1, country2);
        assertEquals(instance.getNoDefendDice(), 2);
    }

    
    //------------------------
    // RollA
    //------------------------
    public void testRollA() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player1 = instance.setCurrentPlayer(0);
        Player player2 = instance.setCurrentPlayer(1);
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        Country country1 = Mockito.spy(instance.getCountryInt(countryId));
        do { countryId = instance.getRandomCountry(); } 
        while (country1.getIdString().equals(countryId+""));
        Country country2 = Mockito.spy(instance.getCountryInt(countryId)); 
        country1.addNeighbour(country2);
        country2.addNeighbour(country1);
        Mockito.doReturn(player1).when(country1).getOwner();
        Mockito.doReturn(player2).when(country2).getOwner();
        Mockito.doReturn(5).when(country2).getArmies();
        Mockito.doReturn(2).when(country1).getArmies();
        instance.startGame(0, 0, true, true);
        assertFalse(instance.rollA(1));
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country2, country1);
        instance.setGameState(instance.STATE_ROLLING);
        assertFalse(instance.rollA(-1));
        assertFalse(instance.rollA(4));
        assertTrue(instance.rollA(1));
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country1, country2);
        instance.setGameState(instance.STATE_ROLLING);
        assertFalse(instance.rollA(-1));
    }
    
        //------------------------
    // RollA
    //------------------------
    public void testRollD() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player1 = instance.setCurrentPlayer(0);
        Player player2 = instance.setCurrentPlayer(1);
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        Country country1 = Mockito.spy(instance.getCountryInt(countryId));
        do { countryId = instance.getRandomCountry(); } 
        while (country1.getIdString().equals(countryId+""));
        Country country2 = Mockito.spy(instance.getCountryInt(countryId)); 
        country1.addNeighbour(country2);
        country2.addNeighbour(country1);
        Mockito.doReturn(player1).when(country1).getOwner();
        Mockito.doReturn(player2).when(country2).getOwner();
        Mockito.doReturn(5).when(country2).getArmies();
        Mockito.doReturn(2).when(country1).getArmies();
        instance.startGame(0, 0, true, true);
        assertFalse(instance.rollD(1));
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country2, country1);
        instance.setGameState(instance.STATE_DEFEND_YOURSELF);
        assertFalse(instance.rollD(-1));
        assertFalse(instance.rollD(4));
        assertTrue(instance.rollD(1));
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country1, country2);
        instance.setGameState(instance.STATE_DEFEND_YOURSELF);
        assertTrue(instance.rollD(1));
        assertFalse(instance.rollD(-1));
        assertFalse(instance.rollD(4));
    }

    //------------------------
    // getRandomCountry
    //------------------------
    public void testShouldThrowIfNotPlacingArmies() throws Exception {
        RiskGame instance = new RiskGame();
        try {
            instance.getRandomCountry();
            assert false;
        } catch (IllegalStateException e) {
            assert true;
        }
    }
    
    public void testGetRandomCountry() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        assertNotNull(instance.getCountryInt(countryId));
    }
    
    public void testNoMove() throws Exception {
        RiskGame instance = new RiskGame();
        assertFalse(instance.noMove());
        instance.setGameState(instance.STATE_FORTIFYING);
        assertTrue(instance.noMove());
        assertEquals(instance.getState(), instance.STATE_END_TURN);
    }
    
    public void testGetRandomCountryNoCountries() throws Exception {
        RiskGame instance = new RiskGame();
        instance.setCountries(new Country[]{});
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        assertNotNull(instance.getCountryInt(countryId));
    }
    //------------------------
    // PlaceArmy
    //------------------------
//    public void testPlaceArmy() throws Exception {
//        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
//        RiskGame instance = new RiskGame();
//        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
//        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
//        Country country = instance.getCountryInt(instance.getRandomCountry());
////        assertTrue(instance.placeArmy(country, 1) == 1);
//        instance.startGame(0, 0, true, true);
//
////        assertTrue(instance.placeArmy(country, 1) == 1);
//    }
    
        
    //------------------------
    // EndGO
    //------------------------
//    public void testEndGo() throws Exception {
//        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
//        RiskGame instance = new RiskGame();
//        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
//        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
//        instance.setGameState(instance.STATE_END_TURN);
//        Player player = instance.endGo();
////        Country country = instance.getCountryInt(instance.getRandomCountry());
////        assertTrue(instance.placeArmy(country, 1) == 1);
////        instance.startGame(0, 0, true, true);
//
////        assertTrue(instance.placeArmy(country, 1) == 1);
//    }
    
    //------------------------
    // Attack
    //------------------------
    public void testAttack() throws Exception { 
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
        Player player1 = instance.setCurrentPlayer(0);
        Player player2 = instance.setCurrentPlayer(1);
        instance.startGame(0, 0, true, true);
        int countryId = instance.getRandomCountry();
        Country country1 = Mockito.spy(instance.getCountryInt(countryId));
        do { countryId = instance.getRandomCountry(); } 
        while (country1.getIdString().equals(countryId+""));
        Country country2 = Mockito.spy(instance.getCountryInt(countryId)); 
        country1.addNeighbour(country2);
        country2.addNeighbour(country1);
        Mockito.doReturn(player1).when(country1).getOwner();
        Mockito.doReturn(player2).when(country2).getOwner();
        Mockito.doReturn(5).when(country2).getArmies();
        instance.startGame(0, 0, true, true);
        instance.setGameState(instance.STATE_ATTACKING);
        instance.attack(country2, country1);
    }
    
    public void testWorkOutEndGoStats() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        Player player = instance.setCurrentPlayer(0);
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        instance.workOutEndGoStats(player);
//        assertEquals(player.currentStatistic.get(StatType.KILLS), 0);
//        player.currentStatistic.addKill();
//        assertEquals(player.currentStatistic.get(StatType.KILLS), 1);
    }
    
    //------------------------
    // retreat
    //------------------------
    public void testRetreat() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        instance.setCurrentPlayer(0);
        assertFalse(instance.retreat());
        instance.setGameState(instance.STATE_ROLLING);
        assertTrue(instance.retreat());
    }
    
    //------------------------
    // getPlayersStats
    //------------------------
    public void testGetPlayersStats() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        instance.addPlayer(0, "foo", 0, "localhost");
        Player player = instance.setCurrentPlayer(0);
        Mockito.doNothing().when(instance).workOutEndGoStats(Mockito.<Player>any());
        assertEquals(player, instance.getPlayersStats().get(0));
    }
    
    
    //------------------------
    // Battle
    //------------------------
//    public void testBattle() throws Exception { 
//        RiskGame instance = new RiskGame();
//        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
//        assertTrue(instance.addPlayer(0, "bar", 1, "localhost"));
//        instance.startGame(0, 0, true, true);
//        
//           Player player = instance.setCurrentPlayer(0);
//        instance.workOutEndGoStats(player); 
//        
//        instance.setGameState(instance.STATE_DEFEND_YOURSELF);
//        int[] attackers = new int[]{0, 1, 2, 3, 4, 5};
//        int[] defenders = new int[]{0, 1, 2, 3, 4, 5};
//        instance.battle(attackers, defenders);
//
//    }
   
    
        
    //------------------------
    // getCountryInt
    //------------------------
    public void testGetNullForUnknownCountryInt() throws Exception {
        RiskGame instance = new RiskGame();
        instance.startGame(0, 0, true, true);
        assertNull(instance.getCountryInt(-1));
        assertNull(instance.getCountryInt(Integer.MAX_VALUE));
    }
            
    //------------------------
    // getCountryInt
    //------------------------
    public void testGetSetCardMode() throws Exception {
        RiskGame instance = new RiskGame();
        instance.setCardMode(instance.MODE_CAPITAL);
        assertEquals(instance.getCardMode(), instance.MODE_CAPITAL);
    }
    
    //------------------------
    // getCountryInt
    //------------------------
    public void testSetMapNameToNullRemovesFromProperties() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.getProperties().containsKey("name"));
        instance.setMapName(null);
        assertFalse(instance.getProperties().containsKey("name"));
    }

    public void testSetMapName() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.getProperties().containsKey("name"));
        instance.setMapName("foo");
        assertEquals(instance.getMapName(), "foo");
    }

    //------------------------
    // tCircleSize
    //------------------------
    public void testGetSetCircleSize() throws Exception {
        RiskGame instance = new RiskGame();
        assertEquals(instance.getCircleSize(), 20);
        instance.setCircleSize(10);
        assertEquals(instance.getCircleSize(), 10);
        assertTrue(instance.getProperties().containsKey("circle"));
        instance.setCircleSize(20);
        assertFalse(instance.getProperties().containsKey("circle"));
        assertEquals(instance.getCircleSize(), 20);
    }
    
    //------------------------
    // getsetVersion
    //------------------------
    public void testGetSetVersion() throws Exception {
        RiskGame instance = new RiskGame();
        assertEquals(instance.getVersion(), 1);
        instance.setVersion(2);
        assertEquals(instance.getVersion(), 2);
    }
    //------------------------
    // rollDice
    //------------------------
    public void testRollDice() throws Exception {
        RiskGame instance = new RiskGame();
        assertEquals(instance.rollDice(1).length, 1);
    }
    
    public void testDiceShouldBeNonZero() throws Exception {
        RiskGame instance = new RiskGame();  
        int[] dice = instance.rollDice(3);
        assertTrue(dice[0] < 6);
        assertTrue(dice[1] < 6);
        assertTrue(dice[2] < 6);
    }
    
    
    public void testGetNumber() {
        assertEquals(RiskGame.getNumber("1"), 1);
        assertEquals(RiskGame.getNumber("-1"), -1);
        assertEquals(RiskGame.getNumber("asff"), -1);
    }
    
    
    
    
    
    
    
    
    
    
        
    //------------------------
    // HSBtoRGB
    //------------------------
    public void testHSBtoRGB() {
        int rgb = RiskGame.HSBtoRGB(30f, .96f, .52f);
        int blue = rgb & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int red = (rgb >> 16) & 0xFF;
        System.out.println("#" + red + green + blue);
    }
    
    //------------------------
    // getPlayer
    //------------------------
    public void testGetPlayer() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertEquals(instance.getPlayer("foo").getName(), "foo");
    }
    
    public void testGetPlayerReturnsNullOnNotFound() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertEquals(instance.getPlayer("bar"), null);
    }

    
    //------------------------
    // endAttack
    //------------------------
    public void testEndAttack() throws Exception {
        RiskGame instance = new RiskGame();
        assertFalse("Must be in attacking state", instance.endAttack());
        instance.setGameState(instance.STATE_ATTACKING);
        assertTrue("Must be in attacking state", instance.endAttack());
        assertEquals("Should transtion to stateForitfied", instance.getState(), instance.STATE_FORTIFYING);
    }
    
    //------------------------
    // canContinue
    //------------------------
    public void testCanContinue() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        Mockito.doReturn(false).when(instance).checkPlayerWon();
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        instance.setGameState(instance.STATE_GAME_OVER);
        assertTrue("CanContinue", instance.canContinue());
    }
    
    public void testCanNotContinue() throws Exception {
        RiskGame instance = new RiskGame();
        assertFalse("CanNotContinue", instance.canContinue());
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        instance.setGameState(instance.STATE_GAME_OVER);
        assertFalse("CanNotContinue", instance.canContinue());
    }
    

    //------------------------
    // endTrade
    //------------------------
    public void testEndTrade() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        Mockito.doReturn(true).when(instance).canEndTrade();
        assertEquals(instance.endTrade(), true);
        assertEquals("Should Transition to STATE_PLACE_ARMIES", instance.getState(), instance.STATE_PLACE_ARMIES );
    }
    
    public void testNotEndTrade() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        Mockito.doReturn(false).when(instance).canEndTrade();
        assertEquals(instance.endTrade(), false);
    }

    
    public void testCanEndTrade() throws Exception {
        RiskGame instance = new RiskGame();
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        assertFalse(instance.canEndTrade());
        instance.setGameState(instance.STATE_TRADE_CARDS);
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        instance.setCurrentPlayer(0);
        assertTrue(instance.canEndTrade());
        instance.setCardMode(instance.CARD_ITALIANLIKE_SET);
        assertTrue(instance.canEndTrade());
    }

    public void testCantTradeAfterTradeCap() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        Mockito.doReturn(true).when(instance).canEndTrade();
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        try {
            instance.endTrade();
        } catch (IllegalStateException e) {
            assert true;
        }
    }
            
//    public void testRollA() throws Exception {
//        RiskGame instance = Mockito.spy(new RiskGame());
//        instance.setGameState(instance.STATE_ROLLING);
//        instance.rollA(6);
//    }
//    public void testEndTrade() throws Exception {
//        RiskGame instance = Mockito.spy(new RiskGame());
//        Mockito.doReturn(true).when(instance).canEndTrade();
//        assertEquals(instance.endTrade(), 1);
//        assertEquals("Should Transition to STATE_PLACE_ARMIES", instance.getState(), instance.STATE_PLACE_ARMIES );
//    }
        

    
    //------------------------
    // setupNewMap
    //------------------------
    public void testSetupNewMap() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        instance.setupNewMap();
        assertEquals(instance.getCountries().length, 0);
        assertEquals(instance.getContinents().length, 0);
        assertEquals(instance.getCards().size(), 0);
        assertEquals(instance.getUsedCards().size(), 0);
        assertEquals(instance.getMissions().size(), 0);
        assertEquals(instance.getProperties().size(), 0);
        assertTrue(instance.NoEmptyCountries());
    }
    
    //------------------------
    // continuePlay
    //------------------------
    public void testContinuePlay() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        instance.setCurrentPlayer(0);
        Mockito.doReturn(true).when(instance).canContinue();
        assertTrue("continuePlay", instance.continuePlay());
        assertEquals("", instance.getState(), instance.STATE_ATTACKING);
    }
    
//    public void testCantContinuePlay() throws Exception {
//        RiskGame instance = new RiskGame();
//        assertTrue("cant continuePlay", instance.continuePlay());
//    }
    
    //------------------------
    // getClosestCountry
    //------------------------
    public void testGetClosestCountry() throws Exception {
        RiskGame instance = new RiskGame();
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        int color = instance.getClosestCountry(100, 100);
        System.out.println("fooasdfaf" + color);
    }
    
    /**
     * Test of trade method, of class RiskGame.
     */
    public void testTrade() {
        System.out.println("trade");

        RiskGame instance;
        try {
            RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
            instance = new RiskGame();
        }
        catch(Exception ex) {
            throw new RuntimeException(ex);
        }

        //Country country =  new Country(1, "name", "Full Name", new Continent("name", "Full Name", 5, 0xFFFF0000), 10, 10);

        // 3 different cards = there are 24 combinations

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );


        // 2 cards are the same - CANNON
        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );

        assertEquals(4, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );
        assertEquals(4, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_INCREASING_SET) );














        int all_INFANTRY = 4;
        int all_CAVALRY = 6;
        int all_CANNON = 8;
        int all_DIFF = 10;
        int all_WILDCARD = 12;


        // 3 different cards = there are 24 combinations

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_WILDCARD, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );


        // 2 cards are the same - CANNON
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_FIXED_SET) );


        
        
        
        
        
        
        
        
        
        all_INFANTRY = 6;//4;
        all_CAVALRY = 8;//6;
        all_CANNON = 4;//8;
        all_DIFF = 10;
        int one_wildcard_2_the_same = 12;
        int one_wildcard_2_different = 0;
        int two_wildcards = 0;
        all_WILDCARD = 0;//12;


        // 3 different cards = there are 24 combinations

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_DIFF, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(one_wildcard_2_different, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );


        // 3 cards are the same - 4 combinations
        assertEquals(all_CANNON, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_INFANTRY, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_CAVALRY, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(all_WILDCARD, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );


        // 2 cards are the same - CANNON
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - INFANTRY
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CANNON, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - CAVALRY
        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CAVALRY, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.INFANTRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CAVALRY, Card.CANNON, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(one_wildcard_2_the_same, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.INFANTRY, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(0, instance.getTradeAbsValue(Card.CANNON, Card.CAVALRY, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        // 2 cards are the same - WILDCARD
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CANNON, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.INFANTRY, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.WILDCARD, Card.CAVALRY, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.CANNON, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.INFANTRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.WILDCARD, Card.CAVALRY, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );

        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.CANNON, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.INFANTRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
        assertEquals(two_wildcards, instance.getTradeAbsValue(Card.CAVALRY, Card.WILDCARD, Card.WILDCARD, RiskGame.CARD_ITALIANLIKE_SET) );
    }
    
    public void testDefaults() throws Exception {
        RiskGame instance = Mockito.spy(new RiskGame());
        assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
        Player player = instance.setCurrentPlayer(0);
        instance.startGame(instance.MODE_CAPITAL, 0, true, true);
        assertEquals(instance.getMaxDefendDice(), 3);
        assertEquals(instance.isCapturedCountry(), false);
        assertEquals(instance.canTrade(), false);
        assertEquals(instance.getAttackerDice(), 0);
        assertEquals(instance.getDefenderDice(), 0);
        assertEquals(instance.getBattleRounds(), 0);
        assertEquals(instance.getImageMap(), "luca_map.gif");
        assertEquals(instance.getCardsFile(), "risk.cards");
        assertEquals(instance.getMapFile(), "luca.map");
        assertEquals(instance.getNoCountries(), 42);
        assertEquals(instance.getNoContinents(), 6);
        assertEquals(instance.getNoMissions(), 9);
        assertEquals(instance.getNoCards(), 44);
        assertEquals(instance.isRecycleCards(), true);
        assertEquals(instance.getDefaultMap(), "luca.map");
        assertEquals(instance.getDefaultCards(), "risk.cards");
    }
}
