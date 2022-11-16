package model;

import java.io.File;
import java.util.List;

public class PpalmsLogicHandler {
	
	public String convertFileToString(File file) {
		//TODO
		return null;
	}
	
	public boolean validateCodeInput(PpalmsProblem problem) {
		if(problem.getSourceCode() != null) {
			System.out.println(problem.getSourceCode());
			return true;
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
