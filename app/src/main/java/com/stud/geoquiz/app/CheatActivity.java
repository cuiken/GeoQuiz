package com.stud.geoquiz.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @autor Ken.Cui
 */
public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";
    private static final String KEY_INDEX = "index";

    public static final String EXTRA_ANSWER_IS_TRUE = "com.stud.geoquiz.app.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.stud.geoquiz.app.answer_shown";
    private boolean mAnswerIsTrue;
    private boolean isAnswerShown;

    private TextView mAnswerTextView;
    private TextView mVersionTextView;
    private Button mShowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        if (savedInstanceState != null) {
            isAnswerShown = savedInstanceState.getBoolean(KEY_INDEX, false);
        }
        setAnswerShownResult(isAnswerShown);
        mShowButton = (Button) findViewById(R.id.showAnswerButton);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                isAnswerShown = true;
                setAnswerShownResult(true);
            }
        });

        mVersionTextView = (TextView) findViewById(R.id.versionTextView);
        mVersionTextView.setText("Api level "+Build.VERSION.SDK_INT);

    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_INDEX, isAnswerShown);
    }
}
