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
	
	
	/**
	 * testTargetLMSNoneSelected
	 * 
	 * @brief Tests the intended behavior of the LMSInputStrategy
	 * view, whereby given that no target LMS is selected, the 
	 * problemType ComboBox and confirmLmsTarget button would not be
	 * enabled. 
	 * 
	 * @author Shen Lua 
	 */
	@Test 
	void testTargetLMSNoneSelected(){
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		lmsTargetComboBox.setSelectedIndex(0); // Invalid Argument
		assertEquals(false, problemTypeComboBox.isEnabled()); // not enabled
		assertEquals(false, confirmLmsTargetButton.isEnabled()); // not enabled
	}
	
	/**
	 * testTargetLMSCanvasSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * LMS target "Canvas" was selected. 
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testTargetLMSCanvasSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(1); // Canvas
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	/**
	 * testTargetLMSD2LSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * LMS target "D2L" was selected. 
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testTargetLMSD2LSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(2); // D2L
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	/**
	 * testTargetLMSAbsorbSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * LMS target "Absorb" was selected. 
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testTargetLMSAbsorbSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(3); // Absorb
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	/**
	 * testTargetLMSMatrixSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * LMS target "Matrix" was selected. 
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testTargetLMSMatrixSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(4); // Matrix
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	/**
	 * testTargetLMSTalentSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * LMS target "Talent" was selected. 
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testTargetLMSTalentSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(5); // Talent
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	/**
	 * testProblemTypeNotSelected
	 * 
	 * @brief Tests the intended system behavior when the 
	 * problem type is not selected. 
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
		assertEquals(false, confirmLmsTargetButton.isEnabled());
	}
	
	/**
	 * testProblemTypeSelected
	 * 
	 * @brief Tests the intended system behavior when 
	 * a problem type is selected from the combobox
	 * 
	 * @author Shen Lua 
	 */
	@Test
	void testProblemTypeSelected() { // ordering Problem type
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(1);
		assertEquals(true, confirmLmsTargetButton.isEnabled());
	}
	
}
