package agh.edu.pl.quiz.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.SparseArray;

public class QuestionParser {

	static QuestionParserParameters questionParserParameters = QuestionParserParameters
			.getInstance();

	static BufferedReader reader;
	static int questionNumber;

	public static void parseQuestions(File file) {
		questionNumber = 0;
		try {
			FileInputStream fin = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		String question = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (line != null) {
			while (line != null
					&& line.contains(questionParserParameters
							.getQUESTION_KEY_WORD())) {
				if (line.length() != 0) {
					question += line;
					question += "\n";
				}
				try {
					line = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				while (line != null
						&& !line.contains(questionParserParameters
								.getQUESTION_KEY_WORD())) {
					if (line.length() != 0) {
						question += line;
						question += "\n";
					}
					try {
						line = reader.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				getQuestion(question);
				question = "";
			}
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void getQuestion(String questionConent) {
		int index = 0;
		int answerIndex = 0;

		String[] questionContentArray = questionConent.split("\n");
		SparseArray<Answer> answers = new SparseArray<Answer>();
		Question question = new Question();
		List<String> questionContentList = new ArrayList<String>(
				Arrays.asList(questionContentArray));
		
		
		String content = questionContentList.get(++index);
		question.setContent(content);

		for (int i = ++index; i < questionContentArray.length; i++) {
			Answer answer = new Answer();
			if (questionContentArray[i].contains(questionParserParameters
					.getCORRECT_ANSWER())) {
				answer.setCorrect(true);
				questionContentArray[i] = questionContentArray[i].replace(
						questionParserParameters.getCORRECT_ANSWER(), "");
			}
			answer.setContent(questionContentArray[i]);
			answers.put(answerIndex++, answer);
			question.setAnswers(answers);
		}
		QuestionsDispatcher.questions.put(questionNumber++, question);
	}

}
