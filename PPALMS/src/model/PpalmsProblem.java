package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The PPLAMS problem object.
 * 
 * In the PPALMS design document, PpalmsProblem
 * is one of the classes specified. It's purpose
 * is reflected in that document.
 * 
 * @author Anthony Narlock
 *
 */
public class PpalmsProblem {

	/**
	 * The source code string of the input source code.
	 */
	private String sourceCode;
	
	/**
	 * The lines of the input source code.
	 */
	private List<String> sourceCodeLines;
	
	/**
	 * The type of problem.
	 */
	private ProblemType problemType;
	
	/**
	 * The target LMS for the problem.
	 */
	private LmsTarget lmsTarget;
	
	/**
	 * The annotations for the problem.
	 */
	private List<Integer> annotations;
	
	/**
	 * The title of the problem.
	 */
	private String title;
	
	/**
	 * The description of the problem.
	 */
	private String description;
	
	public String getSourceCode() {
		return sourceCode;
	}
	
	/**
	 * Setter method for sourceCode
	 * 
	 * @param sourceCode
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	/**
	 * Getter method for problemType
	 * 
	 * @return problemType
	 */
	public ProblemType getProblemType() {
		return problemType;
	}
	
	/**
	 * Setter method for setting problemType
	 * 
	 * @param problemType
	 */
	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}
	
	/**
	 * Getter method for lmsTarget
	 * 
	 * @return lmsTarget
	 */
	public LmsTarget getLmsTarget() {
		return lmsTarget;
	}
	
	/**
	 * Setter method for lmsTarget
	 * 
	 * @param lmsTarget
	 */
	public void setLmsTarget(LmsTarget lmsTarget) {
		this.lmsTarget = lmsTarget;
	}
	
	/**
	 * Getter method for Parson's Problem annotations
	 * 
	 * @return annotations
	 */
	public List<Integer> getAnnotations() {
		if(annotations == null) { annotations = new ArrayList<Integer>(); }
		return annotations;
	}
	
	/**
	 * Setter method for Parson's Problem annotations
	 * 
	 * @param annotations
	 */
	public void setAnnotations(List<Integer> annotations) {
		this.annotations = annotations;
	}
	
	/**
	 * Getter method for title 
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter method for Problem title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Getter method for Problem Description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter method for Problem Description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter method for list of lines from uploaded input
	 * sourcecode file.
	 * 
	 * @return sourceCodeLines
	 */
	public List<String> getSourceCodeLines() {
		return sourceCodeLines;
	}
	
	/**
	 * Setter method for storing list of lines
	 * from uploaded input sourcecode file
	 * 
	 * @param sourceCodeLines
	 */
	public void setSourceCodeLines(List<String> sourceCodeLines) {
		this.sourceCodeLines = sourceCodeLines;
	}
	
	/**
	 * Concatenates all relevant properties of the 
	 * PpalmsProblem into string format.
	 * 
	 * @return concatenated String of Parson's Problem details
	 */
	@Override
	public String toString() {
		return "PpalmsProblem [sourceCode Exists=" + sourceCode + ", problemType=" + problemType + ", lmsTarget=" + lmsTarget
				+ ", annotations=" + annotations + ", title=" + title + ", description=" + description + "]";
	}
}