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
public class MissionTest extends TestCase {
    public void testMissionDefaults() {
        Continent c1 = new Continent("FOO", "BAR", 0, 0);
        Continent c2 = new Continent("FIZZ", "BUZZ", 0, 0);
        Player player = new Player(0, "Foo", 0, "bar");
        Mission mission = new Mission(player, 0, 0, c1, c2, null, "DO THE THING");
        assertEquals(mission.getPlayer(), player);
        assertEquals(mission.getNoofarmies(), 0);
        assertEquals(mission.getNoofcountries(), 0);
        assertEquals(mission.getContinent1(), c1);
        assertEquals(mission.getContinent2(), c2);
        assertEquals(mission.getContinent3(), null);
        assertEquals(mission.getDiscription(), "DO THE THING");
        assertEquals(mission.toString(), "DO THE THING");
        Player player2 = new Player(0, "Foo", 0, "bar");
        mission.setPlayer(player2);
        assertEquals(mission.getPlayer(), player2);
        mission.setNoofcountries(1);
        assertEquals(mission.getNoofcountries(), 1);
        mission.setNoofarmies(1);
        assertEquals(mission.getNoofarmies(), 1);
        mission.setContinent1(c2);
        assertEquals(mission.getContinent1(), c2);
        mission.setContinent2(c1);
        assertEquals(mission.getContinent2(), c1);
        mission.setContinent3(c1);
        assertEquals(mission.getContinent3(), c1);
        mission.setDiscription("");
         assertEquals(mission.toString(), "(Discription Missing)");
    }
}
