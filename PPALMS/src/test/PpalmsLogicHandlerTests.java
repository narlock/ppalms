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
import model.PpalmsLogicHandler;
import model.PpalmsProblem;
import model.ProblemType;

/**
 * Test suite which checks that the logic handler meets design specifications
 * and accounts for errors and situations which lie outside the design specification domain.
 * @author jadenrodriguez
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
		problem = createValidPpalmsProblem();
	}
	/**
	 * Test that handler accepts file extensions specified in design document
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
	 * Test that handler rejects uninitialized code input
	 */
	@Test
	void testValidateCodeInputNoCode() {
		PpalmsProblem problem = new PpalmsProblem();
		assertFalse(handler.validateCodeInput(problem));
	}
	/**
	 * Test that PPALMS rejects extensions which are not listed in the design document
	 */
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
	/**
	 * Test that Title validation succeeds according to specification
	 * (Succeed as long as initialized because it is optional).
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
	 * Test that Description validation fails when uninitialized
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
	 * Test that Description validation succeeds according to specification
	 * (Succeed as long as initialized because it is optional).
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
	 * Test that Description validation fails when uninitialized
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
	 * Tests that setAnnotations correctly processes user annotations
	 * via sorting and line filtering
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
		System.out.println(annotations);
		System.out.println(problem.toString());
		System.out.println(problem.getSourceCodeLines());
		handler.setAnnotations(problem);
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
	 * This tests that  createPermutations generates a list of the correct specifications,
	 * including size limit, and element content and uniqueness 
	 * (checks that the list is actually a list of permutations).
	 * It checks it over the range of allowed input sizes for more coverage.
	 */
	@Test
	void testCreatePermutations() {
		//TODO @Jaden Rodriguez
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
			List<PpalmsProblem> permutedProblems = handler.createPermutations(problem);
			// check the  number of permutations is within bounds
			int length = permutedProblems.size();
			assertTrue(0 < length && length <= maxLength);
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
	 * This tests that exportPpalmsProblem creates an output file and that the JSON parses to the original problem
	 * and its permutations (does not check that annotations/source lines are within constraints
	 * because this method should only called if this is so; these constraints are tested
	 * in another component)
	 */
	@Test
	void testExportPpalmsProblem() {
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
		
		JSONObject obj;
		try {
			obj = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		assertEquals(obj.get("title"),problem.getTitle());
		assertEquals(obj.get("description"),problem.getDescription());
		assertEquals(obj.get("lms"),problem.getLmsTarget().toString());
		assertEquals(obj.get("type"), problem.getProblemType().toString());
		List<String> original = (List<String>) obj.get("correct");
		System.out.println(original);
		assertEquals(original,problem.getSourceCodeLines());
		List<List<String>> permutations = (List<List<String>>) obj.get("permutations");
		for (List<String> permutation : permutations) {
			assertTrue(isPermutation(original, permutation));
		}
		// check that no duplicate permutations were made
		assertTrue(allUniquePermutations(permutations));
	}
	
	/**
	 * This tests that exportPpalmsProblem creates an output file and that the JSON parses to the original problem
	 * and its permutations, including the empty title and description. Also changes LMS target for coverage.
	 */
	@Test
	void testExportPpalmsProblemNoTitleDescription() {
		// make sure problem file is not present
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
		
		JSONObject obj;
		try {
			obj = (JSONObject) new JSONParser().parse(new FileReader("problem.json"));
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false); // fail test if JSON can't be parsed
			return;
		} 
		assertEquals(obj.get("title"),problem.getTitle());
		assertEquals(obj.get("description"),problem.getDescription());
		assertEquals(obj.get("lms"),problem.getLmsTarget().toString());
		assertEquals(obj.get("type"), problem.getProblemType().toString());
		List<String> original = (List<String>) obj.get("correct");
		assertEquals(original,problem.getSourceCodeLines());
		List<List<String>> permutations = (List<List<String>>) obj.get("permutations");
		for (List<String> permutation : permutations) {
			assertTrue(isPermutation(original, permutation));
		}
		// check that no duplicate permutations were made
		assertTrue(allUniquePermutations(permutations));
	}
	
	/**
	 * This tests that PPALMS problem exportation fails if the destination file already exists
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
	 * @return
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
	 * This method creates a PPALMS problem to run unit tests on.
	 * It has been given values which purposefully push the limitations specified in the design document.
	 * @return
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
