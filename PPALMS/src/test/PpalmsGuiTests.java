package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.Test;

import view.LMSInputStrategy;
import view.CodeInputStrategy;
import view.ViewStrategy;
import view.ProblemInputStrategy;
import view.PpalmsGui;

class PpalmsGuiTests {
	
	// Tests whether if the default "CodeInputStrategy" view strategy has been updated 
	@Test
	void testUpdateViewStrategy() {
	  PpalmsGui p = new PpalmsGui();
	  LMSInputStrategy l = new LMSInputStrategy();
	  p.updateViewStrategy(l);
	  assertEquals(true, p.getViewStrategy() instanceof LMSInputStrategy);
	}
	
	
	
}
