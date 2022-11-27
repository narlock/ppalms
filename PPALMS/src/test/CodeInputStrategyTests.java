package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.CodeInputStrategy;
import view.PpalmsGui;

class CodeInputStrategyTests {

	/**
	 * testCodeInputStrategyAttributesNotNull
	 * will ensure that the components of the
	 * CodeInputStrategy is not null.
	 * 
	 * @author narlock
	 * 
	 */
	@Test
	void testCodeInputStrategyAttributesNotNull() {
		CodeInputStrategy strategy = new CodeInputStrategy();
		assertNotNull(strategy.getCodeInputButton());
		assertNotNull(strategy.getCodeInputLabel());
		assertNotNull(strategy.getFileChooser());
	}
	
	/**
	 * testCodeInputStrategyAttributesNotNull
	 * will ensure that the component attributes
	 * are visually set correctly.
	 * 
	 * @author narlock
	 * 
	 */
	@Test
	void testCodeInputStrategyComponentAttributes() {
		CodeInputStrategy strategy = new CodeInputStrategy();
		assertEquals("Input Source Code", strategy.getCodeInputLabel().getText());
		assertEquals("Select File", strategy.getCodeInputButton().getText());
	}

}
