package model;

import java.util.ArrayList;
import java.util.List;

/**
 * PpalmsProblem
 * 
 * @brief The PPLAMS problem object.
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
	 * @brief The source code string of the input source code.
	 */
	private String sourceCode;
	
	/**
	 * @brief The lines of the input source code.
	 */
	private List<String> sourceCodeLines;
	
	/**
	 * @brief The type of problem.
	 */
	private ProblemType problemType;
	
	/**
	 * @brief The target LMS for the problem.
	 */
	private LmsTarget lmsTarget;
	
	/**
	 * @brief The annotations for the problem.
	 */
	private List<Integer> annotations;
	
	/**
	 * @brief The title of the problem.
	 */
	private String title;
	
	/**
	 * @brief The description of the problem.
	 */
	private String description;
	
	/**
	 * @brief The number of students for the problem
	 */
	private int numberOfStudents;
	
	
	public String getSourceCode() {
		return sourceCode;
	}
	
	/**
	 * @brief Setter method for sourceCode
	 * 
	 * @param sourceCode
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	/**
	 * @brief Getter method for problemType
	 * 
	 * @return problemType
	 */
	public ProblemType getProblemType() {
		return problemType;
	}
	
	/**
	 * @brief Setter method for setting problemType
	 * 
	 * @param problemType
	 */
	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}
	
	/**
	 * @brief Getter method for lmsTarget
	 * 
	 * @return lmsTarget
	 */
	public LmsTarget getLmsTarget() {
		return lmsTarget;
	}
	
	/**
	 * @brief Setter method for lmsTarget
	 * 
	 * @param lmsTarget
	 */
	public void setLmsTarget(LmsTarget lmsTarget) {
		this.lmsTarget = lmsTarget;
	}
	
	/**
	 * @brief Getter method for Parson's Problem annotations
	 * 
	 * @return annotations
	 */
	public List<Integer> getAnnotations() {
		if(annotations == null) { 
			annotations = new ArrayList<Integer>(); 
		}
		return annotations;
	}
	
	/**
	 * @brief Setter method for Parson's Problem annotations
	 * 
	 * @param annotations
	 */
	public void setAnnotations(List<Integer> annotations) {
		this.annotations = annotations;
	}
	
	/**
	 * @brief Getter method for title 
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @brief Setter method for Problem title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @brief Getter method for Problem Description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @brief Setter method for Problem Description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @brief Getter method for list of lines from uploaded input
	 * sourcecode file.
	 * 
	 * @return sourceCodeLines
	 */
	public List<String> getSourceCodeLines() {
		return sourceCodeLines;
	}
	
	/**
	 * @brief Setter method for storing list of lines
	 * from uploaded input sourcecode file
	 * 
	 * @param sourceCodeLines
	 */
	public void setSourceCodeLines(List<String> sourceCodeLines) {
		this.sourceCodeLines = sourceCodeLines;
	}
	
	/**
	 * @brief Getter method for the number of students attribute
	 * 
	 * @return numberOfStudents
	 */
	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	/**
	 * @brief Setter method for the number of students
	 * 
	 * @param numberOfStudents
	 */
	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	/**
	 * @brief Concatenates all relevant properties of the 
	 * PpalmsProblem into string format.
	 * 
	 * @return concatenated String of Parson's Problem details
	 */
	@Override
	public String toString() {
		return "PpalmsProblem [sourceCode Exists=" + sourceCode + ", problemType=" + problemType + ", lmsTarget=" + lmsTarget
				+ ", annotations=" + annotations + ", title=" + title + ", description=" + description + ", numberOfStudents="
				+ numberOfStudents + "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this) {
            return true;
        }
        if (!(other instanceof PpalmsProblem)) {
            return false;
        }
        PpalmsProblem otherProblem = (PpalmsProblem) other;
	    return sourceCodeLines == otherProblem.getSourceCodeLines();
	}

	/**
	 * @brief Copies all attributes to a new object, to be used for creating permutations
	 * 
	 * @return new Parson's problem with copied attributes
	 */
	public PpalmsProblem copy() {
		PpalmsProblem copiedProblem = new PpalmsProblem();
		copiedProblem.setSourceCode(sourceCode);
		copiedProblem.setSourceCodeLines(new ArrayList<>(sourceCodeLines));
		copiedProblem.setProblemType(problemType);
		copiedProblem.setLmsTarget(lmsTarget);
		copiedProblem.setAnnotations(new ArrayList<>(annotations));
		copiedProblem.setTitle(title);
		copiedProblem.setDescription(description);
		copiedProblem.setNumberOfStudents(numberOfStudents);
		return copiedProblem;
	}
	
}
