package model;

import java.util.List;

public class PpalmsProblem {

	private String sourceCode;
	private ProblemType problemType;
	private LmsTarget lmsTarget;
	private List<String> annotations;
	private String title;
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
	
	public List<String> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<String> annotations) {
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
	
	@Override
	public String toString() {
		return "PpalmsProblem [sourceCode=" + sourceCode + ", problemType=" + problemType + ", lmsTarget=" + lmsTarget
				+ ", annotations=" + annotations + ", title=" + title + ", description=" + description + "]";
	}
}
