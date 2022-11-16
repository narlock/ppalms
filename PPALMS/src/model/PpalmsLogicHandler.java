package model;

import java.io.File;
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
		//TODO
		return false;
	}
	
	public boolean validateAnnotations(PpalmsProblem problem) {
		//TODO
		return false;
	}
	
	public List<PpalmsProblem> createPermutations(PpalmsProblem problem) {
		//TODO
		return null;
	}
	
	public boolean exportPpalmsProblem(PpalmsProblem problem) {
		//TODO - will call createPermutations
		return false;
	}
}
