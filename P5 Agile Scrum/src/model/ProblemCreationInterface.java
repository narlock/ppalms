package model;

import org.json.simple.JSONObject;

/**
 * ProblemCreationInterface
 * @brief Holds the logic for creating the output
 * of an chosen problem. Concrete implementations
 * will be given by problem type.
 * 
 * @author Anthony Narlock
 *
 */
public abstract class ProblemCreationInterface {
	protected PpalmsProblem problem;
	
	/**
	 * @brief Constructor
	 * @param problem
	 */
	public ProblemCreationInterface(PpalmsProblem problem) {
		this.problem = problem;
	}
	
	/**
	 * @brief Creates the problem JSONObject used in export
	 * @return JSONObject
	 */
	public abstract JSONObject getProblemJson();
}
