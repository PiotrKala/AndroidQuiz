package agh.edu.pl.quiz.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import agh.edu.pl.quiz.helpers.QuestionsDispatcher;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.R;

@EActivity(R.layout.activity_finish)
public class FinishActivity extends Activity {
	private final String CORRECT = " Correct: ";
	private final String INCORRECT = " Incorrect: ";
	private final String SCORE = " Score: ";

	@ViewById(R.id.correct)
	TextView correct;

	@ViewById(R.id.incorrect)
	TextView incorrect;

	@ViewById(R.id.score)
	TextView score;

	@ViewById(R.id.finishResterButton)
	Button finishRestartButton;

	@ViewById(R.id.finishLoadButton)
	Button finishLoadButton;

	@AfterViews
	void showStatistics() {
		finishLoadButton.setBackgroundColor(Color.parseColor("#00ff7f"));
		finishRestartButton.setBackgroundColor(Color.parseColor("#00ff7f"));
		correct.setText(CORRECT + QuestionsDispatcher.getCorrectAnswers());
		incorrect.setText(INCORRECT + QuestionsDispatcher.getWrongAnswers());
		double percentage = (((double) QuestionsDispatcher.getCorrectAnswers()) / ((double) QuestionsDispatcher
				.getQuestionsNumber())) * 100;
		score.setText(SCORE + percentage + "%");
	}

	@Click(R.id.finishResterButton)
	void restart() {
		QuestionsDispatcher.setFromStart();
		Intent intent = new Intent(this, QuestionActivity_.class);
		startActivity(intent);
	}
	
	@Click(R.id.finishLoadButton)
	void load() {
		QuestionsDispatcher.setFromStart();
		Intent intent = new Intent(this, MainActivity_.class);
		startActivity(intent);
	}
}