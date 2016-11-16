/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.yura.domination.engine.guishared;

import net.yura.domination.engine.guishared.MapMouseListener;
import net.yura.domination.engine.guishared.PicturePanel;
import net.yura.domination.engine.Risk;
import net.yura.domination.engine.core.RiskGame;
import junit.framework.TestCase;
import net.yura.domination.engine.RiskUtil;
import net.yura.domination.mobile.RiskMiniIO;
import org.junit.Test;

/**
 *
 * @author zack
 */
public class GuiSharedTest extends TestCase {
    
    public GuiSharedTest(String testName) {
        super(testName);
    }

    public void testSmoke() {
        assert(true);
    }
    
    public void testConstructor() throws Exception {
        RiskUtil.streamOpener = new RiskMiniIO();

        Risk myrisk = new Risk();
//        myrisk.start();
        PicturePanel pp = new PicturePanel(myrisk);
        MapMouseListener mml = new MapMouseListener(myrisk, pp);
        mml.mouseMoved(1, 1, 1);
    }
    
        
    public void testFoobar() throws Exception {
        RiskUtil.streamOpener = new RiskMiniIO();

        Risk myrisk = new Risk();
//        myrisk.start();
        PicturePanel pp = new PicturePanel(myrisk);
        MapMouseListener mml = new MapMouseListener(myrisk, pp);
        mml.mouseReleased(1, 1, 1);
    }
}
