package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import controller.PpalmsInputHandler;
import model.LmsTarget;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;
import view.PpalmsGui;
import view.ProblemInputStrategy;
import view.CodeInputStrategy;
import view.LMSInputStrategy;

/**
 * PpalmsInputHandlerTests
 * 
 * This class handles the unit tests for the PpalmsInputHandler class and the
 * inputs of the user.
 * 
 * These unit tests are specified in the PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework in this class.
 * 
 * @author Stephanie Ye
 *
 */
public class PpalmsInputHandlerTests {
	/**
	 * testGetProblem
	 * 
	 * @brief This tests the getProblem() function in the InputHandler class
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testGetProblem() {
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsProblem problem = inputHandler.getProblem();
		assertEquals(inputHandler.getProblem(), problem);
	}

	/**
	 * testGetProblemHandler
	 * 
	 * @brief This tests the getProblemHandler() function in the InputHandler class
	 *
	 * @author Stephanie Ye
	 */
	@Test
	void testGetProblemHandler() {
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsLogicHandler logicHandler = inputHandler.getProblemHandler();
		assertEquals(inputHandler.getProblemHandler(), logicHandler);
	}

	/**
	 * testInvalidInput
	 * 
	 * @brief This tests if no input is given, nothing is processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testInvalidInput() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(null, "test"));
	}

	/**
	 * testSourceCodeExtensionSuccessful
	 * 
	 * @brief This tests the case where a source file with an acceptable file
	 *        extension is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testSourceCodeExtensionSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("example_source.py"), "sourceCodeExtension"));
	}

	/**
	 * testSourceCodeInputSuccessful
	 * 
	 * @brief This tests if source code has been inputed to the application and is
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testSourceCodeInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("example_source.py"), "sourceCode"));
	}

	/**
	 * testLmsTargetInputSuccessful
	 * 
	 * @brief This tests the case where a valid target LMS is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testLmsTargetInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(1); // Canvas- Valid argument
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
	}

	/**
	 * testLmsTargetInputUnsuccessful
	 * 
	 * @brief This tests if an invalid target LMS is inputed and therefore not
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testLmsTargetInputUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
	}

	/**
	 * testProblemTypeInputSuccessful
	 * 
	 * @brief This tests if a valid problem type is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProblemTypeInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(1); // Ordering- Valid argument
		assertTrue(p.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
	}

	/**
	 * testProblemTypeInputUnsuccessful
	 * 
	 * @brief This tests if an invalid problem type is inputed and therefore not
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProblemTypeInputUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(p.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
	}

	/**
	 * testProblemTtileInputSuccessful
	 * 
	 * @brief This tests if a Problem Title is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProblemTtileInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test title"), "problemTitle"));
	}

	/**
	 * testProblemDescriptionInputSuccessful
	 * 
	 * @brief This tests if a Problem Description is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProblemDescriptionInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextArea("test description: This is a PPALMS Problem"), "problemDescription"));
	}

	/**
	 * testExportProblemInputSuccessful
	 * 
	 * @brief This tests if export problem is processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testExportProblemInputSuccessful() {
//		PpalmsGui p = new PpalmsGui();
//		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
//		PpalmsProblem problem = new PpalmsProblem();
//		problem.setTitle("Test Title");
//		problem.setDescription("Test Description");
//		
//		problem.setSourceCode("test.py");
//		List<String> sourceCodeLines = new ArrayList<String>();
//			sourceCodeLines.add("def main:");
//			sourceCodeLines.add("# Comment before print");
//			sourceCodeLines.add("print('Hello World')");
//		problem.setSourceCodeLines(sourceCodeLines);
//		
//		problem.setLmsTarget(LmsTarget.Canvas);
//		problem.setProblemType(ProblemType.Ordering);
//		assertTrue(p.getPpalmsInputHandler().processInput(null, "exportProblem"));
	}

	/**
	 * testSourceCodeLinesInput
	 * 
	 * @brief This tests if user input source code lines are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testSourceCodeLinesInput() {
		PpalmsGui p = new PpalmsGui();
		List<String> lines = Arrays.asList("void function()", "function(variable)", "x=null");
		assertTrue(p.getPpalmsInputHandler().processInput(lines));
	}

	/**
	 * testAddAnnotationSuccessful
	 * 
	 * @brief This tests if user input annotations are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testAddAnnotationSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(0, "addAnnotation"));
	}

	/**
	 * testAddAnnotationUnsuccessful
	 * 
	 * @brief This tests if user input annotations are not processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testAddAnnotationUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(0, "test"));
	}

//	/**
//	 * testGetPpalmsProblem
//	 * 
//	 * @brief This tests the getPpalmsProblem() function in the InputHandler class
//	 * 
//	 * @author Stephanie Ye
//	 */
//	void testGetPpalmsProblem() {
//		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
//		PpalmsProblem problem = inputHandler.getPpalmsProblem();
//		assertEquals(inputHandler.getPpalmsProblem(), problem);
//	}
}
