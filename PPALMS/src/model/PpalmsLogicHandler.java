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
	
	private ProblemCreationInterface problemCreation;
	
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
	 * @brief Exports the PpalmsProblem.
	 * 
	 * @param problem
	 * @return true for successful export, false for unsuccessful export.
	 */
	public boolean exportPpalmsProblem(PpalmsProblem problem) {
		setAnnotations(problem);
		
		//Logic to instantiate problem creation based on problem type
		if(problem.getProblemType() == ProblemType.Ordering) {
			problemCreation = new OrderingCreation(problem);
		}
		
		// Create the JSON and save file location
		JSONObject obj = new JSONObject();
		obj.put("title", problem.getTitle());
		obj.put("description", problem.getDescription());
		obj.put("lms", problem.getLmsTarget().toString());
		obj.put("type", problem.getProblemType().toString());
		obj.put("problem", problemCreation.getProblemJson());
		
		
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
