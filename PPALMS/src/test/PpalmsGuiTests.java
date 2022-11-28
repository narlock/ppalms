package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import controller.PpalmsInputHandler;
import model.LmsTarget;
import view.LMSInputStrategy;
import view.CodeInputStrategy;
import view.ViewStrategy;
import view.ProblemInputStrategy;
import view.PpalmsGui;

/**
 * PpalmsGuiTests
 * 
 * This class holds all the unite tests 
 * for the PpalmsGuiTests class. 
 * 
 * The following unit tests are specified
 * in the PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class. 
 * 
 * @author Shen
 *
 */
class PpalmsGuiTests {
	
	/**
	 * testUpdateViewStrategy
	 * 
	 * @brief Tests whether if the view strategy has changed
	 * from the default "CodeInputStrategy" to "LMSInputStrategy"
	 * in this testing scenario when updateViewStrategy method 
	 * is invoked.  
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testUpdateViewStrategy() {
	  PpalmsGui p = new PpalmsGui();
	  LMSInputStrategy l = new LMSInputStrategy();
	  p.updateViewStrategy(l);
	  assertEquals(true, p.getViewStrategy() instanceof LMSInputStrategy);
	}
	
	@Test
	void testFileInputSuccessful() {
		//Functional test, done manually
	}
	
	@Test
	void testNoFileSelected() {
		//Functional test, done manually
	}
	
	
	/**
	 * testSettargetLMSSuccess
	 * 
	 * @brief Tests the intended behavior of the
	 * LMSInputStrategy when no lms target is 
	 * selected and when a lms target is selected
	 * from the lmsTargetComboBox. 
	 * 
	 * @author Shen Lua
	 * 
	 */
	@Test
	void testSetTargetLMSSuccess() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(0); // Default
		assertFalse(problemTypeComboBox.isEnabled());
		lmsTargetComboBox.setSelectedIndex(1); // Canvas
		assertTrue(problemTypeComboBox.isEnabled());
		lmsTargetComboBox.setSelectedIndex(2); // D2L
		assertTrue(problemTypeComboBox.isEnabled());
		lmsTargetComboBox.setSelectedIndex(3); // Absorb
		assertTrue(problemTypeComboBox.isEnabled());
		lmsTargetComboBox.setSelectedIndex(4); // Matrix
		assertTrue(problemTypeComboBox.isEnabled());
		lmsTargetComboBox.setSelectedIndex(5); // Talent
		assertTrue(problemTypeComboBox.isEnabled());
	}
	
	/**
	 * testProblemTypeNotSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * problem type is and not selected. 
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testProblemTypeNotSelected() { // no problem type selected
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0);
		assertFalse(confirmLmsTargetButton.isEnabled());
		problemTypeComboBox.setSelectedIndex(1); // ordering
		assertTrue(confirmLmsTargetButton.isEnabled());
	}
	
}
