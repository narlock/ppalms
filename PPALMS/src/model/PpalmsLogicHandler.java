package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PpalmsLogicHandler {
	
	public String convertFileToString(File file) {
		//TODO
		return null;
	}
	
	public boolean validateCodeInput(PpalmsProblem problem) {
		if(problem.getSourceCode() == null) {
			return false;
		}
		System.out.println("[PpalmsLogicHandler/validateCodeInput] source = " + problem.getSourceCode());
		
		String[] validExtensions = {"java", "py", "cpp", "c"};
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
	
	public boolean validateTitleDescInput(PpalmsProblem problem) {
		String title = problem.getTitle();
		if(title != null)
			return true;
		else return false;
	}
	
	public boolean validateAnnotations(PpalmsProblem problem) {
		String description = problem.getDescription();
		if(description != null)
			return true;
		else return false;
	}
	
	public List<PpalmsProblem> createPermutations(PpalmsProblem problem) {
		//TODO Generate permutations and return List of problems
		List<PpalmsProblem> permutedProblems = new ArrayList<PpalmsProblem>();
		System.out.println("Creating Permutations for " + problem.toString());
		return permutedProblems;
	}
	
	public boolean exportPpalmsProblem(PpalmsProblem problem) {
		//TODO - will call createPermutations
		List<PpalmsProblem> permutedProblems = createPermutations(problem);
		//TODO - Create the JSON and save file location
		//If the user saves a file, then return true
		//Else, return false.
		System.out.println("Exporting PpalmsProblem!");
		return true;
	}
}
