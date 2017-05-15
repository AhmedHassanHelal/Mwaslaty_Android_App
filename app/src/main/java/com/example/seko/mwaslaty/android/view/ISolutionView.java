package com.example.seko.mwaslaty.android.view;

import android.content.Context;
import android.view.View;

import com.example.seko.mwaslaty.android.model.ErrorCodes;

/**
 * Created by Helal on 13/05/2017.
 */
public interface ISolutionView {
    Context getContext();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void loadSolutions();

    void showLoadingError(ErrorCodes code);

    void showSolutions();

    void showSolutionOnMap(int solutionIndex);

    void onClick(View v);
}
