package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
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
 * This class holds all the unit tests 
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
	 * testUpdateViewStrategySuccess
	 * 
	 * @brief Tests whether if the view strategy has changed
	 * from the default "CodeInputStrategy" to "LMSInputStrategy"
	 * in this testing scenario when updateViewStrategy method 
	 * is invoked.  
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testUpdateViewStrategySuccess() {
		 PpalmsGui gui = new PpalmsGui();
		 // To prevent errors when transitioning from LMSInputStrategy to ProblemInputStrategy view
		 List<String> testSourceCodeLines = new ArrayList<String>();
		 testSourceCodeLines.add("int main() {printf('hello world')}");
		 gui.getPpalmsInputHandler().getProblem().setSourceCodeLines(testSourceCodeLines);
		 gui.updateViewStrategy(new LMSInputStrategy());
		 assertTrue(gui.getViewStrategy() instanceof LMSInputStrategy);
		 gui.updateViewStrategy(new ProblemInputStrategy());
		 assertTrue(gui.getViewStrategy() instanceof ProblemInputStrategy);
	}
	
	/**
	 * testProblemTitleDescriptionInput
	 * 
	 * @brief Tests whether both the Title and 
	 * Description JTextFields are operational and 
	 * accept user input. 
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testProblemTitleDescriptionInput() {
		PpalmsGui gui = new PpalmsGui();
		List<String> testSourceCodeLines = new ArrayList<String>();
		testSourceCodeLines.add("int main() {printf('hello world')}");
		gui.getPpalmsInputHandler().getProblem().setSourceCodeLines(testSourceCodeLines);
		gui.updateViewStrategy(new ProblemInputStrategy());
		JTextField titleInputTextField = ((ProblemInputStrategy) gui.getViewStrategy()).getTitleInputTextField();
		JTextArea descriptionInputTextField = ((ProblemInputStrategy) gui.getViewStrategy()).getDescriptionInputTextField();
		titleInputTextField.setText("testTitle");
		assertTrue(gui.getPpalmsInputHandler().processInput(titleInputTextField,"problemTitle"));
		descriptionInputTextField.setText("testDescription");
		assertTrue(gui.getPpalmsInputHandler().processInput(descriptionInputTextField, "problemDescription"));
	}
	
	/**
	 * testProblemAnnotation
	 * 
	 * @brief Tests whether if clicking on 
	 * a line button for annotation would 
	 * result in the exportProblem button
	 * to be enabled. 
	 * 
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testProblemAnnotation() {
		PpalmsGui gui = new PpalmsGui();
		List<String> testSourceCodeLines = new ArrayList<String>();
		testSourceCodeLines.add("int main() {printf('hello world')}");
		gui.getPpalmsInputHandler().getProblem().setSourceCodeLines(testSourceCodeLines);
		gui.updateViewStrategy(new ProblemInputStrategy());
		JButton exportProblem = ((ProblemInputStrategy) gui.getViewStrategy()).getExportProblem();
		List<String> alines = gui.getPpalmsInputHandler().getProblem().getSourceCodeLines();
		JButton lineButton = gui.getAnnotationLineButton(exportProblem, 0, alines.get(0));
		lineButton.doClick();
		lineButton.doClick(); 
		assertTrue(exportProblem.isEnabled()); 
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
	void testProblemTypeSelection() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0);
		assertFalse(confirmLmsTargetButton.isEnabled()); // disabled when none selected
		problemTypeComboBox.setSelectedIndex(1); // ordering
		assertTrue(confirmLmsTargetButton.isEnabled()); // enabled when selected
	}
	
}
