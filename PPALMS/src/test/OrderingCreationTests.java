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
import model.OrderingCreation;
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

/**
 * PpalmsLogicHandlerTests
 * 
 * @brief This class holds all of the unit tests
 * for the OrderingCreation class.
 * 
 * These unit tests are specified in the
 * PPALMS Testing Document and implemented
 * utilizing the JUnit testing framework
 * in this class.
 * 
 * @author Anthony Narlock
 *
 */
class OrderingCreationTests {
	
	private PpalmsProblem problem;
	private List<String> dummyLines;
	private OrderingCreation orderingCreation;
	
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
		problem = createValidPpalmsProblem();
		orderingCreation = new OrderingCreation(problem);
	}


	


	
	/**
	 * This tests that getProblemJson creates a JSON object with the expected keys and values
	 */
	@Test
	void testGetProblemJson() {
		JSONObject obj = orderingCreation.getProblemJson();
		List<String> original = (List<String>) obj.get("correct");
		assertEquals(original,problem.getSourceCodeLines());
		List<List<String>> permutations = (List<List<String>>) obj.get("permutations");
		for (List<String> permutation : permutations) {
			System.out.println(original);
			System.out.println(permutation);
			System.out.println('\n');
			assertTrue(isPermutation(original, permutation));
		}
		assertTrue(allUniquePermutations(permutations));
	}
	

	/**
	 * This tests that  createPermutations generates a list of the correct specifications,
	 * including size limit, and element content and uniqueness 
	 * (checks that the list is actually a list of permutations).
	 * It checks it over the range of allowed input sizes for more coverage.
	 */
	@Test
	void testCreatePermutations() {
		PpalmsLogicHandler handler = new PpalmsLogicHandler();
		int limit = 30;
		int lineLimit = 50;
		int maxLength = 1; // 0! = 1
		for (int numLines = 1; numLines <= lineLimit; numLines++) {
			// update length using factorial (Dynamic programming)
			maxLength *= numLines; // n! = n * (n-1)!
			if (maxLength > limit) {
				maxLength = limit;
			}
			problem = createValidPpalmsProblem();
			ArrayList<Integer> annotations = new ArrayList<>();
			for (int i = 0; i < numLines; i++) {
				annotations.add(i);
			}
			problem.setAnnotations(annotations);
			handler.setAnnotations(problem);
			
			orderingCreation = new OrderingCreation(problem);
			List<PpalmsProblem> permutedProblems = orderingCreation.createPermutations();
			// check the  number of permutations is within bounds
			int length = permutedProblems.size();
			assertTrue(0 < length);
			assertTrue(length <= maxLength);
			// check each element is a permutation of the original problem
			List<String> original = problem.getSourceCodeLines();
			for (PpalmsProblem permutedProblem : permutedProblems) {
				List<String> candidate = permutedProblem.getSourceCodeLines();
				assertTrue(isPermutation(original, candidate));
			}
			// check that no duplicate permutations were made
			assertTrue(allUniquePermutations(permutedProblems));
		}
	}
	
	
	/**
	 * This method helps ensure that a list of permutations was generated
	 * by confirming that each element is unique
	 * @param <T> - generic type of list
	 * @param l - list of permutations
	 * @return
	 */
	private <T> boolean allUniquePermutations(List<T> l) {
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(i).equals(l.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * This helper method tells whether a candidate String list
	 * is a permutation of a reference original String list.
	 * @param reference - original String list to be compared to
	 * @param candidate - candidate permutation of original String list
	 * @return true if is a permutation, false if not
	 */
	private boolean isPermutation(List<String> reference, List<String> candidate) {
		HashMap<String, Integer> referenceCount = new HashMap<>();
		HashMap<String, Integer> candidateCount = new HashMap<>();
		for (String line: reference)
			referenceCount.put(line, referenceCount.getOrDefault(line, 0) + 1);			
		for (String line: candidate)
			candidateCount.put(line, candidateCount.getOrDefault(line, 0) + 1);		
		return candidateCount.equals(referenceCount);
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
	private PpalmsProblem createValidPpalmsProblem() {
		PpalmsProblem problem = new PpalmsProblem();
		problem.setTitle("Test Title");
		problem.setDescription("Test Description");
		problem.setSourceCode("test.py");
		problem.setSourceCodeLines(dummyLines);
		problem.setAnnotations(Arrays.asList(31, 13, 45));
		problem.setLmsTarget(LmsTarget.Canvas);
		problem.setProblemType(ProblemType.Ordering);
		return problem;
	}

}
