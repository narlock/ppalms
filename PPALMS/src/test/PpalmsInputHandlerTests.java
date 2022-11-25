package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import controller.PpalmsInputHandler;

public class PpalmsInputHandlerTests {

	
	@Test
	void testSourceCodeExtensionSuccessful() {
		PpalmsInputHandler p = new PpalmsInputHandler();
		assertTrue(p.processInput(new JTextField("/test"), "sourceCodeExtension"));
		// either returns true, or nothing at all. 
	}
	
}
