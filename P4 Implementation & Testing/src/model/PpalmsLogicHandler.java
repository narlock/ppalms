package model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;

import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;
import java.io.StringWriter; 

/**
 * PpalmsLogicHandler
 *  
 * @brief Serves to manipulate the
 * model in the MVC pattern. The logic handler
 * will validate the user input given to the domain
 * and also serve as functions for creating
 * the final exported problem.
 * 
 * In the PPALMS design document, PpalmsLogicHandler
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 * @author Jaden Rodriguez
 *
 */
public class PpalmsLogicHandler {
	
	/**
	 * PermutationMaker
	 * 
	 * @brief A private inner class responsible for generating permutations.
	 * It will generate a limited number of unique orderings of n elements.
	 * 
	 * @author Jaden Rodriguez
	 *
	 */
	private class PermutationMaker {
		private int limit = 30; // specified in docs
		private Integer[] nums;
		private ArrayList<ArrayList<Integer>> permutations;

		/**
		 * @brief Swaps two elements in the nums list
		 * @param i - index of first element to be swapped
		 * @param j - index of second element to be swapped
		 */
		private void swap(int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
		/**
		 * @brief Recursively builds permutations list using backtracking and recursive substructure.
		 * The nums array is considered from the start index to its end with start initially at 0.
		 * Each element is swapped into the starting position, and the following subarray
		 * is permuted recursively as a subproblem.
		 * @param nums - index list to permute in various orders
		 * @param start - index to consider as start of subarray
		 */
		private void permutation(Integer[] nums, int start) {
		      if (start == nums.length) {
		    	  ArrayList<Integer> candidate = new ArrayList<>(Arrays.asList(nums));
		    	  if (!permutations.contains(candidate))
		    		  permutations.add(candidate);
		    	  return;
		      }
		      for (int swapIdx = start; swapIdx < nums.length; swapIdx++) {
		    	if (permutations.size() == limit)
		    		break;
		        swap(start, swapIdx);
		        permutation(nums, start+1);
		        swap(start, swapIdx);
		      }
		      
		}
		/**
		 * @brief Builds a list of unique permutations of n indices (0-indexed)
		 * @param n - number of indices to permute
		 * @return
		 */
		public ArrayList<ArrayList<Integer>> getPermutations(int n){
			nums = new Integer[n];
		    for (int i = 0; i < n; i++) 
		    	nums[i] = i;
		    permutations = new ArrayList<>();
		    permutation(nums, 0);
		    return permutations;
		}
	}
	
	/**
	 * @brief Validates the source code input. The source code must
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
	 * @brief Checks that the title and description objects are defined.
	 * Since these fields are not required by the user, this
	 * validation is sufficient.
	 * 
	 * @param problem
	 * @return true if correct input, false for invalid input.
	 */
	public boolean validateTitleInput(PpalmsProblem problem) {
		String title = problem.getTitle();
		if(title != null)
			return true;
		else return false;
	}
	
	/**
	 * @brief Checks that the title and description objects are defined.
	 * Since these fields are not required by the user, this
	 * validation is sufficient.
	 * 
	 * @param problem
	 * @return true if correct input, false for invalid input.
	 */
	public boolean validateDescInput(PpalmsProblem problem) {
		String title = problem.getDescription();
		if(title != null)
			return true;
		else return false;
	}
	
	/**
	 * @brief Grabs the annotations from the annotations list of integers
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
		problem.setSourceCodeLines(chosenCodeLines);
	}
	
	/**
	 * @brief Creates the permutations of the parson's problem. This is
	 * to different versions of the problem so the problem
	 * can be reused.
	 * 
	 * @param problem
	 * @return A list of permutations of the PpalmsProblem.
	 */
	public List<PpalmsProblem> createPermutations(PpalmsProblem problem) {
		//Generate permutations and return List of problems
		setAnnotations(problem);
		List<PpalmsProblem> permutedProblems = new ArrayList<PpalmsProblem>();
		int n = problem.getAnnotations().size();
		ArrayList<ArrayList<Integer>> permutations = new PermutationMaker().getPermutations(n);
		for (ArrayList<Integer> permutation: permutations) {
			PpalmsProblem reorderedProblem = new PpalmsProblem();
			List<String> oldSourceCodeLines = problem.getSourceCodeLines();
			ArrayList<String> reorderedSourceCodeLines = new ArrayList<>();
			for (Integer position : permutation) {
				reorderedSourceCodeLines.add(oldSourceCodeLines.get(position));
			}
			reorderedProblem.setSourceCodeLines(reorderedSourceCodeLines);
			
			// copy rest of attributes
			reorderedProblem.setSourceCode(problem.getSourceCode());
			reorderedProblem.setProblemType(problem.getProblemType());
			reorderedProblem.setLmsTarget(problem.getLmsTarget());
			reorderedProblem.setTitle(problem.getTitle());
			reorderedProblem.setDescription(problem.getDescription());
			
			permutedProblems.add(reorderedProblem);
		}
		return permutedProblems;
	}
	
	/**
	 * @brief Exports the PpalmsProblem.
	 * 
	 * @param problem
	 * @return true for successful export, false for unsuccessful export.
	 */
	public boolean exportPpalmsProblem(PpalmsProblem problem) {
		// Will call createPermutations
		List<PpalmsProblem> permutedProblems = createPermutations(problem);
		System.out.println(permutedProblems.size());
		ArrayList<List<String>> annotations = new ArrayList<>();
		for (PpalmsProblem permutedProblem: permutedProblems) {
			System.out.println(permutedProblem.getSourceCodeLines());
			annotations.add(permutedProblem.getSourceCodeLines());
		}
    
		// Create the JSON and save file location
		JSONObject obj = new JSONObject();
		obj.put("title", problem.getTitle());
		obj.put("description", problem.getDescription());
		obj.put("lms", problem.getLmsTarget().toString());
		obj.put("type", problem.getProblemType().toString());
		obj.put("correct", problem.getSourceCodeLines());
		obj.put("permutations", annotations);
		
		
		StringWriter out = new StringWriter();
	    try {
			obj.writeJSONString(out);
		}
	    catch (IOException e1) {
			System.out.println("Error occured converting JSON to String.");
			e1.printStackTrace();
	        return false;
		}
	    String jsonText = out.toString();
		
		System.out.println("Exporting PpalmsProblem!");
		
		String filename = "problem.json";
		File file;
		
		// Create file
		try {
		      file = new File(filename);
		      if (!file.createNewFile()) {
		        System.out.println("File already exists.");
		        return false;
		      }
		} 
		catch (IOException e) {
		      System.out.println("An error occurred creating the file.");
		      e.printStackTrace();
		      return false;
		}
		
		// Write JSON
		try {
		      FileWriter writer = new FileWriter(filename);
		      writer.write(jsonText);
		      writer.close();
		    }
		catch (IOException e) {
		      System.out.println("An error occurred writing to the file.");
		      e.printStackTrace();
		      return false;
		}
		
		//If the user saves a file, then return true
		return true;
	}
	
}
