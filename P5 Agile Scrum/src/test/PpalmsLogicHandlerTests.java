package test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.LmsTarget;
import model.MultipleChoiceCreation;
import model.OrderingCreation;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

/**
 * PpalmsLogicHandlerTests
 * 
 * @brief This class holds all of the unit tests
 * for the PpalmsLogicHandler class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Anthony Narlock
 * @author Jaden Rodriguez
 *
 */
class PpalmsLogicHandlerTests {
	
	private PpalmsLogicHandler handler;
	private PpalmsProblem problem;
	private List<String> dummyLines;
	
	/**
	 * Helper function which initializes handler and problem to useful state before each test
	 */
	@BeforeEach
	void beforeEach() {
		dummyLines  = Arrays.asList( // used by createValidPpalmsProblem
				"def main:",
				"    # Comment before print",
				"    print('Hello World')",
				"    x = 0",
				"    x += 1",
				"    x *= 4",
				"    x -= 1",
				"    x *= 5",
				"    print(x)",
				"    x += 1",
				"    y = 1",
				"    y = x * 12",
				"    y -= x - 13",
				"    y -= x + 14",
				"    y -= x // 15",
				"    y -= x / 16",
				"    y += x * 17",
				"    y += x * 18",
				"    y = x / 19",
				"    y = x * 21",
				"    y = x - 22",
				"    y = x * 23",
				"    y = x * 24",
				"    y = x * 11",
				"    y = x - 26",
				"    y = x * 27",
				"    y = x * 28",
				"    y = x * 29",
				"    y = x * 30",
				"    y = x * 12",
				"    y = x * 13",
				"    y = x * 14",
				"    y = x * 15",
				"    y = x * 61",
				"    y = x * 71",
				"    y = x * 81",
				"    y = x * 91",
				"    y = x * 111",
				"    y = x * 121",
				"    y = x * 131",
				"    y = x * 141",
				"    y = x * 151",
				"    y = x * 161",
				"    y = x * 171",
				"    y = x * 181",
				"    y = x * 191",
				"    y = x * 101",
				"    y = x * 31",
				"    y = x * 1",
				"    y = x * 2"
				);
		handler = new PpalmsLogicHandler();
		problem = createValidOrderingProblem();
	}

	/**
	 * testValidateCodeInputSuccess
	 * 
	 * @brief Ensure validity of a user’s source code file. 
	 * This test executes the validateCodeInput method 
	 * in the PpalmsLogicHandler.
	 * 
	 * @author Anthony Narlock
	 */
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
	
	/**
	 * testValidateCodeInputNoCode
	 * 
	 * @brief Ensure validity of a user’s source code file under the condition 
	 * of failure to select a source code file. This can happen if the 
	 * user selects cancel on the file chooser dialog. This test executes 
	 * the validateCodeInput method in the PpalmsLogicHandler.
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testValidateCodeInputNoCode() {
		PpalmsProblem problem = new PpalmsProblem();
		assertFalse(handler.validateCodeInput(problem));
	}
	
	/**
	 * Ensure validity of a user’s source code file under the condition 
	 * of failure to select a source code file. This can happen if the 
	 * user selects a file that contains an unsupported file extension. 
	 * This test executes the validateCodeInput method in the PpalmsLogicHandler.
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testValidateCodeInputInvalidExtension() {
		//Test with .txt extension
		problem.setSourceCode("test.txt");
		assertFalse(handler.validateCodeInput(problem));
		
		//Test with .png extension
		problem.setSourceCode("test.png");
		assertFalse(handler.validateCodeInput(problem));
		
		//Test with .mp4 extension
		problem.setSourceCode("test.mp4");
		assertFalse(handler.validateCodeInput(problem));
		
		//Test with .ml extension
		problem.setSourceCode("test.ml");
		assertFalse(handler.validateCodeInput(problem));
	}
	
	/**
	 * Ensure that the PPALMS application properly validates title input 
	 * in regards to the requirements specification. That is, the user can 
	 * choose to leave the field blank and not indicate a title since it is optional.
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testValidateTitleInputSuccess() {
		problem.setTitle("Test");
		assertTrue(handler.validateTitleInput(problem));
		
		//No title is given. This will be the text area's
		//input if no title is given.
		problem.setTitle("");
		assertTrue(handler.validateTitleInput(problem));
	}
  
	/**
	 * Ensure that the PPALMS application will return failure under an unexpected 
	 * condition that does not initialize the title input text field in the 
	 * ProblemInputStrategy.
	 * 
	 * @author Anthony Narlock
	 */
	@Test 
	void testValidateTitleInputFailure() {
		//Title input will fail only under the condition
		//where our program fails to initialize the
		//text field's getText. This means that the
		//problem's title will be null.
		problem.setTitle(null);
		assertFalse(handler.validateTitleInput(problem));
	}
	
