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
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import controller.PpalmsInputHandler;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;
import view.PpalmsGui;
import view.ProblemInputStrategy;
import view.CodeInputStrategy;
import view.LMSInputStrategy;

/**
 * 
 * PpalmsInputHandler serves as the controller of the
 * PPALMS application. This class handles the unit tests for the PpalmsInputHandler class and 
 * the inputs of the user.
 * @author Stephanie Ye
 *
 */
public class PpalmsInputHandlerTests {
	/**
	 * testGetProblem()
	 * This tests the getProblem() function in the InputHandler class
	 * @author Stephanie Ye
	 */
	@Test 
	void testGetProblem() {
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsProblem problem = inputHandler.getProblem();
		assertEquals(inputHandler.getProblem(), problem);
	}
	
	/**
	 * testGetProblemHandler()
	 * This tests the getProblemHandler() function in the InputHandler class
	 * @author Stephanie Ye
	 */
	@Test
	void testGetProblemHandler() {
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsLogicHandler logicHandler = inputHandler.getProblemHandler();
		assertEquals(inputHandler.getProblemHandler(), logicHandler);
	}
	
	/**
	 * testInvalidInput()
	 * This tests if no input is given
	 * @author Stephanie Ye 
	 */
	@Test
	void testInvalidInput() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(null, "test"));
	}
	
	/**
	 * testSourceCodeExtensionSuccessful() 
	 * This tests the case where 
	 */
	@Test
	void testSourceCodeExtensionSuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsProblem problem = new PpalmsProblem();
		PpalmsLogicHandler handler = new PpalmsLogicHandler();
		assertTrue(p.getPpalmsInputHandler().processInput(new JTextField("example_source.py"), "sourceCodeExtension"));
	}
	
	@Test
	void testSourceCodeInputSuccessful() {
		PpalmsGui p = new PpalmsGui();
		PpalmsProblem problem = new PpalmsProblem();
		PpalmsLogicHandler handler = new PpalmsLogicHandler();
		p.getPpalmsInputHandler().processInput(new JTextField("example_source.py"), "sourceCode");
//		assert
	}
	
	@Test
	void testLmsTargetInputSuccessful() {
		
	}
	
	@Test
	void testLmsTargetInputUnsuccessful() {
		
	}
	
	@Test
	void testProblemTypeInputSuccessful() {
		
	}
	
	@Test
	void testProblemTypeInputUnsuccessful() {
	
	}
	
	@Test
	// used the Robot class to generate native system input events
	void testProblemTtileInputSuccessful() {
	
	}
	
	@Test
	void testProblemDescriptionInputSuccessful() {
		
	}
	
	@Test
	void testExportProblemInputSuccessful() {
		
	}
	
	@Test
	void testSourceCodeLinesInput() {
		PpalmsGui p = new PpalmsGui();
		List<String> lines = Arrays.asList("void function()", "function(variable)", "x=null");
		assertTrue(p.getPpalmsInputHandler().processInput(lines));
	}
	
	
	@Test
	void testAddAnnotationSuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertTrue(p.getPpalmsInputHandler().processInput(0, "addAnnotation"));
	}
	
	@Test
	void testAddAnnotationUnsuccessful() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(0, "test"));
	}
	
	void testGetPpalmsProblem() {
		PpalmsInputHandler inputHandler = new PpalmsInputHandler();
		PpalmsProblem problem = inputHandler.getPpalmsProblem();
//		assertEquals(inputHandler.getPpalmsProblem(), problem);
	}
}
