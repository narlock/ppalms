package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * OrderingCreation
 * @brief Concrete implementation of ProblemCreationInterface
 * that serves the purpose of holding the creation logic
 * for an ordering problem.
 * 
 * @author Anthony Narlock
 *
 */
public class OrderingCreation extends ProblemCreationInterface {
	
	/**
	 * @brief See {@link ProblemCreationInterface} for definition.
	 */
	public OrderingCreation(PpalmsProblem problem) {
		super(problem);
	}
	
	/**
	 * @brief See {@link ProblemCreationInterface} for definition.
	 */
	@Override
	public JSONObject getProblemJson() {
		// Will call createPermutations
		List<PpalmsProblem> permutedProblems = createPermutations();
		ArrayList<List<String>> annotations = new ArrayList<>();
		for (PpalmsProblem permutedProblem: permutedProblems) {
			annotations.add(permutedProblem.getSourceCodeLines());
		}
		
		JSONObject obj = new JSONObject();
		obj.put("correct", problem.getSourceCodeLines());
		obj.put("permutations", annotations);
		return obj;
	}
	
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
	 * @brief Creates the permutations of the parson's problem. This is
	 * to different versions of the problem so the problem
	 * can be reused.
	 * 
	 * @param problem
	 * @return A list of permutations of the PpalmsProblem.
	 */
	public List<PpalmsProblem> createPermutations() {
		//Generate permutations and return List of problems
		List<PpalmsProblem> permutedProblems = new ArrayList<PpalmsProblem>();
		int n = problem.getSourceCodeLines().size();
		ArrayList<ArrayList<Integer>> permutations = new PermutationMaker().getPermutations(n);
		for (ArrayList<Integer> permutation: permutations) {
			PpalmsProblem reorderedProblem = problem.copy();
			List<String> oldSourceCodeLines = problem.getSourceCodeLines();
			ArrayList<String> reorderedSourceCodeLines = new ArrayList<>();
			for (Integer position : permutation) {
				reorderedSourceCodeLines.add(oldSourceCodeLines.get(position));
			}
			reorderedProblem.setSourceCodeLines(reorderedSourceCodeLines);
			permutedProblems.add(reorderedProblem);
		}
		return permutedProblems;
	}

}
