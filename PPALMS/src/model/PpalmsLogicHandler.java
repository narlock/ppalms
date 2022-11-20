package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PpalmsLogicHandler serves to manipulate the
 * model in the MVC pattern. The logic handler
 * will validate the user input given to the domain
 * and also serve as functions for creating
 * the final exported problem.
 * 
 * @author narlock
 *
 */
public class PpalmsLogicHandler {
	
	/**
	 * Validates the source code input. The source code must
	 * be a supported programming language file. An input is
	 * valid if the user inputs source code file that contains
	 * the proper file extension of a supported programming
	 * language.
	 * 
	 * @param problem
	 * @return true if correct input, false for invalid input.
	 */
	public boolean validateCodeInput(PpalmsProblem problem) {
		if(problem.getSourceCode() == null) {
			return false;
		}
		String[] validExtensions = {"java", "py", "cpp", "c", "cc"};
		String extension = "";
		
		int i = problem.getSourceCode().lastIndexOf('.');
		if (i > 0) { extension = problem.getSourceCode().substring(i+1); }
		System.out.println("[PpalmsLogicHandler/validateCodeInput] extension = " + extension);
		
		for(String ext : validExtensions) {
			if(extension.equals(ext)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks that the title and description objects are defined.
	 * Since these fields are not required by the user, this
	 * validation is sufficient.
	 * 
	 * @param problem
	 * @return true if correct input, false for invalid input.
	 */
	public boolean validateTitleDescInput(PpalmsProblem problem) {
		String title = problem.getTitle();
		if(title != null)
			return true;
		else return false;
	}
	
	/**
	 * Grabs the annotations from the annotations list of integers
	 * will modify the sourceCode list of strings to only include
	 * the integers that are in the list of annotations.
	 * These will be the selected lines of code the user wishes
	 * to keep in their parson's problem.
	 *
	 * @param problem
	 */
	public void setAnnotations(PpalmsProblem problem) {
		Collections.sort(problem.getAnnotations());
		List<String> chosenCodeLines = new ArrayList<String>();
		for(int index : problem.getAnnotations()) {
			chosenCodeLines.add(problem.getSourceCodeLines().get(index));
		}
		for(String line : chosenCodeLines) {
			System.out.println(line);
		}
		problem.setSourceCodeLines(chosenCodeLines);
	}
	
	/**
	 * Creates the permutations of the parson's problem. This is
	 * to different versions of the problem so the problem
	 * can be reused.
	 * 
	 * @param problem
	 * @return A list of permutations of the PpalmsProblem.
	 */
	public List<PpalmsProblem> createPermutations(PpalmsProblem problem) {
		//TODO Generate permutations and return List of problems
		List<PpalmsProblem> permutedProblems = new ArrayList<PpalmsProblem>();
		System.out.println("Creating Permutations for " + problem.toString());
		return permutedProblems;
	}
	
	/**
	 * Exports the PpalmsProblem.
	 * 
	 * @param problem
	 * @return true for successful export, false for unsuccessful export.
	 */
	public boolean exportPpalmsProblem(PpalmsProblem problem) {
		setAnnotations(problem);
		//TODO - will call createPermutations
		List<PpalmsProblem> permutedProblems = createPermutations(problem);
		//TODO - Create the JSON and save file location
		//If the user saves a file, then return true
		//Else, return false.
		System.out.println("Exporting PpalmsProblem!");
		return true;
	}
}
