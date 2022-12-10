package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * AllTests
 * 
 * @brief Purpose of this class is to provide a JUnit
 * testing suite such that the engineer can run
 * the tests of all of the classes in the PPALMS
 * application.
 * 
 * @author Anthony Narlock
 *
 */
@Suite
@SelectClasses({ 
		CodeInputStrategyTests.class, 
		LMSInputStrategyTests.class, 
		PpalmsLogicHandlerTests.class, 
		PpalmsGuiTests.class, 
		PpalmsInputHandlerTests.class,
		ProblemInputStrategyTests.class,
		OrderingCreationTests.class,
		ChooseLinesAddBlankTests.class
	})
public class AllTests {

}
