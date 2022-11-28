package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CodeInputStrategyTests.class, LMSInputStrategyTests.class, PpalmsLogicHandlerTests.class, PpalmsGuiTests.class, PpalmsInputHandlerTests.class })
public class AllTests {

}
