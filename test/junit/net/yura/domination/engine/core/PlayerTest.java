/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import junit.framework.TestCase;

/**
 *
 * @author brett
 */
public class PlayerTest extends TestCase {
    public void testRename() {
        Player player = new Player(0, "foo", 0, "bar");
        assertEquals(player.getName(), "foo");
        player.rename("fizz");
        assertEquals(player.toString(), "fizz");
    }
    
    public void testGetSetColor() {
        Player player = new Player(0, "foo", 0, "bar");
        player.setColor(1);
        assertEquals(player.getColor(), 1);
    }
    
    public void testStats() {
        Player player = new Player(0, "foo", 0, "bar");
        player.nextTurn();
        assertNotNull(player.getStatistics());   
    }
    
    public void testGetSetCapital() {
        Player player = new Player(0, "foo", 0, "bar");
        Country country = new Country(0, "foo", "foo", new Continent("foobar", "fasdf", 0, 0), 0, 0);
        player.setCapital(country);
        assertEquals(player.getCapital(), country);
    }
    
    public void testGetSetMission() {
        Player player = new Player(0, "foo", 0, "bar");
        Continent c1 = new Continent("FOO", "BAR", 0, 0);
        Continent c2 = new Continent("FIZZ", "BUZZ", 0, 0);
        Mission mission = new Mission(player, 0, 0, c1, c2, null, "DO THE THING");
        player.setMission(mission);
        assertEquals(player.getMission(), mission);
    }
}
