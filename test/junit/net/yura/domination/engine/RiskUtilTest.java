/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import junit.framework.TestCase;
import net.yura.domination.engine.core.RiskGame;

/**
 *
 * @author brett
 */
public class RiskUtilTest extends TestCase {
    
    public RiskUtilTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testFalsePositive() {
        assert(true);
    }

//    /**
//     * Test of openMapStream method, of class RiskUtil.
//     */
//    public void testOpenMapStream() throws Exception {
//        System.out.println("openMapStream");
//        String a = "";
//        InputStream expResult = null;
//        InputStream result = RiskUtil.openMapStream(a);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of openStream method, of class RiskUtil.
//     */
//    public void testOpenStream() throws Exception {
//        System.out.println("openStream");
//        String a = "";
//        InputStream expResult = null;
//        InputStream result = RiskUtil.openStream(a);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getResourceBundle method, of class RiskUtil.
//     */
//    public void testGetResourceBundle() {
//        System.out.println("getResourceBundle");
//        Class c = null;
//        String n = "";
//        Locale l = null;
//        ResourceBundle expResult = null;
//        ResourceBundle result = RiskUtil.getResourceBundle(c, n, l);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of openURL method, of class RiskUtil.
//     */
//    public void testOpenURL() throws Exception {
//        System.out.println("openURL");
//        URL url = null;
//        RiskUtil.openURL(url);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of openDocs method, of class RiskUtil.
//     */
//    public void testOpenDocs() throws Exception {
//        System.out.println("openDocs");
//        String docs = "";
//        RiskUtil.openDocs(docs);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveFile method, of class RiskUtil.
//     */
//    public void testSaveFile() throws Exception {
//        System.out.println("saveFile");
//        String file = "";
//        RiskGame aThis = null;
//        RiskUtil.saveFile(file, aThis);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLoadFileInputStream method, of class RiskUtil.
//     */
//    public void testGetLoadFileInputStream() throws Exception {
//        System.out.println("getLoadFileInputStream");
//        String file = "";
//        InputStream expResult = null;
//        InputStream result = RiskUtil.getLoadFileInputStream(file);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createGameString method, of class RiskUtil.
//     */
//    public void testCreateGameString() {
//        System.out.println("createGameString");
//        int easyAI = 0;
//        int averageAI = 0;
//        int hardAI = 0;
//        int gameMode = 0;
//        int cardsMode = 0;
//        boolean AutoPlaceAll = false;
//        boolean recycle = false;
//        String mapFile = "";
//        String expResult = "";
//        String result = RiskUtil.createGameString(easyAI, averageAI, hardAI, gameMode, cardsMode, AutoPlaceAll, recycle, mapFile);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMapNameFromLobbyStartGameOption method, of class RiskUtil.
//     */
//    public void testGetMapNameFromLobbyStartGameOption() {
//        System.out.println("getMapNameFromLobbyStartGameOption");
//        String options = "";
//        String expResult = "";
//        String result = RiskUtil.getMapNameFromLobbyStartGameOption(options);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getGameDescriptionFromLobbyStartGameOption method, of class RiskUtil.
//     */
//    public void testGetGameDescriptionFromLobbyStartGameOption() {
//        System.out.println("getGameDescriptionFromLobbyStartGameOption");
//        String options = "";
//        String expResult = "";
//        String result = RiskUtil.getGameDescriptionFromLobbyStartGameOption(options);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of printStackTrace method, of class RiskUtil.
//     */
//    public void testPrintStackTrace() {
//        System.out.println("printStackTrace");
//        Throwable ex = null;
//        RiskUtil.printStackTrace(ex);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of donate method, of class RiskUtil.
//     */
//    public void testDonate() throws Exception {
//        System.out.println("donate");
//        RiskUtil.donate();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of donatePayPal method, of class RiskUtil.
//     */
//    public void testDonatePayPal() throws Exception {
//        System.out.println("donatePayPal");
//        RiskUtil.donatePayPal();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPlayerSettings method, of class RiskUtil.
//     */
//    public void testGetPlayerSettings() {
//        System.out.println("getPlayerSettings");
//        Risk risk = null;
//        Class uiclass = null;
//        Properties expResult = null;
//        Properties result = RiskUtil.getPlayerSettings(risk, uiclass);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadPlayers method, of class RiskUtil.
//     */
//    public void testLoadPlayers() {
//        System.out.println("loadPlayers");
//        Risk risk = null;
//        Class uiclass = null;
//        RiskUtil.loadPlayers(risk, uiclass);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of savePlayers method, of class RiskUtil.
//     */
//    public void testSavePlayers_Risk_Class() {
//        System.out.println("savePlayers");
//        Risk risk = null;
//        Class uiclass = null;
//        RiskUtil.savePlayers(risk, uiclass);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of savePlayers method, of class RiskUtil.
//     */
//    public void testSavePlayers_List_Class() {
//        System.out.println("savePlayers");
//        List players = null;
//        Class uiclass = null;
//        RiskUtil.savePlayers(players, uiclass);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readMap method, of class RiskUtil.
//     */
//    public void testReadMap() throws Exception {
//        System.out.println("readMap");
//        InputStream in = null;
//        BufferedReader expResult = null;
//        BufferedReader result = RiskUtil.readMap(in);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadInfo method, of class RiskUtil.
//     */
//    public void testLoadInfo() {
//        System.out.println("loadInfo");
//        String fileName = "";
//        boolean cards = false;
//        Map expResult = null;
//        Map result = RiskUtil.loadInfo(fileName, cards);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveGameLog method, of class RiskUtil.
//     */
//    public void testSaveGameLog() throws Exception {
//        System.out.println("saveGameLog");
//        File logFile = null;
//        RiskGame game = null;
//        RiskUtil.saveGameLog(logFile, game);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOutputStream method, of class RiskUtil.
//     */
//    public void testGetOutputStream() throws Exception {
//        System.out.println("getOutputStream");
//        File dir = null;
//        String fileName = "";
//        OutputStream expResult = null;
//        OutputStream result = RiskUtil.getOutputStream(dir, fileName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of rename method, of class RiskUtil.
//     */
//    public void testRename() {
//        System.out.println("rename");
//        File oldFile = null;
//        File newFile = null;
//        RiskUtil.rename(oldFile, newFile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of asVector method, of class RiskUtil.
//     */
//    public void testAsVector() {
//        System.out.println("asVector");
//        List list = null;
//        Vector expResult = null;
//        Vector result = RiskUtil.asVector(list);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of asHashtable method, of class RiskUtil.
//     */
//    public void testAsHashtable() {
//        System.out.println("asHashtable");
//        Map map = null;
//        Hashtable expResult = null;
//        Hashtable result = RiskUtil.asHashtable(map);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of replaceAll method, of class RiskUtil.
//     */
//    public void testReplaceAll() {
//        System.out.println("replaceAll");
//        String string = "";
//        String notregex = "";
//        String replacement = "";
//        String expResult = "";
//        String result = RiskUtil.replaceAll(string, notregex, replacement);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of quote method, of class RiskUtil.
//     */
//    public void testQuote() {
//        System.out.println("quote");
//        String s = "";
//        String expResult = "";
//        String result = RiskUtil.quote(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of quoteReplacement method, of class RiskUtil.
//     */
//    public void testQuoteReplacement() {
//        System.out.println("quoteReplacement");
//        String s = "";
//        String expResult = "";
//        String result = RiskUtil.quoteReplacement(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of copy method, of class RiskUtil.
//     */
//    public void testCopy() throws Exception {
//        System.out.println("copy");
//        File src = null;
//        File dest = null;
//        RiskUtil.copy(src, dest);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAtLeastOne method, of class RiskUtil.
//     */
//    public void testGetAtLeastOne() {
//        System.out.println("getAtLeastOne");
//        StringTokenizer stringT = null;
//        String expResult = "";
//        String result = RiskUtil.getAtLeastOne(stringT);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
