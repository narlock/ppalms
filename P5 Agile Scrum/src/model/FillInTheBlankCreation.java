package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import view.AnnotationInterface;

/**
 * OrderingCreation
 * @brief Concrete implementation of ProblemCreationInterface
 * that serves the purpose of holding the creation logic
 * for an ordering problem.
 * 
 * @author Jaden Rodriguez
 *
 */
public class FillInTheBlankCreation extends ProblemCreationInterface {
	
	/**
	 * @brief See {@link ProblemCreationInterface} for definition.
	 */
	public FillInTheBlankCreation(PpalmsProblem problem) {
		super(problem);
	}
	
	/**
	 * @brief See {@link ProblemCreationInterface} for definition.
	 */
	@Override
	public JSONObject getProblemJson() {
		List<JSONObject> fillInTheBlankQuestions = new ArrayList<>();
		Map<Integer, List<Integer>> blankBounds = problem.getBlankBounds();
		List<String> lines = problem.getSourceCodeLines();
		for (int i = 0; i < lines.size(); i++) {
			
			if (!blankBounds.containsKey(i)) { continue;}
			String line = lines.get(i);
			if (line.trim().isEmpty()) { continue; }
			
			List<Integer> bounds = blankBounds.get(i);
			int startInclusive = bounds.get(0);
			int endInclusive = bounds.get(1);
			
			JSONObject fillInTheBlankQuestion = makefillInTheBlankQuestion(line, startInclusive, endInclusive);
			fillInTheBlankQuestions.add(fillInTheBlankQuestion);
		}
		JSONObject obj = new JSONObject();
		obj.put("questions", fillInTheBlankQuestions);
		return obj;
	}
	
	/**
	 * Creates the blank in a multiple choice question
	 * @param line
	 * @param startInclusive
	 * @param endInclusive
	 * @return JSONobject of the line problem with blank
	 */
	private JSONObject makefillInTheBlankQuestion(String line, int startInclusive, int endInclusive) {
		String left = line.substring(0, startInclusive);
		String slice = line.substring(startInclusive, endInclusive + 1);
		String right = line.substring(endInclusive + 1);
		String blank = "";
		for(int i=0; i < slice.length();i++) {
			blank += "_";
		}
		JSONObject obj = new JSONObject();
		obj.put("prompt", left + blank + right);
		obj.put("answer", slice);
		return obj;
	}
	
	
}
