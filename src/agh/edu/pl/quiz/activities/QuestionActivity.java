package agh.edu.pl.quiz.activities;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import agh.edu.pl.quiz.helpers.Answer;
import agh.edu.pl.quiz.helpers.Answer.Marked;
import agh.edu.pl.quiz.helpers.Question;
import agh.edu.pl.quiz.helpers.QuestionsDispatcher;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quiz.R;

@EActivity(R.layout.activity_question)
public class QuestionActivity extends ListActivity {
	private final String START = "Start The Quiz !";
	private final String NEXT_QUESTION = "Next Question";
	private final String QUESTION_NUMBER = "Question Number: ";
	private final String QUESTIONS_NUMBER = " Number of questions : ";
	private final String FINISH = " Finish Quiz";
	private final String CHECK_ANSWERS = " Check Answers";
	private final String NO_QUESTIONS = " No questions loaded, try another file.";

	private final int SELECTED_COLOR = Color.parseColor("#b6fcd5");
	private final int UNSELECTED_COLOR = Color.WHITE;
	private final int CHECK_ANSWERS_COLOR = Color.parseColor("#7fffd4");
	private final int NEXT_QUESTION_COLOR = Color.parseColor("#b6fcd5");
	private final int ANSWER_MARKED_CORRECT = Color.parseColor("#00ff7f");
	private final int ANSWER_MARKED_INCORRECT = Color.parseColor("#ff4040");
	private final int QUESTION_COLOR = Color.parseColor("#0a6dbb");
	private final String CORRECT = " Correct: ";
	private final String INCORRECT = " Incorrect: ";
	private final String SCORE = " Score: ";

	private List<Integer> selected;
	private Question question;

	private void getQuestion() {
		selected = new ArrayList<Integer>();
		question = QuestionsDispatcher.getNextQuestion();
		if (QuestionsDispatcher.questionsFinished()) {
			Intent intent = new Intent(this, FinishActivity_.class);
			startActivity(intent);
		} else {
			List<String> answersContent = new ArrayList<String>();
			String number = Integer.toString(QuestionsDispatcher
					.getQuestionIndex());
			questionsNumber.setText("");
			questionNumber.setText(QUESTION_NUMBER + " " + number + " / "
					+ QuestionsDispatcher.getQuestionsNumber());
			if (question.getContent().length() >= 300) {
				questionContent.setTextSize(18);
			} else {
				questionContent.setTextSize(21);
			}
			questionContent.setText(question.getContent());
			questionContent.setTextColor(QUESTION_COLOR);
			nextQuestionButton.setText(CHECK_ANSWERS);
			nextQuestionButton.setBackgroundColor(CHECK_ANSWERS_COLOR);

			for (int i = 0; i < question.getAnswers().size(); i++) {
				answersContent.add(question.getAnswers().get(i).getContent());
			}
			ArrayAdapter<String> answersList = new ArrayAdapter<String>(this,
					R.layout.answer, answersContent);
			setListAdapter(answersList);
		}
	}

	private void checkAnswers() {
		for (int index = 0; index < question.getAnswers().size(); index++) {
			Answer answer = question.getAnswers().get(index);
			if (!answer.isUnmarked()) {
				if (answer.isMarkedCorrectly())
					this.getListView().getChildAt(index)
							.setBackgroundColor(ANSWER_MARKED_CORRECT);
				else
					this.getListView().getChildAt(index)
							.setBackgroundColor(ANSWER_MARKED_INCORRECT);
			} else if (answer.isCorrect()) {
				this.getListView().getChildAt(index)
						.setBackgroundColor(ANSWER_MARKED_CORRECT);
			}
		}
		question.setCorrect();
		if (question.isCorrect()) {
			QuestionsDispatcher.addCorrectlyAnswered();
		} else {
			QuestionsDispatcher.addInorrectlyAnswered();
		}
		showStatistics();
		nextQuestionButton.setBackgroundColor(NEXT_QUESTION_COLOR);
		nextQuestionButton.setText(NEXT_QUESTION);
		if (QuestionsDispatcher.getQuestionIndex() == QuestionsDispatcher
				.getQuestionsNumber()) {
			nextQuestionButton.setText(FINISH);
		}
	}

	private void prepareMenu() {
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.quiz_actionbar, null);

		ImageButton loadButton = (ImageButton) mCustomView
				.findViewById(R.id.loadButton);
		loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(),
						MainActivity_.class);
				startActivity(intent);
			}
		});

		ImageButton restertButton = (ImageButton) mCustomView
				.findViewById(R.id.restartButton);
		restertButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				QuestionsDispatcher.setFromStart();
				Intent intent = new Intent(getApplicationContext(),
						QuestionActivity_.class);
				startActivity(intent);
			}
		});

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
	}

	private void showStatistics() {
		correct.setText(CORRECT + QuestionsDispatcher.getCorrectAnswers());
		incorrect.setText(INCORRECT + QuestionsDispatcher.getWrongAnswers());
		double percentage = (((double) QuestionsDispatcher.getCorrectAnswers()) / ((double) QuestionsDispatcher
				.getQuestionsNumber())) * 100;
		score.setText(SCORE + percentage + "%");
	}

	@ViewById(R.id.questionNumber)
	TextView questionNumber;

	@ViewById(R.id.questionContent)
	TextView questionContent;

	@ViewById(R.id.nextQuestion)
	Button nextQuestionButton;

	@ViewById(R.id.questionsNumber)
	TextView questionsNumber;

	@ViewById(R.id.questionCorrect)
	TextView correct;

	@ViewById(R.id.questionIncorrect)
	TextView incorrect;

	@ViewById(R.id.questionScore)
	TextView score;

	@AfterViews
	void prepareData() {
		if (QuestionsDispatcher.getQuestionsNumber() == 0) {
			questionContent.setText(NO_QUESTIONS);
		} else {
			showStatistics();
			nextQuestionButton.setText(START);
			nextQuestionButton.setBackgroundColor(NEXT_QUESTION_COLOR);
			questionsNumber.setText(QUESTIONS_NUMBER
					+ QuestionsDispatcher.getQuestionsNumber());

		}
		prepareMenu();
	}

	@Click(R.id.nextQuestion)
	void getNextQuestion() {
		if (nextQuestionButton.getText() == CHECK_ANSWERS) {
			checkAnswers();
		} else {
			getQuestion();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@ItemClick
	void listItemClicked(int position) {
		if (!selected.contains(position)) {
			if (this.getListView().getChildAt(position) != null) {
				selected.add(position);
				this.getListView().getChildAt(position)
						.setBackgroundColor(SELECTED_COLOR);
				question.getAnswers().get(position).setMarked(Marked.CORRECT);
			}

		} else {
			if (this.getListView().getChildAt(position) != null) {
				selected.remove(Integer.valueOf(position));
				this.getListView().getChildAt(position)
						.setBackgroundColor(UNSELECTED_COLOR);
				question.getAnswers().get(position).setMarked(Marked.UNMARKED);
			}
		}

	}

}
