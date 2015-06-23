package agh.edu.pl.quiz.helpers;

public class QuestionParserParameters {

	private final String QUESTION_KEY_WORD = "Pytanie";
	private final String CORRECT_ANSWER = ">>>";
	private final String WRONG_FORMAT = " Wrong format of a question ";

	private static QuestionParserParameters questionParserParameters = new QuestionParserParameters();

	public static QuestionParserParameters getInstance() {
		return questionParserParameters;
	}

	public String getQUESTION_KEY_WORD() {
		return QUESTION_KEY_WORD;
	}

	public String getCORRECT_ANSWER() {
		return CORRECT_ANSWER;
	}

	public String getWRONG_FORMAT() {
		return WRONG_FORMAT;
	}
}
