package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import model.LmsTarget;
import model.PpalmsProblem;
import model.ProblemType;
import view.LMSInputStrategy;
import view.ProblemInputStrategy;
import view.ChooseLinesAnnotation;
import view.PpalmsGui;

/**
 * PpalmsGuiTests
 * 
 * @brief This class holds all the unit tests 
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
		 PpalmsProblem problem = createValidPpalmsProblem();
		 gui.getPpalmsInputHandler().setProblem(problem);
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
		PpalmsProblem problem = createValidPpalmsProblem();
		gui.getPpalmsInputHandler().setProblem(problem);
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
	 * testOrderingAnnotationInterface
	 * 
	 * @brief Tests whether if clicking on 
	 * a line button for annotation would 
	 * result in the exportProblem button
	 * 
	 * @author Shen Lua
	 */
	@Test
	void testOrderingAnnotationInterface() {
		PpalmsGui gui = new PpalmsGui();
		gui.getPpalmsInputHandler().setProblem(createValidPpalmsProblem());
		gui.updateViewStrategy(new ProblemInputStrategy());
		JButton lineButton = ((ChooseLinesAnnotation) gui.getAnnotationInterface()).getFirstAnnotationLineButton();
		assertNotNull(lineButton);
		assertEquals(lineButton.getText(), "Hello");
		assertTrue(lineButton instanceof JButton);
		try {
			lineButton.doClick();
		} catch(UnsupportedOperationException e) {
			assertTrue(lineButton.getForeground().equals(Color.GREEN));
		}
	}
	
	/**
	 * testMultipleChoiceAnnotationInterface
	 * 
	 * @brief This tests the case where the Ordering problem type 
	 * is selected and the annotation interface does show up
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testMultipleChoiceAnnotationInterface() {
		PpalmsGui gui = new PpalmsGui();
		gui.getPpalmsInputHandler().setProblem(createValidMultipleChoicePpalmsProblem());
		gui.updateViewStrategy(new ProblemInputStrategy());
		JButton lineButton = ((ChooseLinesAnnotation) gui.getAnnotationInterface()).getFirstAnnotationLineButton();
		assertNotNull(lineButton);
		assertEquals(lineButton.getText(), "Hello");
		assertTrue(lineButton instanceof JButton);
		try {
			lineButton.doClick();
		} catch(UnsupportedOperationException e) {
			assertTrue(lineButton.getForeground().equals(Color.GREEN));
		}
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
	 * testOrderingProblemTypeSelection
	 * 
	 * @brief Tests the intended system behavior when the 
	 * problem type is and not selected and when the 
	 * Ordering problem type is selected
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
	
	/**
	 * testFillInBlankProblemTypeSelection
	 * 
	 * @brief Tests the intended system behavior when the 
	 * problem type is and not selected and the Fill In Blank
	 * problem type is selected
	 * 
	 * @author Stephanie Ye 
	 */
	@Test
	void testFillInBlankProblemTypeSelection() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0);
		assertFalse(confirmLmsTargetButton.isEnabled()); // disabled when none selected
		problemTypeComboBox.setSelectedIndex(2); // ordering
		assertTrue(confirmLmsTargetButton.isEnabled()); // enabled when selected
	}
	
	/**
	 * testMultipleChoiceProblemTypeSelection
	 * 
	 * @brief Tests the intended system behavior when the 
	 * problem type is and not selected and the Multiple 
	 * Choice problem type is selected
	 * 
	 * @author Stephanie Ye 
	 */
	@Test
	void testMultipleChoiceProblemTypeSelection() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0);
		assertFalse(confirmLmsTargetButton.isEnabled()); // disabled when none selected
		problemTypeComboBox.setSelectedIndex(2); // ordering
		assertTrue(confirmLmsTargetButton.isEnabled()); // enabled when selected
	}
	
	/**
	 * testNumberOfStudentsChangeListener
	 * 
	 * @brief Tests the change listener in the PpalmsGui
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testNumberOfStudentsChangeListener() {
		PpalmsGui gui = new PpalmsGui();
		gui.updateViewStrategy(new LMSInputStrategy());
		JSpinner numberOfStudentsSpinner = ((LMSInputStrategy) gui.getViewStrategy()).getNumberOfStudentsSpinner();
		numberOfStudentsSpinner.setEnabled(true);
		numberOfStudentsSpinner.setValue(10);
		
		assertEquals(10, gui.getPpalmsInputHandler().getProblem().getNumberOfStudents());
	}
	
	/**
	 * This is a private helper function that creates
	 * a sample PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidPpalmsProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		problem.setSourceCode("test.py");
		problem.setSourceCodeLines(List.of(new String("Hello")));
		problem.setAnnotations(Arrays.asList(31, 13, 45));
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.Ordering);
		return problem;
	}
	
	/**
	 * This is a private helper function that creates
	 * a sample PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidMultipleChoicePpalmsProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		problem.setSourceCode("test.py");
		problem.setSourceCodeLines(List.of(new String("Hello")));
		problem.setAnnotations(Arrays.asList(31, 13, 45));
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.MultipleChoice);
		return problem;
	}
	
}
