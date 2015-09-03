package sample.code.corejavainterviewquestion.questions;

import sample.code.corejavainterviewquestion.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MiscellaneousQuestions extends Activity implements
		OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		// Set layout xml file
		setContentView(R.layout.questions_template);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.question_titlebar);
	}

	@Override
	public void onClick(View v) {

	}

}
