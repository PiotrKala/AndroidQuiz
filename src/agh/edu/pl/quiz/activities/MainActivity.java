package agh.edu.pl.quiz.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById(R.id.chooseFileButton)
	Button chooseFileButton;

	@ViewById(R.id.addFile)
	TextView addFileText;

	@AfterViews
	void setConf() {
		chooseFileButton.setBackgroundColor(Color.parseColor("#00ff7f"));
	}

	@Click(R.id.chooseFileButton)
	void chooseFile() {
		Intent intent = new Intent(this, FileExploringActivity_.class);
		startActivity(intent);
	}

}
