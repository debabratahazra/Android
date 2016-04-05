package sample.code.corejavainterviewquestion.questions;

import sample.code.corejavainterviewquestion.R;
import sample.code.corejavainterviewquestion.util.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MiscellaneousQuestions extends Activity implements OnClickListener {

	private static final String ANSWER = "answer";
	private static final String INDEX = "index";
	private String[] miscQuestions = null;
	private String[] miscAnswers = null;
	private int index;

	private TextView textviewQuestion, textviewAnswer, textviewXX, textviewYY, textviewSubject;
	private Button buttonLeft, buttonShowAnswer, buttonRight;
	private ToggleButton toggleButtonOnOff;

	private String defaultTextviewAnswer = null;
	private TextToSpeech textToSpeech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Utils.getInstance().customeTitle(this);
		
		// initialize the TextView
		textviewQuestion = (TextView) findViewById(R.id.textViewQuestion);
		textviewAnswer = (TextView) findViewById(R.id.textViewAnswer);
		textviewXX = (TextView) findViewById(R.id.textViewXX);
		textviewYY = (TextView) findViewById(R.id.textViewYY);

		// Question template title
		textviewSubject = (TextView) findViewById(R.id.textViewSubject);
				
		// Initialize the Button and set the onClickListener for Button
		buttonLeft = (Button) findViewById(R.id.buttonLeft);
		buttonLeft.setOnClickListener(this);
		buttonRight = (Button) findViewById(R.id.buttonRight);
		buttonRight.setOnClickListener(this);
		buttonShowAnswer = (Button) findViewById(R.id.buttonAnswer);
		buttonShowAnswer.setOnClickListener(this);
		
		// Initial Toggle Button
		toggleButtonOnOff = (ToggleButton) findViewById(R.id.voice_on_off);

		// Import and initialize the string-array elements from values folder
		miscQuestions = getResources().getStringArray(
				R.array.miscellaneous_questions);
		miscAnswers = getResources().getStringArray(
				R.array.miscellaneous_answers);

		// Set the first question index
		index = 0;

		// Get the default answer in TextView.
		defaultTextviewAnswer = getResources().getString(R.string.answer);

		// Set the first question in page.
		textviewQuestion.setText(miscQuestions[index]);
		textviewAnswer.setText(defaultTextviewAnswer);
		textviewXX.setText(String.valueOf(index + 1) + "/");
		textviewYY.setText(String.valueOf(miscQuestions.length));
		
		textviewSubject.setText("Java - Miscellaneous");

		// Text to Speech conversion
		textToSpeech = Utils.getInstance().textToSpeechConversion(toggleButtonOnOff,
				getApplicationContext(), this);
		Utils.getInstance().addTextToSpeechListener(toggleButtonOnOff,
				getApplicationContext(), textviewAnswer, defaultTextviewAnswer);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Save the state of current Question and Answer
		outState.putString(ANSWER, textviewAnswer.getText().toString());
		outState.putInt(INDEX, index);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		index = savedInstanceState.getInt(INDEX);
		textviewAnswer.setText(savedInstanceState.getString(ANSWER));

		textviewQuestion.setText(miscQuestions[index]);
		textviewXX.setText(String.valueOf(index + 1) + "/");
	}

	@Override
	public void onClick(View v) {
		index = Utils.getInstance().onClickEvent(v, index, textviewAnswer, textviewQuestion,
				textviewXX, miscQuestions, miscAnswers, defaultTextviewAnswer, textToSpeech);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Utils.getInstance().stopTextToSpeech(true, textToSpeech);
	}
}
