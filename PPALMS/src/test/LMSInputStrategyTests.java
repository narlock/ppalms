package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import view.LMSInputStrategy;

class LMSInputStrategyTests {

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
	
	@Test
	void testAllProblemTypesExistInProblemTypeComboBox() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertEquals("Expand", (l.getProblemTypeComboBox()).getItemAt(0));
		assertEquals("Ordering", (l.getProblemTypeComboBox()).getItemAt(1));
	}
	
	@Test
	void testControllerActionsSet() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setControllerActions();
		assertFalse(l.getProblemTypeComboBox().isEnabled());
		assertFalse(l.getConfirmLmsTargetButton().isEnabled());
  }
	
	@Test
	void testLmsTargetLabelExists() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertNotNull(l.getLmsTargetLabel());
	}
	
	@Test
	void testProblemTypeLabelExists() {
		LMSInputStrategy l = new LMSInputStrategy();
		l.setViewPanel();
		assertNotNull(l.getProblemTypeLabel());
	}
}
