package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import controller.PpalmsInputHandler;
import model.ProblemType;
import view.PpalmsGui;
import view.ProblemInputStrategy;
import view.CodeInputStrategy;
import view.LMSInputStrategy;

public class PpalmsInputHandlerTests {

	
	@Test
	void testInvalidInput() {
		PpalmsGui p = new PpalmsGui();
		assertFalse(p.getPpalmsInputHandler().processInput(null, "test"));
	}
	
	@Test
	void testSourceCodeInputSuccessful() {
		
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
		
	}
	
	
	
}
