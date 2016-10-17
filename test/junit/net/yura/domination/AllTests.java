package junit.net.yura.domination;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.net.yura.domination.engine.RiskUIUtilTest;
import junit.net.yura.domination.engine.ai.AISimulationTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(RiskUIUtilTest.class);
		suite.addTestSuite(AISimulationTest.class);
//		suite.addTestSuite(RecipeBookTest.class);
//		suite.addTestSuite(RecipeTest.class);
		//$JUnit-END$
		return suite;
	}

}
