package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.CodeInputStrategy;

/**
 * CodeInputStrategyTests
 * 
 * @brief This class holds all of the unit tests
 * for the CodeInputStrategy class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Anthony Narlock
 *
 */
class CodeInputStrategyTests {

	/**
	 * testCodeInputStrategyAttributesNotNull
	 * 
	 * @brief This test will ensure that the components 
	 * of the CodeInputStrategy is not null.
	 * 
	 * @author Anthony Narlock
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
	 * @author Anthony Narlock
	 * 
	 */
	@Test
	void testCodeInputStrategyComponentAttributes() {
		CodeInputStrategy strategy = new CodeInputStrategy();
		assertEquals("Input Source Code", strategy.getCodeInputLabel().getText());
		assertEquals("Select File", strategy.getCodeInputButton().getText());
	}

}
