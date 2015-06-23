package agh.edu.pl.quiz.helpers;


public class Answer {
	private boolean correct;
	private String content;
	private Marked marked = Marked.UNMARKED;
	
	public void setFromStart() {
		marked = Marked.UNMARKED;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Marked getMarked() {
		return marked;
	}

	public void setMarked(Marked marked) {
		this.marked = marked;
	}
	
	public boolean isMarkedCorrectly() {
		if(marked == Marked.CORRECT && isCorrect()) return true;
		if(marked == Marked.UNMARKED && !isCorrect()) return true;
		return false;
	}
	
	public boolean isUnmarked() {
		return marked == Marked.UNMARKED;
	}
	
	public enum Marked {
		CORRECT, INCORRECT, UNMARKED
	}

}
