package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import org.junit.jupiter.api.Test;

import model.LmsTarget;
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
	
//	// example_source.py
//	@Test
//	void testFileInputSuccessful() {
//		PpalmsGui p = new PpalmsGui();
//		JButton btn = ((CodeInputStrategy) p.getViewStrategy()).getCodeInputButton();
//		btn.doClick(); // simulate btn click
//		File inputFile = new File("src/test/example_source.py");
//		JFileChooser fileChooser = ((CodeInputStrategy) p.getViewStrategy()).getFileChooser();
//		int returnValue = 0;
//		assertEquals(0, JFileChooser.APPROVE_OPTION);
//	}
	
	@Test 
	void testTargetLMSNoneSelected(){
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		lmsTargetComboBox.setSelectedIndex(0); // Invalid Argument
		System.out.println(LmsTarget.Canvas.ordinal());
		assertEquals(false, problemTypeComboBox.isEnabled()); // not enabled
		assertEquals(false, confirmLmsTargetButton.isEnabled()); // not enabled
	}
	
	@Test
	void testTargetLMSCanvasSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(1); // Canvas
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	@Test
	void testTargetLMSD2LSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(2); // D2L
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	@Test
	void testTargetLMSAbsorbSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(3); // Absorb
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	@Test
	void testTargetLMSMatrixSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(4); // Matrix
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	@Test
	void testTargetLMSTalentSelected() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(5); // Talent
		assertEquals(true, problemTypeComboBox.isEnabled()); // enabled
	}
	
	@Test
	void testProblemTypeNotSelected() { // no problem type selected
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(0);
		assertEquals(false, confirmLmsTargetButton.isEnabled());
	}
	
	@Test
	void testProblemTypeSelected() { // ordering Problem type
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		problemTypeComboBox.setSelectedIndex(1);
		assertEquals(true, confirmLmsTargetButton.isEnabled());
	}
	
	@Test
	void testViewStrategyUpdatedAfterConfirmLmsTargetButton() {
		PpalmsGui p = new PpalmsGui();
		LMSInputStrategy l = new LMSInputStrategy();
		p.updateViewStrategy(l);
		JComboBox<String> lmsTargetComboBox = ((LMSInputStrategy) p.getViewStrategy()).getLmsTargetComboBox();
		JButton confirmLmsTargetButton = ((LMSInputStrategy) p.getViewStrategy()).getConfirmLmsTargetButton();
		JComboBox<String> problemTypeComboBox = ((LMSInputStrategy) p.getViewStrategy()).getProblemTypeComboBox();
		lmsTargetComboBox.setSelectedIndex(1);
		problemTypeComboBox.setSelectedIndex(1);
		confirmLmsTargetButton.doClick(); // once both lmsTargetComboBox & ProblemTypeComboBox have been selected, we simulate a click on the confirm button
		assertEquals(true, p.getViewStrategy() instanceof ProblemInputStrategy);
	}
	
	// When ViewStrategy is ProblemInputStrategy

	
	
	
}
