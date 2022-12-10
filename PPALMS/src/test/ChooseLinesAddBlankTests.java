package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.junit.jupiter.api.Test;

import model.LmsTarget;
import model.PpalmsProblem;
import model.ProblemType;
import view.ChooseLinesAddBlank;
import view.PpalmsGui;
import view.ProblemInputStrategy;

class ChooseLinesAddBlankTests {

	/**
	 * testLowerBoundSelection
	 * 
	 * @brief Tests lower bound selection for blank
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testLowerBoundSelection() {
		PpalmsGui gui = new PpalmsGui();
		PpalmsProblem problem = createValidFillInTheBlankPpalmsProblem();
		gui.getPpalmsInputHandler().setProblem(problem);
		gui.updateViewStrategy(new ProblemInputStrategy());
		assertTrue(gui.getViewStrategy() instanceof ProblemInputStrategy);
		gui.setCommunicationActions();
		
		ChooseLinesAddBlank annotationInterface = new ChooseLinesAddBlank(gui.getPpalmsInputHandler(), problem);
		annotationInterface.createAnnotationLineButtonPanel(new JButton(), 0, problem.getSourceCodeLines().get(0));
		
		JComboBox<Character> lowerBoundBox = annotationInterface.getProblemLineComboBoxIndex(0);
		assertNotNull(lowerBoundBox);
		assertEquals(2,annotationInterface.getProblemLineComboBox().size());
		for(int i = 0; i < problem.getSourceCodeLines().get(0).length(); i++) {
			lowerBoundBox.addItem(problem.getSourceCodeLines().get(0).charAt(i));
		}
		lowerBoundBox.setSelectedIndex(0);
		assertEquals('H', lowerBoundBox.getSelectedItem());
	}
	
	/**
	 * testUpperBoundSelection
	 * 
	 * @brief Tests upper bound selection for blank
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testUpperBoundSelection() {
		PpalmsGui gui = new PpalmsGui();
		PpalmsProblem problem = createValidFillInTheBlankPpalmsProblem();
		gui.getPpalmsInputHandler().setProblem(problem);
		gui.updateViewStrategy(new ProblemInputStrategy());
		assertTrue(gui.getViewStrategy() instanceof ProblemInputStrategy);
		gui.setCommunicationActions();
		
		ChooseLinesAddBlank annotationInterface = new ChooseLinesAddBlank(gui.getPpalmsInputHandler(), problem);
		annotationInterface.createAnnotationLineButtonPanel(new JButton(), 0, problem.getSourceCodeLines().get(0));
		
		JComboBox<Character> upperBoundBox = annotationInterface.getProblemLineComboBoxIndex(0);
		assertNotNull(upperBoundBox);
		assertEquals(2,annotationInterface.getProblemLineComboBox().size());
		for(int i = 0; i < problem.getSourceCodeLines().get(0).length(); i++) {
			upperBoundBox.addItem(problem.getSourceCodeLines().get(0).charAt(i));
		}
		upperBoundBox.setSelectedIndex(0);
		assertEquals('H', upperBoundBox.getSelectedItem());
	}
	
	/**
	 * This is a private helper function that creates
	 * a sample PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidFillInTheBlankPpalmsProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		problem.setSourceCode("test.py");
		problem.setSourceCodeLines(List.of(new String("Hello")));
		problem.setAnnotations(Arrays.asList(31, 13, 45));
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.FillInTheBlank);
		return problem;
	}
}
