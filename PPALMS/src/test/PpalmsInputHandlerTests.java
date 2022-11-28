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
	 * testProcessInputSourceCodeInputUnsuccessful
	 * 
	 * @brief This tests if no source code has been inputed to the application and therefore nothing is
	 *        processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeInputUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		JTextField sourceCode = new JTextField();
		sourceCode.setText(null);
		assertFalse(p.getPpalmsInputHandler().processInput(sourceCode, "sourceCode"));
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
		
		//Tests with Canvas LMS
		lmsTargetComboBox.setSelectedIndex(1); 
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with D2L LMS
		lmsTargetComboBox.setSelectedIndex(2); 
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Absorb LMS
		lmsTargetComboBox.setSelectedIndex(3); 
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Matrix LMS
		lmsTargetComboBox.setSelectedIndex(4); 
		assertTrue(p.getPpalmsInputHandler().processInput(lmsTargetComboBox, "lmsTarget"));
		
		//Tests with Talent LMS
		lmsTargetComboBox.setSelectedIndex(5); 
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
		
		//Tests with Ordering Problem Type
		problemTypeComboBox.setSelectedIndex(1); 
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
	 * @brief This tests if a problem title is inputed and processed.
	 * In the processInput case for problemTitle, it will always return
	 * true because even when passing in a JTextField that is null, when it 
	 * does JComponenet.getText(), it processes it as an empty string "" 
	 * which is valid
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputProblemTtileInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("test title"), "problemTitle"));
		
		//No title is given. This will be the text area's
		//input if no title is given.
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField(""), "problemTitle"));
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
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextArea("test description: This is a PPALMS Problem"), "problemDescription"));
		
		//No description is given. This will be the text area's
		//input if no title is given.
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextArea(""), "problemDescription"));
	}

//	/**
//	 * testProcessInputExportProblemInputSuccessful
//	 * 
//	 * @brief This tests if export problem is processed
//	 * 
//	 * @author Stephanie Ye
//	 */
//	@Test
//	void testProcessInputExportProblemInputSuccessful() {
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
//	}

	/**
	 * testProcessInputSourceCodeLinesInputSuccessful
	 * 
	 * @brief This tests if user input source code lines are processed
	 * 
	 * @author Stephanie Ye
	 */
	@Test
	void testProcessInputSourceCodeLinesInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		List<String> lines = Arrays.asList("void function()", "function(variable)", "x=null");
		assertTrue(p.getPpalmsInputHandler().processInput(lines));
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
		PpalmsGui p = new PpalmsGui();
		
		//Test where source code is empty
		List<String> emptyCode = Collections.emptyList();
		assertFalse(p.getPpalmsInputHandler().processInput(emptyCode));
		
		//Test where source code is more than 50 lines
		List<String> lines = new ArrayList<>();
		for (int i = 0; i <51; i++) {
			lines.add("test");
		}
		assertFalse(p.getPpalmsInputHandler().processInput(lines));
	}

//	/**
//	 * testProcessInputAddAnnotationSuccessful
//	 * 
//	 * @brief This tests if valid user input annotations are processed
//	 * 
//	 * @author Stephanie Ye
//	 */
//	@Test
//	void testProcessInputAddAnnotationSuccessful() {
//		PpalmsGui p = new PpalmsGui();
//		assertTrue(p.getPpalmsInputHandler().processInput(0, "addAnnotation"));
//	}
//
//	/**
//	 * testProcessInputAddAnnotationUnsuccessful
//	 * 
//	 * @brief This tests if invalid user input annotations are not processed
//	 * 
//	 * @author Stephanie Ye
//	 */
//	@Test
//	void testProcessInputAddAnnotationUnsuccessful() {
//		PpalmsGui p = new PpalmsGui();
//		assertFalse(p.getPpalmsInputHandler().processInput(0, "test"));
//	}
}
