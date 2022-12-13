package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

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
	
	public FillInTheBlankCreation(PpalmsProblem problem) {
		super(problem);
	}
	
	
	@Override
	public JSONObject getProblemJson() {
		List<JSONObject> fillInTheBlankQuestions = new ArrayList<>();
		Map<Integer, List<Integer>> blankBounds = problem.getBlankBounds();
		List<String> lines = problem.getSourceCodeLines();
		for (int i = 0; i < lines.size(); i++) {
			
			if (!blankBounds.containsKey(i)) { continue;}
			String line = lines.get(i);
			line = line.trim();
			if (line.isEmpty()) { continue; }
			
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
	
	private JSONObject makefillInTheBlankQuestion(String line, int startInclusive, int endInclusive) {
		String left = line.substring(0, startInclusive);
		String slice = line.substring(startInclusive, endInclusive + 1);
		String right = line.substring(endInclusive + 1);
		String blank = "";
		for(int i=0; i < slice.length();i++) {
			blank += "-";
		}
		JSONObject obj = new JSONObject();
		obj.put("answer", left + blank + right);
		obj.put("answer", slice);
		return obj;
	}
	
	
}
