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
import java.util.Map;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.FillInTheBlankCreation;
import model.LmsTarget;
import model.OrderingCreation;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

/**
 * FillInTheBlankCreationTests
 * 
 * @brief This class holds all of the unit tests
 * for the FillInTheBlankCreation class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Jaden Rodriguez
 *
 */
class FillInTheBlankCreationTests {
	
	private PpalmsProblem problem;
	private List<String> dummyLines;
	private FillInTheBlankCreation fillInTheBlankCreation;
	
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
				"    y = x * 2",
				"             "
				);
		problem = createValidFillInTheBlankProblem();
		fillInTheBlankCreation = new FillInTheBlankCreation(problem);
	}


	


	
	/**
	 * This tests that getProblemJson creates a JSON object with the expected keys and values
	 */
	@Test
	void testGetProblemJson() {
		JSONObject obj = fillInTheBlankCreation.getProblemJson();
		List<JSONObject> questions = (List<JSONObject>) obj.get("questions");
		
		Map<Integer, List<Integer>> blankBounds = problem.getBlankBounds();
		
		List<String> lines = problem.getSourceCodeLines();
		int questionNum = 0;
		for (int i = 0; i < lines.size(); i++) {
			if (!blankBounds.containsKey(i)) {
				continue;
			}
			String line = lines.get(i);
			if (line.trim().isEmpty()) {
				continue;
			}
			
			JSONObject question = questions.get(questionNum++);
			assertTrue(question.containsKey("prompt"));
			assertTrue(question.containsKey("answer"));
			
			String prompt = (String) question.get("prompt");
			String answer = (String) question.get("answer");
			
			List<Integer> bounds = blankBounds.get(i);
			int startInclusive = bounds.get(0);
			int endInclusive = bounds.get(1);
			assertEquals(answer, line.substring(startInclusive, endInclusive + 1));
			
			String blank = prompt.substring(startInclusive, endInclusive + 1);
			for (int j = 0; j < blank.length(); j++) {
				assertEquals(blank.charAt(j), '_');
			}
			
			String left = prompt.substring(0, startInclusive);
			String right = prompt.substring(endInclusive +1);
			
			assertEquals(line, left + answer + right);			
		}
		assertEquals(questions.size(), questionNum);
	}
	
	
	/**
	 * This is a private helper function that creates
	 * a sample FillInTheBlanks PpalmsProblem object that can be used
	 * during the test cases.
	 * 
	 * This helper function promotes code reuse as
	 * the PpalmsLogicHandler requires the use
	 * of modifying model attributes.
	 * 
	 * @return validate PpalmsProblem object
	 */
	private PpalmsProblem createValidFillInTheBlankProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		problem.setSourceCode("test.py");
		problem.setSourceCodeLines(dummyLines);
		problem.setAnnotations(Arrays.asList(31, 13, 45, 51));
		problem.setBlankBounds
			(
				new HashMap<Integer, List<Integer>>() {
					{
				        put(31, Arrays.asList(5, 5));
				        put(13, Arrays.asList(0, 14));
				        put(45, Arrays.asList(7, 10));
				        put(51, Arrays.asList(3, 4));
					}
				}
			);
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.Ordering);
		return problem;
	}

}