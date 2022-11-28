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
	 * testProcessInputInvalidInput
	 * 
	 * @brief This tests if no input is given, nothing is processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputInvalidInput() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(null, "test"));
	}

	/**
	 * testProcessInputSourceCodeExtensionSuccessful
	 * 
	 * @brief This tests the case where a source file with an acceptable file
	 *        extension is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeExtensionSuccessful() {
		PpalmsGui p = new PpalmsGui();
		//Tests with Python code extension
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test.py"), "sourceCodeExtension"));
		
		//Tests with Java code extension
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test.java"), "sourceCodeExtension"));
		
		//Tests with C++ "cpp" code extension
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test.cpp"), "sourceCodeExtension"));
		
		//Tests with C++ "cc" code extension
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test.cc"), "sourceCodeExtension"));
	
		//Tests with C code extension
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test.c"), "sourceCodeExtension"));
	}
	
	/**
	 * testProcessInputSourceCodeExtensionUnsuccessful
	 * 
	 * @brief This tests the case where a source file with an unacceptable file
	 *        extension is inputed and therefore not processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeExtensionUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		//Tests with .txt extension
		assertFalse(p.getPpalmsInputHandler().processInput(new JTextField("test.txt"), "sourceCodeExtension"));
		
		//Tests with .png extension
		assertFalse(p.getPpalmsInputHandler().processInput(new JTextField("test.png"), "sourceCodeExtension"));
		
		//Tests with .mp4 extension
		assertFalse(p.getPpalmsInputHandler().processInput(new JTextField("test.mp4"), "sourceCodeExtension"));
				
		//Tests with .ml extension
		assertFalse(p.getPpalmsInputHandler().processInput(new JTextField("test.ml"), "sourceCodeExtension"));
				
	}

	/**
	 * testProcessInputSourceCodeInputSuccessful
	 * 
	 * @brief This tests if source code has been inputed to the application and is
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("example_source.py"), "sourceCode"));
	}

	/**
	 * testProcessInputLmsTargetInputSuccessful
	 * 
	 * @brief This tests the case where a valid target LMS is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputLmsTargetInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(1); // Canvas- Valid argument
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
	}

	/**
	 * testProcessInputLmsTargetInputUnsuccessful
	 * 
	 * @brief This tests if an invalid target LMS is inputed and therefore not
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputLmsTargetInputUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
	}

	/**
	 * testProcessInputProblemTypeInputSuccessful
	 * 
	 * @brief This tests if a valid problem type is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemTypeInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(1); // Ordering- Valid argument
		assertTrue(p.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
	}

	/**
	 * testProcessInputProblemTypeInputUnsuccessful
	 * 
	 * @brief This tests if an invalid problem type is inputed and therefore not
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemTypeInputUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(p.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
	}

	/**
	 * testProcessInputProblemTtileInputSuccessful
	 * 
	 * @brief This tests if a Problem Title is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemTtileInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test title"), "problemTitle"));
	}

	/**
	 * testProcessInputProblemDescriptionInputSuccessful
	 * 
	 * @brief This tests if a Problem Description is inputed and processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemDescriptionInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextArea("test description: This is a PPALMS Problem"), "problemDescription"));
	}

	/**
	 * testProcessInputExportProblemInputSuccessful
	 * 
	 * @brief This tests if export problem is processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputExportProblemInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		
		problem.setSourceCode("test.py");
		List<String> sourceCodeLines = new ArrayList<String>();
			sourceCodeLines.add("def main:");
			sourceCodeLines.add("# Comment before print");
			sourceCodeLines.add("print('Hello World')");
		problem.setSourceCodeLines(sourceCodeLines);
		
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.Ordering);
		assertTrue(p.getPpalmsInputHandler().processInput(null, "exportProblem"));
	}

	/**
	 * testProcessInputSourceCodeLinesInput
	 * 
	 * @brief This tests if user input source code lines are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeLinesInput() {
		PpalmsGui p = new PpalmsGui();
		List<String> lines = Arrays.asList("void function()", "function(variable)", "x=null");
		assertTrue(p.getPpalmsInputHandler().processInput(lines));
	}

	/**
	 * testProcessInputAddAnnotationSuccessful
	 * 
	 * @brief This tests if user input annotations are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputAddAnnotationSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(0, "addAnnotation"));
	}

	/**
	 * testProcessInputAddAnnotationUnsuccessful
	 * 
	 * @brief This tests if user input annotations are not processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputAddAnnotationUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(0, "test"));
	}
}
