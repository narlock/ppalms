package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The PPLAMS problem object.
 * 
 * @author narlock
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
	
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public ProblemType getProblemType() {
		return problemType;
	}
	
	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}
	
	public LmsTarget getLmsTarget() {
		return lmsTarget;
	}
	
	public void setLmsTarget(LmsTarget lmsTarget) {
		this.lmsTarget = lmsTarget;
	}
	
	public List<Integer> getAnnotations() {
		if(annotations == null) { annotations = new ArrayList<Integer>(); }
		return annotations;
	}
	
	public void setAnnotations(List<Integer> annotations) {
		this.annotations = annotations;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getSourceCodeLines() {
		return sourceCodeLines;
	}
	
	public void setSourceCodeLines(List<String> sourceCodeLines) {
		this.sourceCodeLines = sourceCodeLines;
	}
	
	@Override
	public String toString() {
		return "PpalmsProblem [sourceCode Exists=" + sourceCode + ", problemType=" + problemType + ", lmsTarget=" + lmsTarget
				+ ", annotations=" + annotations + ", title=" + title + ", description=" + description + "]";
	}
}