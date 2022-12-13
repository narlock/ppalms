package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONObject;

/**
 * MultipleChoiceCreation
 * @brief Concrete implementation of ProblemCreationInterface
 * that serves the purpose of holding the creation logic
 * for a multiple choice problem.
 * 
 * @author Jaden Rodriguez
 *
 */
public class MultipleChoiceCreation extends ProblemCreationInterface{
	private int numOptions = 4; // a, b, c, d
	
	public MultipleChoiceCreation(PpalmsProblem problem) {
		super(problem);
	}

	
	public  JSONObject getProblemJson() {
		
		List<JSONObject> multipleChoiceQuestions = new ArrayList<>();
		System.out.println(problem);
		System.out.println(problem.getSourceCodeLines());
		for (String line : problem.getSourceCodeLines()) {
			String trimmedLine = line.trim();
			if (trimmedLine.isEmpty()) { continue; }
			JSONObject multipleChoiceQuestion = makeMultipleChoiceQuestion(line);
			multipleChoiceQuestions.add(multipleChoiceQuestion);
		}
		JSONObject obj = new JSONObject();
		obj.put("questions", multipleChoiceQuestions);
		return obj;
	}

	private JSONObject makeMultipleChoiceQuestion(String line) {
		
		ArrayList<String> mutations = generateMutations(line, numOptions-1);
		mutations.add(line);
		Collections.shuffle(mutations);
		
		JSONObject options = new JSONObject();
		for (int i = 0; i < numOptions; i++) {
			String choice = new Character((char) (i + 'a')).toString();
			options.put(choice, mutations.get(i));
		}
	
		String answer = new Character((char) (mutations.indexOf(line) + 'a')).toString();
		
		JSONObject multipleChoiceQuestion = new JSONObject();
		multipleChoiceQuestion.put("answer", answer);
		multipleChoiceQuestion.put("options", options);
		
		return multipleChoiceQuestion;
	}
	
	private ArrayList<String> generateMutations(String line, int numMutations){

		String[] tokens = line.split(" ", 0);
		
		HashMap<String, Integer> tokenCount = new HashMap<>();
		for (String token: tokens) {
			tokenCount.put(token, tokenCount.getOrDefault(token, 0) + 1);
		}
		
		addSufficientNoise(tokenCount);
		
		Random rng = new Random();
		HashSet<String> mutations = new HashSet<>();
		while (mutations.size() < numMutations) {
			ArrayList<String> mutationTokens = new ArrayList<>();
			
			HashMap<String, Integer> remaining = new HashMap<>(tokenCount);
			while (remaining.size() > 0) {
				int r = rng.nextInt(remaining.size());
				int i = 0;
				for(String token : new HashSet<>(remaining.keySet())) {
				    if (i++ != r) { continue; }
				    
				    mutationTokens.add(token);
				    remaining.put(token, remaining.get(token) - 1);
				    if (remaining.get(token) == 0) {
				    	remaining.remove(token);
				    }
				}
			}
			mutations.add(String.join(" ", mutationTokens));
		}
		return new ArrayList<>(mutations);
	}
	
	private void addSufficientNoise(HashMap<String, Integer> tokenCount) {
		String noise = "!/#*^";
		for (int i = 0; i < noise.length(); i++) {
			if (tokenCount.size() >= 3 ) { return; } // 3 or more factorial > 4
			String noiseToken = Character.toString(noise.charAt(i));
			if (tokenCount.containsKey(noiseToken)) { continue;}
			tokenCount.put(noiseToken, 1);
		}
	}
	
	
}
