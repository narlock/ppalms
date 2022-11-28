package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		PpalmsGui gui = new PpalmsGui();
		assertFalse(gui.getPpalmsInputHandler().processInput(null, "test"));
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
		PpalmsGui gui = new PpalmsGui();
		//Tests with Python code extension
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test.py"), "sourceCodeExtension"));
		
		//Tests with Java code extension
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test.java"), "sourceCodeExtension"));
		
		//Tests with C++ "cpp" code extension
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test.cpp"), "sourceCodeExtension"));
		
		//Tests with C++ "cc" code extension
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test.cc"), "sourceCodeExtension"));
	
		//Tests with C code extension
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test.c"), "sourceCodeExtension"));
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
		PpalmsGui gui = new PpalmsGui();
		//Tests with .txt extension
		assertFalse(gui.getPpalmsInputHandler().processInput(new JTextField("test.txt"), "sourceCodeExtension"));
		
		//Tests with .png extension
		assertFalse(gui.getPpalmsInputHandler().processInput(new JTextField("test.png"), "sourceCodeExtension"));
		
		//Tests with .mp4 extension
		assertFalse(gui.getPpalmsInputHandler().processInput(new JTextField("test.mp4"), "sourceCodeExtension"));
				
		//Tests with .ml extension
		assertFalse(gui.getPpalmsInputHandler().processInput(new JTextField("test.ml"), "sourceCodeExtension"));
				
	}

	/**
	 * testProcessInputSourceCodeInputUnsuccessful
	 * 
	 * @brief This tests if no source code has been inputed to the application and therefore nothing is
	 *        processed. This test will always return false because there is no return true in the switch
	 *        case and there is no check in setSourceCode() to return anything either. 
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeInputUnsuccessful() {
		PpalmsGui gui = new PpalmsGui();
		JTextField sourceCode = new JTextField();
		sourceCode.setText(null);
		assertFalse(gui.getPpalmsInputHandler().processInput(sourceCode, "sourceCode"));
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
		PpalmsGui gui = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy lms = new LMSInputStrategy();
		gui.updateViewStrategy(lms);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getLmsTargetComboBox();
		
		//Tests with Canvas LMS
		lmsTargetComboBox.setSelectedIndex(1); 
		assertTrue(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with D2L LMS
		lmsTargetComboBox.setSelectedIndex(2); 
		assertTrue(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Absorb LMS
		lmsTargetComboBox.setSelectedIndex(3); 
		assertTrue(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Matrix LMS
		lmsTargetComboBox.setSelectedIndex(4); 
		assertTrue(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Talent LMS
		lmsTargetComboBox.setSelectedIndex(5); 
		assertTrue(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
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
		PpalmsGui gui = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy lms = new LMSInputStrategy();
		gui.updateViewStrategy(lms);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
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
		PpalmsGui gui = new PpalmsGui();
		LMSInputStrategy lms = new LMSInputStrategy();
		gui.updateViewStrategy(lms);
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getProblemTypeComboBox();
		
		//Tests with Ordering Problem Type
		problemTypeComboBox.setSelectedIndex(1); 
		assertTrue(gui.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
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
		PpalmsGui gui = new PpalmsGui();
		LMSInputStrategy lms = new LMSInputStrategy();
		gui.updateViewStrategy(lms);
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0); // Invalid Argument
		assertFalse(gui.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType"));
	}

	/**
	 * testProcessInputProblemTtileInputSuccessful
	 * 
	 * @brief This tests if a problem title is inputed and processed.
	 * In the processInput case for problemTitle, it will always return
	 * true because even when passing in a JTextField that is null, when it 
	 * does JComponenet.getText(), it processes it as an empty string "" 
	 * which is valid
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemTtileInputSuccessful() {
		PpalmsGui gui = new PpalmsGui();
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField("test title"), "problemTitle"));
		
		//No title is given. This will be the text area's
		//input if no title is given.
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextField(""), "problemTitle"));
	}
	
	/**
	 * testProcessInputProblemDescriptionInputSuccessful
	 * 
	 * @brief This tests if a problem description is inputed and processed.
	 * In the processInput case for problemDescription, it will always return
	 * true because even when passing in a JTextArea that is null, when it 
	 * does JComponenet.getText(), it processes it as an empty string "" 
	 * which is valid
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemDescriptionInputSuccessful() {
		PpalmsGui gui = new PpalmsGui();
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextArea("test description: This is a PPALMS Problem"), "problemDescription"));
		
		//No description is given. This will be the text area's
		//input if no title is given.
		assertTrue(gui.getPpalmsInputHandler().processInput(new JTextArea(""), "problemDescription"));
	}

	/**
	 * testProcessInputExportProblemInputSuccessful
	 * 
	 * @brief This tests if export problem is processed given 
	 * an input PPALMS problem. 
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputExportProblemInputSuccessful() {
		PpalmsGui gui = new PpalmsGui();
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		LMSInputStrategy lms = new LMSInputStrategy();
		gui.updateViewStrategy(lms);
		
		//Creating a valid PPALMS Problem
		PpalmsProblem problem = new PpalmsProblem();
		
		gui.getPpalmsInputHandler().processInput(new JTextField("test.py"), "sourceCodeExtension");
		List<String> sourceCodeLines = new ArrayList<String>();
			sourceCodeLines.add("def main:");
			sourceCodeLines.add("# Comment before print");
			sourceCodeLines.add("print('Hello World')");
		gui.getPpalmsInputHandler().processInput(sourceCodeLines);
		
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getLmsTargetComboBox();
		lmsTargetComboBox.setSelectedIndex(1); // Set to Canvas LMS
		gui.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget");
		
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) gui.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(1); // Set to Ordering Problem Type
		gui.getPpalmsInputHandler().processInput(problemTypeComboBox, "problemType");
		
		gui.getPpalmsInputHandler().processInput(new JTextField("Test Title"), "problemTitle");
		gui.getPpalmsInputHandler().processInput(new JTextArea("Test Description"), "problemDescription");
		
		gui.getPpalmsInputHandler().processInput(0, "addAnnotation");
		gui.getPpalmsInputHandler().processInput(1, "addAnnotation");
	
		assertTrue(gui.getPpalmsInputHandler().processInput(null, "exportProblem"));
	}

	/**
	 * testProcessInputSourceCodeLinesInputSuccessful
	 * 
	 * @brief This tests if user input source code lines are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeLinesInputSuccessful() {
		PpalmsGui gui = new PpalmsGui();
		List<String> lines = Arrays.asList("void function()", "function(variable)", "x=null");
		assertTrue(gui.getPpalmsInputHandler().processInput(lines));
	}
	
	/**
	 * testProcessInputSourceCodeLinesInputUnsuccessful
	 * 
	 * @brief This tests if user inputs invalid source code 
	 * and so it is not processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeLinesInputUnsuccessful() {
		PpalmsGui gui = new PpalmsGui();
		
		//Test where source code is empty
		List<String> emptyCode = Collections.emptyList();
		assertFalse(gui.getPpalmsInputHandler().processInput(emptyCode));
		
		//Test where source code is more than 50 lines
		List<String> lines = new ArrayList<>();
		for (int i = 0; i <51; i++) {
			lines.add("test");
		}
		assertFalse(gui.getPpalmsInputHandler().processInput(lines));
	}

	/**
	 * testProcessInputAddAnnotationSuccessful
	 * 
	 * @brief This tests if valid user input annotations are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputAddAnnotationSuccessful() {
		PpalmsGui gui = new PpalmsGui();
		assertTrue(gui.getPpalmsInputHandler().processInput(0, "addAnnotation"));
	}

	/**
	 * testProcessInputAddAnnotationUnsuccessful
	 * 
	 * @brief This tests if event parameter is not valid for processInput of annotations  
	 * (i.e. not addAnnotation)
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputAddAnnotationUnsuccessful() {
		PpalmsGui gui = new PpalmsGui();
		assertFalse(gui.getPpalmsInputHandler().processInput(0, "test"));
	}
}
