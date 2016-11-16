/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine;

import java.io.File;
import junit.framework.TestCase;
import net.yura.domination.engine.core.*;

/**
 * @author brett
 */
public class RiskTest extends TestCase {
    
    public RiskTest(String testName) {
        super(testName);
    }

    public void testSmoke() {
        assert(true);
    }
    
    
    public void testCreateRandomUniqueAddress() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        RiskGame instance = new RiskGame();
        Risk game = new Risk();
        String temp1 = game.createRandomUniqueAddress();
        String temp2 = game.createRandomUniqueAddress();
        assertNotNull(temp1);
        assertNotSame(temp1, temp2);
        
    }
    
    public void testSetGame() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        Risk game = new Risk();
        RiskGame instance = new RiskGame();
        game.setGame(instance);
        
        assertNotNull(game.getGame());
        
        game.setGame(instance);
        
        assertSame(instance, game.getGame());
    }
    
    public void testGetGame() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        Risk game = new Risk();
        RiskGame instance = new RiskGame();
        
        assertNull(game.getGame());
              
        game.setGame(instance);
        
        assertNotNull(game.getGame());
        
        
    }
    
    public void testFindEmptySpot() throws Exception {
        RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
        Risk game = new Risk();
        RiskGame instance = new RiskGame();
        game.setGame(instance);
        Player temp = game.findEmptySpot();
        
        assertNull(temp);
        
    
    }
    
   public void testSettMyAdress() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       game.setAddress("newAddress");
       assert(true);
   }
    
   public void testGetMyAddress() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       game.setAddress("newAddress");
       assertEquals(game.getMyAddress(), "newAddress");
   }
   
   public void testGetCurrentMission() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       RiskGame instance = new RiskGame();
              
       assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
       instance.startGame(instance.MODE_CAPITAL, 0, true, true);
       game.setGame(instance);
       System.out.println(game.getCurrentMission());
       assertEquals(game.getCurrentMission(), "Capture all opposing Headquarters-while still controlling your own territory.");
       
       
       RiskGame instance2 = new RiskGame();
       assertTrue(instance2.addPlayer(0, "foo", 0, "localhost"));
       instance2.startGame(instance2.MODE_DOMINATION, 0, true, true);
       game.setGame(instance2);
       assertEquals(game.getCurrentMission(), "Conquer the world by occupying every territory on the board, thus eliminating all your opponents.");
   }
   
   public void testGetPlayerColors() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       RiskGame instance = new RiskGame();
      
       assertNotNull(game.getPlayerColors());
       
       assertTrue(instance.addPlayer(0, "foo", 1, "localhost"));
       instance.startGame(instance.MODE_CAPITAL, 0, true, true);
       game.setGame(instance);
       
       assertNotNull(game.getPlayerColors());
       
   }
   
   
   public void testGetCountryName() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       RiskGame instance = new RiskGame();
       assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
       instance.startGame(instance.MODE_DOMINATION, 0, true, true);
       game.setGame(instance);
       game.getCountryName(game.getGame().getRandomCountry());
       
   }
   
   public void testGetAutoEndGo() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       
       assertFalse(game.getAutoEndGo());
       
       RiskGame instance = new RiskGame();
       instance.startGame(instance.MODE_DOMINATION, 0, true, true);
       game.setGame(instance);
       
       assertNotNull(game.getAutoEndGo());
       
       RiskGame instance2 = new RiskGame();
       assertTrue(instance2.addPlayer(0, "foo", 0, "localhost"));
       instance2.startGame(instance2.MODE_DOMINATION, 0, true, true);
       game.setGame(instance2);
       
       assertNotNull(game.getAutoEndGo());

   }
   
   
   public void testShowMessageDialog() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       RiskGame instance = new RiskGame();
       assertTrue(instance.addPlayer(0, "foo", 0, "localhost"));
       instance.startGame(instance.MODE_DOMINATION, 0, true, true);
       game.setGame(instance);
       
       game.showMessageDialog("a");
   }
   
   public void testGetAutoDefend() throws Exception {
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       
       assertFalse(game.getAutoDefend());
       
       RiskGame instance = new RiskGame();
       instance.startGame(instance.MODE_DOMINATION, 0, true, true);
       game.setGame(instance);
       
       assertNotNull(game.getAutoDefend());
   }
   
   public void testGetLocalGame() throws Exception{
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       assertNotNull(game.getLocalGame());
   }
       
   public void testCanTrade() throws Exception{
       RiskUIUtil.mapsdir = new File("./game/Domination/maps").toURI().toURL();
       Risk game = new Risk();
       RiskGame instance = new RiskGame();
       game.setGame(instance);
       
       assertFalse(game.canTrade(null, null, null));
       
       
       
   }
}