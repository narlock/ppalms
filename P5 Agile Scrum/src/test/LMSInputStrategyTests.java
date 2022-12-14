package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.LMSInputStrategy;

/**
 * ProblemInputStrategyTests
 * 
 * @brief This class holds all of the unit tests
 * for the ProblemInputStrategyTests class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Shen Lua
 *
 */
class LMSInputStrategyTests {

	/**
	 * testAllLMSOptionsExistInLMSTargetComboBox
	 * 
	 * @brief To ensure all LMS target options are available 
	 * for selection in the ComboBox
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testAllLMSOptionsExistInLMSTargetComboBox() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		
		assertEquals("Expand", (l.getLmsTargetComboBox()).getItemAt(0));
		assertEquals("Canvas", (l.getLmsTargetComboBox()).getItemAt(1));
		assertEquals("D2L", (l.getLmsTargetComboBox()).getItemAt(2));
		assertEquals("Absorb", (l.getLmsTargetComboBox()).getItemAt(3));
		assertEquals("Matrix", (l.getLmsTargetComboBox()).getItemAt(4));
		assertEquals("Talent", (l.getLmsTargetComboBox()).getItemAt(5));
	}
	
	/**
	 * testAllProblemTypesExistInProblemTypeComboBox
	 * To ensure all problem type options are available 
	 * for selection in the ComboBox.
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testAllProblemTypesExistInProblemTypeComboBox() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertEquals("Expand", (l.getProblemTypeComboBox()).getItemAt(0));
		assertEquals("Ordering", (l.getProblemTypeComboBox()).getItemAt(1));
		assertEquals("FillInTheBlank", (l.getProblemTypeComboBox().getItemAt(2)));
		assertEquals("MultipleChoice", (l.getProblemTypeComboBox().getItemAt(3)));
	}
	
	/**
	 * testControllerActionsSet
	 * To ensure default controller behavior, disables both ProblemType 
	 * ComboBox and confirmLmsTargetButton until further action.
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testControllerActionsSet() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setControllerActions();
		assertFalse(l.getProblemTypeComboBox().isEnabled());
		assertFalse(l.getConfirmLmsTargetButton().isEnabled());
	}
	
	/**
	 * testLmsTargetLabelExists
	 * To ensure that target LMS label exists so as to guide the user.
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testLmsTargetLabelExists() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertNotNull(l.getLmsTargetLabel());
	}
	
	/**
	 * testProblemTypeLabelExists
	 * To ensure that problem type labels are present for the user.
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testProblemTypeLabelExists() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertNotNull(l.getProblemTypeLabel());
	}
	
}
