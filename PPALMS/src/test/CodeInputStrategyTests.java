package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.CodeInputStrategy;
import view.PpalmsGui;

class CodeInputStrategyTests {

	/**
	 *  This tests that each component of the strategy has been initialized
	 */
	@Test
	void testCodeInputStrategyAttributesNotNull() {
		CodeInputStrategy strategy = new CodeInputStrategy();
		assertNotNull(strategy.getCodeInputButton());
		assertNotNull(strategy.getCodeInputLabel());
		assertNotNull(strategy.getFileChooser());
	}

}
