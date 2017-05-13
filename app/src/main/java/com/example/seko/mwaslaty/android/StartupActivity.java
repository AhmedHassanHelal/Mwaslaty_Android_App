package com.example.seko.mwaslaty.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.seko.mwaslaty.android.presenter.StartupPresenter;
import com.example.seko.mwaslaty.android.view.IStartupView;

/**
 * Created by Helal
 */

public class StartupActivity extends Activity implements OnClickListener, IStartupView {

    private Button startButton;
    private StartupPresenter startupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        init();
    }

    private void init() {
        startupPresenter = new StartupPresenter(this);
        startButton = (Button) findViewById(R.id.btn_start);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startupPresenter.buttonClicked(v.getId());
    }

    @Override
    public void navigateToMainActivity() {
        Intent toMainActivityIntent = new Intent(StartupActivity.this,
                MainActivity.class);
        startActivity(toMainActivityIntent);
    }
}
