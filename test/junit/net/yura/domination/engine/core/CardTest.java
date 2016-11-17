/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.core;

import junit.framework.TestCase;
import static net.yura.domination.engine.core.Card.INFANTRY;

/**
 *
 * @author brett
 */
public class CardTest extends TestCase  {
    public void testWithNullCountry() {
        Card card = new Card(Card.INFANTRY, null);
        assertEquals(card.getName(), "Infantry");
        assertEquals(card.toString(), "Infantry");
    }
    
    public void testWithCountry() {
        Country country = new Country(100, "foobar", "foobar", new Continent("sdf", "asdf", 0, 0), 0, 0);
        Card card = new Card(Card.INFANTRY, country);
        assertEquals(card.getCountry(), country);
        card.toString();
    }
}