	/**
	 * Ensure that the PPALMS application properly validates description input in
	 * regards to the requirements specification. That is, the user can choose to 
	 * leave the field blank and not indicate a description since it is optional.
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testValidateDescInputSuccess() {
		problem.setDescription("Test");
		assertTrue(handler.validateDescInput(problem));
		
		//No description is given. This will be the text area's
		//input if no title is given.
		problem.setDescription("");
		assertTrue(handler.validateDescInput(problem));
	}
	
	/**
	 * Ensure that the PPALMS application will return failure under an unexpected 
	 * condition that does not initialize the description input text field in the
	 * ProblemInputStrategy.
	 * 
	 * @author Anthony Narlock
	 */
	@Test 
	void testValidateDescInputFailure() {
		//Description input will fail only under the condition
		//where our program fails to initialize the
		//text area's getText. This means that the
		//problem's title will be null.
		problem.setDescription(null);
		assertFalse(handler.validateDescInput(problem));
	}
  
	/**
	 * Given an input selection of annotations for an ordering problem. 
	 * The annotations will come in the form of user-selected lines. 
	 * Upon selecting each line, the PPALMS application will validate 
	 * the selected annotations by sorting them. This allows the user 
	 * to select lines of code in their problem out of order and still 
	 * receive their expected result.
	 * 
	 * @author Anthony Narlock
	 */
	@Test
	void testSetAnnotations() {
		//Using the test problem, if we annotate
		//the problem, we should expect the source
		//code lines to be in order.
		
		//Each line in the source code will be annotated
		List<Integer> annotations = new ArrayList<Integer>();
			annotations.add(0);
			annotations.add(1);
			annotations.add(2);
		problem.setAnnotations(annotations);
		handler.setAnnotations(problem);
		assertEquals(annotations.size(), problem.getSourceCodeLines().size());
		assertEquals(dummyLines.get(0), problem.getSourceCodeLines().get(0));
		assertEquals(dummyLines.get(1), problem.getSourceCodeLines().get(1));
		assertEquals(dummyLines.get(2), problem.getSourceCodeLines().get(2));
		
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
		assertEquals(dummyLines.get(0), problem.getSourceCodeLines().get(0));
		assertEquals(dummyLines.get(1), problem.getSourceCodeLines().get(1));
		assertEquals(dummyLines.get(2), problem.getSourceCodeLines().get(2));
		
		
		//The user does not also have to input all of
		//the source code lines.
		annotations = new ArrayList<Integer>();
			annotations.add(1);
			annotations.add(2);
		problem.setAnnotations(annotations);
		handler.setAnnotations(problem);
		assertEquals(dummyLines.get(1), problem.getSourceCodeLines().get(0));
		assertEquals(dummyLines.get(2), problem.getSourceCodeLines().get(1));
		
		//Therefore, if we don't include all of the lines,
		//there will not be an index at position 2.
		assertThrows(IndexOutOfBoundsException.class, () -> {
			problem.getSourceCodeLines().get(2);
	    });
	}
	
	
	/**
	 * This tests that exportPpalmsProblem creates an output file and that the JSON parses to the original ordering problem
	 * and its permutations (does not check that annotations/source lines are within constraints
	 * because this method should only called if this is so; these constraints are tested
	 * in another component)
	 */
	@Test
	void testExportOrderingPpalmsProblem() {
		// make sure problem file is not present
		try {
            Files.deleteIfExists(
                Paths.get("problem.json"));
        }
        catch (Exception e) {
            System.out.println(
                "No such file/directory exists");
        }
		assertTrue(handler.exportPpalmsProblem(problem));
		
		
		JSONObject parsed, original;
		try {
			parsed = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		
		
		assertEquals(parsed.get("title"), problem.getTitle());
		assertEquals(parsed.get("description"), problem.getDescription());
		assertEquals(parsed.get("lms"), problem.getLmsTarget().toString());
		assertEquals(parsed.get("type"), problem.getProblemType().toString());
		OrderingCreation problemCreation = new OrderingCreation(problem);
		
		assertEquals(parsed.get("problem"), problemCreation.getProblemJson());

		// verify that JSON output is parsed correctly in the case that
		// tile/description not specified
		// Also try different LMS target for coverage
		problem = createValidOrderingProblem();
		try {
            Files.deleteIfExists(
                Paths.get("problem.json"));
        }
        catch (Exception e) {
            System.out.println(
                "No such file/directory exists");
        }
		problem.setLmsTarget(LmsTarget.D2L);
		problem.setTitle("");
		problem.setDescription("");
		assertTrue(handler.exportPpalmsProblem(problem));
		
		try {
			parsed = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		
		assertEquals(parsed.get("title"), problem.getTitle());
		assertEquals(parsed.get("description"), problem.getDescription());
		assertEquals(parsed.get("lms"), problem.getLmsTarget().toString());
		assertEquals(parsed.get("type"), problem.getProblemType().toString());
		problemCreation = new OrderingCreation(problem);
		assertEquals(parsed.get("problem"), problemCreation.getProblemJson());

	}
	
	/**
	 * This tests that PPALMS problem export if the destination file already exists
	 */
	@Test
	void testExportPpalmsProblemFail() {
		// make sure problem file is present
		try {
		      File file = new File("problem.json");
		      file.createNewFile();
		} 
		catch (IOException e) {
		      System.out.println("An error occurred creating the file.");
		      e.printStackTrace();
		}
		assertFalse(handler.exportPpalmsProblem(problem));
	}
	
	/**
	 * This tests that exportPpalmsProblem creates an output file and that the JSON parses to the original MultipleChoice problem
	 * structure
	 */
	@Test
	void testExportMultipleChoiceOrderingPpalmsProblem() {
		// make sure problem file is not present
		try {
            Files.deleteIfExists(
                Paths.get("problem.json"));
        }
        catch (Exception e) {
            System.out.println(
                "No such file/directory exists");
        }
		problem = createValidMultipleChoiceProblem();
		System.out.println(problem);
		System.out.println("hi again");
		assertTrue(handler.exportPpalmsProblem(problem));
		
		
		JSONObject parsed;
		try {
			parsed = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		
		
		assertEquals(parsed.get("title"), problem.getTitle());
		assertEquals(parsed.get("description"), problem.getDescription());
		assertEquals(parsed.get("lms"), problem.getLmsTarget().toString());
		assertEquals(parsed.get("type"), problem.getProblemType().toString());
		MultipleChoiceCreation mcCreation = new MultipleChoiceCreation(problem);
		JSONObject problemJSON = (JSONObject) parsed.get("problem");
		for (Object key : mcCreation.getProblemJson().keySet()) {
			assertTrue(problemJSON.containsKey(key));
		}

		// verify that JSON output is parsed correctly in the case that
		// tile/description not specified
		// Also try different LMS target for coverage
		problem = createValidMultipleChoiceProblem();
		try {
            Files.deleteIfExists(
                Paths.get("problem.json"));
        }
        catch (Exception e) {
            System.out.println(
                "No such file/directory exists");
        }
		problem.setLmsTarget(LmsTarget.D2L);
		problem.setTitle("");
		problem.setDescription("");
		assertTrue(handler.exportPpalmsProblem(problem));
		
		try {
			parsed = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		
		assertEquals(parsed.get("title"), problem.getTitle());
		assertEquals(parsed.get("description"), problem.getDescription());
		assertEquals(parsed.get("lms"), problem.getLmsTarget().toString());
		assertEquals(parsed.get("type"), problem.getProblemType().toString());
		mcCreation = new MultipleChoiceCreation(problem);
		problemJSON = (JSONObject) parsed.get("problem");
		for (Object key : mcCreation.getProblemJson().keySet()) {
			assertTrue(problemJSON.containsKey(key));
		}
		
	}

	/**
	 * This is a private helper function that creates
	 * a sample Ordering PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidOrderingProblem() {
		PpalmsProblem orderingProblem = new PpalmsProblem();
		orderingProblem.setTitle("Test Title");
		orderingProblem.setDescription("Test Description");
		orderingProblem.setSourceCode("test.py");
		orderingProblem.setSourceCodeLines(dummyLines);
		orderingProblem.setAnnotations(Arrays.asList(31, 13, 45));
		orderingProblem.setLmsTarget(LmsTarget.Canvas);
		orderingProblem.setProblemType(ProblemType.Ordering);
		return orderingProblem;
	}
	
	/**
	 * This is a private helper function that creates
	 * a sample MultipleChoice PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidMultipleChoiceProblem() {
		PpalmsProblem mcProblem = new PpalmsProblem();
		mcProblem.setTitle("Test Title");
		mcProblem.setDescription("Test Description");
		mcProblem.setSourceCode("test.py");
		mcProblem.setSourceCodeLines(dummyLines);
		mcProblem.setAnnotations(Arrays.asList(31, 13, 45));
		mcProblem.setLmsTarget(LmsTarget.Canvas);
		mcProblem.setProblemType(ProblemType.MultipleChoice);
		System.out.println(mcProblem);
		System.out.println("hi");
		return mcProblem;
	}

}
