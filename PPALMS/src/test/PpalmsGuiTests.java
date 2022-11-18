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
	
	@Test
	void testPpalmsGuiAttributesNotNull() {
		PpalmsGui p = new PpalmsGui();
		assertNotNull(p.viewStrategy);
		assertNotNull(p.controller);
	}
	
	@Test
	void testInstanceOfSetCommunicationActions() {
		PpalmsGui p = new PpalmsGui();
		assertThat(p.viewStrategy, instanceOf(CodeInputStrategy.class));
		assertThat(p.viewStrategy, instanceOf(LMSInputStrategy.class));
		assertThat(p.viewStrategy, instanceOf(ProblemInputStrategy.class));
	}
	
	// returnvalue = true , fileChooser.getSelectedFile() != null
	@Test
	void testReturnValueTrueAndSelectedFileNotNullSetCommunicationActions() {
		JFileChooser fileChooser = ((CodeInputStrategy) viewStrategy).getFileChooser();
		assertNotNull(JFileChooser.APPROVE_OPTION);
		assertNotNull(fileChooser.getSelectedFile());
	}
	
	// returnvalue = null, fileChooser.getSelectedFile() = null
	@Test
	void testReturnValueFalseAndSelectedFileNullSetCommunicationActions() {
		JFileChooser fileChooser = ((CodeInputStrategy) viewStrategy).getFileChooser();
		assertNull(JFileChooser.APPROVE_OPTION);
		assertNull(fileChooser.getSelectedFile());
		assertEquals(viewStrategy.showErrorDialog("No Source Code Selected"), )
	}
	
	// returnvalue = false, fileChooser.getSelectedFile() != null
	@Test
	void testReturnFalseAndSelectedFileNotNullSetCommunicationActions() {
		JFileChooser fileChooser = ((CodeInputStrategy) viewStrategy).getFileChooser();
		assertNull(JFileChooser.APPROVE_OPTION);
		assertNotNull(fileChooser.getSelectedFile());
	}
	
	// returnvalue = true, fileChooser.getSelectedFile() = null
	@Test 
	void testReturnTrueAndSelectedFileNullSetCommunicationActions() {
		JFileChooser fileChooser = ((CodeInputStrategy) viewStrategy).getFileChooser();
		assertEquals(1,JFileChooser.APPROVE_OPTION);
		assertNull(fileChooser.getSelectedFile());
	}
	
	// this.viewStrategy instanceof LMSInputStrategy = True
	@Test
	
}
