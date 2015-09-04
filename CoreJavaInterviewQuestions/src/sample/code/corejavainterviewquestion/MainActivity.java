package sample.code.corejavainterviewquestion;

import sample.code.corejavainterviewquestion.others.RateUs;
import sample.code.corejavainterviewquestion.questions.BasicQuestions;
import sample.code.corejavainterviewquestion.questions.CollectionQuestions;
import sample.code.corejavainterviewquestion.questions.ConcurrencyQuestions;
import sample.code.corejavainterviewquestion.questions.ExceptionQuestions;
import sample.code.corejavainterviewquestion.questions.MiscellaneousQuestions;
import sample.code.corejavainterviewquestion.questions.StringQuestions;
import sample.code.corejavainterviewquestion.questions.ThreadQuestions;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button buttonBasicQ, buttonStringQ, buttonThreadQ,
			buttonExceptionQ, buttonCollectionQ, buttonConcurrencyQ,
			buttonMiscellaneousQ, buttonRateUs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.activity_main);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.main_titlebar);
		
		initializeButton();
	}

	private void initializeButton() {
		
		buttonBasicQ = (Button) findViewById(R.id.basic_questions);
		buttonBasicQ.setOnClickListener(this);
		
		buttonStringQ = (Button) findViewById(R.id.string_questions);
		buttonStringQ.setOnClickListener(this);
		
		buttonThreadQ = (Button) findViewById(R.id.thread_questions);
		buttonThreadQ.setOnClickListener(this);
		
		buttonExceptionQ = (Button) findViewById(R.id.exception_questions);
		buttonExceptionQ.setOnClickListener(this);
		
		buttonCollectionQ = (Button) findViewById(R.id.collection_questions);
		buttonCollectionQ.setOnClickListener(this);
		
		buttonConcurrencyQ = (Button) findViewById(R.id.concurrency_questions);
		buttonConcurrencyQ.setOnClickListener(this);
		
		buttonMiscellaneousQ = (Button) findViewById(R.id.miscellaneous_questions);
		buttonMiscellaneousQ.setOnClickListener(this);
		
		buttonRateUs = (Button) findViewById(R.id.rateus);
		buttonRateUs.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.basic_questions:
			Intent intent = new Intent(MainActivity.this, BasicQuestions.class);
			startActivity(intent);
			break;
		case R.id.string_questions:
			intent = new Intent(MainActivity.this, StringQuestions.class);
			startActivity(intent);
			break;
		case R.id.thread_questions:
			intent = new Intent(MainActivity.this, ThreadQuestions.class);
			startActivity(intent);
			break;
		case R.id.exception_questions:
			intent = new Intent(MainActivity.this, ExceptionQuestions.class);
			startActivity(intent);
			break;
		case R.id.collection_questions:
			intent = new Intent(MainActivity.this, CollectionQuestions.class);
			startActivity(intent);
			break;
		case R.id.concurrency_questions:
			intent = new Intent(MainActivity.this, ConcurrencyQuestions.class);
			startActivity(intent);
			break;
		case R.id.miscellaneous_questions:
			intent = new Intent(MainActivity.this, MiscellaneousQuestions.class);
			startActivity(intent);
			break;
		case R.id.rateus:
			intent = new Intent(MainActivity.this, RateUs.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
