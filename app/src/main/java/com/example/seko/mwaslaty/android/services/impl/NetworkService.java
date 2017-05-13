package com.example.seko.mwaslaty.android.services.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.seko.mwaslaty.android.services.INetworkService;

/**
 * Created by Helal on 05/05/2017.
 */
public class NetworkService implements INetworkService {

    @Override
    public boolean isInternetConnectionAvailable(Context context) {
        // TODO add the implementation
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else
            return ni.isConnected();
    }
}
