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
   
   
}