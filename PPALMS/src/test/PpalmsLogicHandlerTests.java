package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.LmsTarget;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

class PpalmsLogicHandlerTests {
	
	private PpalmsLogicHandler handler;
	private PpalmsProblem problem;
	
	@BeforeEach
	void beforeEach() {
		handler = new PpalmsLogicHandler();
		problem = createValidPpalmsProblem();
	}

	@Test
	void testValidateCodeInputSuccess() {
		//Tests with Python code extension
		problem.setSourceCode("test.py");
		assertTrue(handler.validateCodeInput(problem));
		assertEquals("test.py", problem.getSourceCode());
		
		//Tests with Java code extension
		problem.setSourceCode("test.java");
		assertTrue(handler.validateCodeInput(problem));
		assertEquals("test.java", problem.getSourceCode());
		
		//Tests with C++ "cpp" code extension
		problem.setSourceCode("test.cpp");
		assertTrue(handler.validateCodeInput(problem));
		assertEquals("test.cpp", problem.getSourceCode());
		
		//Tests with C code extension
		problem.setSourceCode("test.c");
		assertTrue(handler.validateCodeInput(problem));
		assertEquals("test.c", problem.getSourceCode());
		
		//Tests with C++ "cc" code extension
		problem.setSourceCode("test.cc");
		assertTrue(handler.validateCodeInput(problem));
		assertEquals("test.cc", problem.getSourceCode());
	}
	
	@Test
	void testValidateCodeInputNoCode() {
		PpalmsProblem problem = new PpalmsProblem();
		assertFalse(handler.validateCodeInput(problem));
	}
	
	@Test
	void testValidateCodeInputInvalidExtension() {
		problem.setSourceCode("test.txt");
		assertFalse(handler.validateCodeInput(problem));
		
		problem.setSourceCode("test.png");
		assertFalse(handler.validateCodeInput(problem));
		
		problem.setSourceCode("test.mp4");
		assertFalse(handler.validateCodeInput(problem));
		
		problem.setSourceCode("test.ml");
		assertFalse(handler.validateCodeInput(problem));
	}
	
	@Test
	void testValidateTitleInputSuccess() {
		problem.setTitle("Test");
		assertTrue(handler.validateTitleInput(problem));
		
		//No title is given. This will be the text area's
		//input if no title is given.
		problem.setTitle("");
		assertTrue(handler.validateTitleInput(problem));
	}
	
	@Test 
	void testValidateTitleInputFailure() {
		//Title input will fail only under the condition
		//where our program fails to initialize the
		//text field's getText. This means that the
		//problem's title will be null.
		problem.setTitle(null);
		assertFalse(handler.validateTitleInput(problem));
	}
	
	@Test
	void testValidateDescInputSuccess() {
		problem.setDescription("Test");
		assertTrue(handler.validateDescInput(problem));
		
		//No description is given. This will be the text area's
		//input if no title is given.
		problem.setDescription("");
		assertTrue(handler.validateDescInput(problem));
	}
	
	@Test 
	void testValidateDescInputFailure() {
		//Description input will fail only under the condition
		//where our program fails to initialize the
		//text area's getText. This means that the
		//problem's title will be null.
		problem.setDescription(null);
		assertFalse(handler.validateDescInput(problem));
	}
	
	@Test
	void testSetAnnotations() {
		//Useing the test problem, if we annotate
		//the problem, we should expect the source
		//code lines to be in order.
		
		//Each line in the source code will be annotated
		List<Integer> annotations = new ArrayList<Integer>();
			annotations.add(0);
			annotations.add(1);
			annotations.add(2);
		problem.setAnnotations(annotations);
		handler.setAnnotations(problem);
		List<String> correctAnnotationList = new ArrayList<String>();
			correctAnnotationList.add("def main:");
			correctAnnotationList.add("# Comment before print");
			correctAnnotationList.add("print('Hello World')");
		assertEquals(correctAnnotationList.get(0), problem.getSourceCodeLines().get(0));
		assertEquals(correctAnnotationList.get(1), problem.getSourceCodeLines().get(1));
		assertEquals(correctAnnotationList.get(2), problem.getSourceCodeLines().get(2));
		
		//The user can annotate in any order. This means we
		//can add the annotations in the order by which
		//the user selects them. We will expect the same
		//order in the sourceCodeLines object after
		//the logic handler operation is completed.
		annotations = new ArrayList<Integer>();
			annotations.add(1);
			annotations.add(2);
			annotations.add(0);
		problem.setAnnotations(annotations);
		handler.setAnnotations(problem);
			correctAnnotationList = new ArrayList<String>();
			correctAnnotationList.add("def main:");
			correctAnnotationList.add("# Comment before print");
			correctAnnotationList.add("print('Hello World')");
		assertEquals(correctAnnotationList.get(0), problem.getSourceCodeLines().get(0));
		assertEquals(correctAnnotationList.get(1), problem.getSourceCodeLines().get(1));
		assertEquals(correctAnnotationList.get(2), problem.getSourceCodeLines().get(2));
		
		
		//The user does not also have to input all of
		//the source code lines.
		annotations = new ArrayList<Integer>();
			annotations.add(1);
			annotations.add(2);
		problem.setAnnotations(annotations);
		handler.setAnnotations(problem);
			correctAnnotationList = new ArrayList<String>();
			correctAnnotationList.add("def main:");
			correctAnnotationList.add("# Comment before print");
			correctAnnotationList.add("print('Hello World')");
		assertEquals(correctAnnotationList.get(1), problem.getSourceCodeLines().get(0));
		assertEquals(correctAnnotationList.get(2), problem.getSourceCodeLines().get(1));
		
		//Therefore, if we don't include all of the lines,
		//there will not be an index at position 2.
		assertThrows(IndexOutOfBoundsException.class, () -> {
			problem.getSourceCodeLines().get(2);
	    });
	}
	
	@Test
	void testCreatePermutations() {
		//TODO @Jaden Rodriguez
	}
	
	@Test
	void testExportPpalmsProblem() {
		//TODO @Jaden Rodriguez
	}
	
	private PpalmsProblem createValidPpalmsProblem() {
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
		
		return problem;
	}

}