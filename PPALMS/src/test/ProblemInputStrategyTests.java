package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.CodeInputStrategy;
import view.ProblemInputStrategy;

/**
 * ProblemInputStrategyTest
 * 
 * This class holds all of the unit tests
 * for the ProblemInputStrategy class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Anthony Narlock
 *
 */
class ProblemInputStrategyTests {

	/**
	 * testProblemInputStrategyAttributesNotNull
	 * @brief This test will ensure that the components 
	 * of the CodeInputStrategy is not null.
	 * 
	 * @author Anthony Narlock
	 * 
	 */
	@Test
	void testCodeInputStrategyAttributesNotNull() {
		ProblemInputStrategy strategy = new ProblemInputStrategy();
		assertNotNull(strategy.getTitleInputLabel());
		assertNotNull(strategy.getTitleInputTextField());
		assertNotNull(strategy.getDescriptionInputLabel());
		assertNotNull(strategy.getDescriptionInputTextField());
		assertNotNull(strategy.getExportProblem());
		assertNotNull(strategy.getAnnotationsLabel());
		assertNotNull(strategy.getAnnotationPanel());
	}
	
	/**
	 * testProblemInputStrategyAttributesNotNull
	 * will ensure that the component attributes
	 * are visually set correctly.
	 * 
	 * @author Anthony Narlock
	 * 
	 */
	@Test
	void testCodeInputStrategyComponentAttributes() {
		ProblemInputStrategy strategy = new ProblemInputStrategy();
		assertEquals("Problem Title", strategy.getTitleInputLabel().getText());
		assertEquals("", strategy.getTitleInputTextField().getText());
		assertEquals("Problem Description", strategy.getDescriptionInputLabel().getText());
		assertEquals("", strategy.getDescriptionInputTextField().getText());
		assertEquals("Annotations", strategy.getAnnotationsLabel().getText());
		assertEquals("Export Problem", strategy.getExportProblem().getText());
	}

}
