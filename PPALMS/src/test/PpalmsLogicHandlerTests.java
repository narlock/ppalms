package test;

import static org.junit.Assert.assertFalse;
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
		
		//Tests with Java code extension
		problem.setSourceCode("test.java");
		assertTrue(handler.validateCodeInput(problem));
		
		//Tests with C++ "cpp" code extension
		problem.setSourceCode("test.cpp");
		assertTrue(handler.validateCodeInput(problem));
		
		//Tests with C code extension
		problem.setSourceCode("test.c");
		assertTrue(handler.validateCodeInput(problem));
		
		//Tests with C++ "cc" code extension
		problem.setSourceCode("test.cc");
		assertTrue(handler.validateCodeInput(problem));
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
	
	private PpalmsProblem createValidPpalmsProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		
		problem.setSourceCode("test.py");
		List<String> sourceCodeLines = new ArrayList<String>();
			sourceCodeLines.add("def main:");
			sourceCodeLines.add("print('Hello World')");
		problem.setSourceCodeLines(sourceCodeLines);
		
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.Ordering);
		
		return problem;
	}

}
