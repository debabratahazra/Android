package sample.code.corejavainterviewquestion.util;

import java.util.HashMap;
import java.util.Locale;

import sample.code.corejavainterviewquestion.R;
import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Utils {

	private static int result;
	private static TextToSpeech textToSpeech;

	public static void customeTitle(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		// Set layout xml file
		activity.setContentView(R.layout.questions_template);

		activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.question_titlebar);
	}

	public static TextToSpeech textToSpeechConversion(
			final ToggleButton toggleButtonOnOff, final Context context,
			final Activity activity) {

		textToSpeech = new TextToSpeech(context,
				new TextToSpeech.OnInitListener() {

					@Override
					public void onInit(int status) {

						if (status == TextToSpeech.SUCCESS) {
							// Set the Language
							result = textToSpeech.setLanguage(Locale.US);
						} else {
							// Language not supported
							Toast.makeText(context,
									"Feature not supported in your device.",
									Toast.LENGTH_SHORT).show();
						}

						textToSpeech
								.setOnUtteranceCompletedListener(new OnUtteranceCompletedListener() {

									@Override
									public void onUtteranceCompleted(
											final String utteranceId) {
										// Text to Speech completed.

										activity.runOnUiThread(new Runnable() {

											@Override
											public void run() {
												toggleButtonOnOff
														.setChecked(false);
												toggleButtonOnOff
														.setBackgroundResource(R.drawable.mute_50);
											}
										});
									}
								});
					}
				});

		return textToSpeech;
	}

	public static OnCheckedChangeListener addTextToSpeechListener(
			final ToggleButton toggleButtonOnOff, final Context context,
			final TextView textviewAnswer, final String defaultTextviewAnswer) {
		OnCheckedChangeListener onCheckListener = new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// Toggle On Off for Text to Speech
				if (result == TextToSpeech.LANG_NOT_SUPPORTED
						|| result == TextToSpeech.LANG_MISSING_DATA) {
					// Not supported.
					Toast.makeText(context,
							"Feature not supported in your device.",
							Toast.LENGTH_SHORT).show();
				} else {
					// Speech the Answer Text with toggle nature
					if (isChecked) {
						// The toggle is enabled
						// Speak
						String text = textviewAnswer.getText().toString();
						if (text.equals(defaultTextviewAnswer)) {
							text = context.getResources().getString(
									R.string.answer_text);
						}
						HashMap<String, String> ttsParams = new HashMap<String, String>();
						ttsParams.put(
								TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
								context.getPackageName());
						textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,
								ttsParams);
					} else {
						// The toggle is disabled
						// Mute
						if (textToSpeech != null) {
							textToSpeech.stop();
						}
					}
				}
			}
		};
		toggleButtonOnOff.setOnCheckedChangeListener(onCheckListener);
		return onCheckListener;
	}
	
	public static int onClickEvent(View v, int index, TextView textviewAnswer, TextView textviewQuestion,
			TextView textviewXX, String[] questions, String[] answers, String defaultText) {
		
		stopTextToSpeech(false);
		switch (v.getId()) {
		case R.id.buttonLeft:
			index--;
			if (index < 0) {
				index++;
				break;
			}
			textviewAnswer.setText(defaultText);
			textviewQuestion.setText(questions[index]);
			textviewXX.setText(String.valueOf(index + 1) + "/");
			break;
		case R.id.buttonRight:
			index++;
			if ((index + 1) > questions.length) {
				index--;
				break;
			}
			textviewAnswer.setText(defaultText);
			textviewQuestion.setText(questions[index]);
			textviewXX.setText(String.valueOf(index + 1) + "/");
			break;
		case R.id.buttonAnswer:
			textviewAnswer.setText(answers[index]);
			break;
		default:
			break;
		}
		return index;
	}
	
	public static void stopTextToSpeech(boolean isShutdown) {
		if (textToSpeech != null) {
			textToSpeech.stop();
			if(isShutdown)
				textToSpeech.shutdown();
		}
	}

}
