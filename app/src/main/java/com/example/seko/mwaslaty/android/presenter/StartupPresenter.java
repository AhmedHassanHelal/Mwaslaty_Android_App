package com.example.seko.mwaslaty.android.presenter;

import com.example.seko.mwaslaty.android.R;
import com.example.seko.mwaslaty.android.view.IStartupView;

/**
 * Created by Helal on 24/04/2017.
 */
public class StartupPresenter {
    IStartupView startupView;

    public StartupPresenter(IStartupView startupView) {
        this.startupView = startupView;
    }

    public void buttonClicked(int btn) {
        switch (btn) {
            case R.id.btn_start:
                startupView.navigateToMainActivity();
                break;
            default:
                break;
        }
    }


}
