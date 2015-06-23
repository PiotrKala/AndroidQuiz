package agh.edu.pl.quiz.helpers;

import android.util.SparseArray;

public class Question {
	private int number;
	private boolean correct;
	private String content;
	private SparseArray<Answer> answers = new SparseArray<Answer>();
	
	public void setFromStart() {
		correct = false;
		for(int i = 0; i < answers.size(); i++) {
			answers.get(i).setFromStart();
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect() {
		for(int i = 0; i < answers.size(); i++) {
			if(!answers.get(i).isMarkedCorrectly()) {
				this.correct = false;
				return;
			}
		}
		this.correct = true;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SparseArray<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(SparseArray<Answer> answers) {
		this.answers = answers;
	}
	
}
