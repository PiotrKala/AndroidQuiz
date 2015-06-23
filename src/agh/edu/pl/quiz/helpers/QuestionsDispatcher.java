package agh.edu.pl.quiz.helpers;

import android.util.SparseArray;

public class QuestionsDispatcher {
	public static SparseArray<Question> questions = new SparseArray<Question>();
	private static int questionIndex = 0;
	private static int correctAnswers = 0;
	private static int incorrectAnswers = 0;

	public static boolean questionsFinished() {
		return questionIndex > getQuestionsNumber();
	}

	public static void setDispatcher() {
		questions.clear();
		setFromStart();
	}
	
	public static void setFromStart() {
		questionIndex = 0;
		incorrectAnswers = 0;
		correctAnswers = 0;
		for(int i = 0; i < questions.size(); i++) {
			questions.get(i).setFromStart();
		}
	}

	public static Question getNextQuestion() {
		return questions.get(questionIndex++);
	}

	public static Question getQuestion(int index) {
		return questions.get(index);
	}

	public static int getQuestionIndex() {
		return questionIndex;
	}

	public static int getQuestionsNumber() {
		return questions.size();
	}

	public static int getCorrectAnswers() {
		return correctAnswers;
	}

	public static int getWrongAnswers() {
		return incorrectAnswers;
	}

	public static void addCorrectlyAnswered() {
		++correctAnswers;
	}

	public static void addInorrectlyAnswered() {
		++incorrectAnswers;
	}

}
