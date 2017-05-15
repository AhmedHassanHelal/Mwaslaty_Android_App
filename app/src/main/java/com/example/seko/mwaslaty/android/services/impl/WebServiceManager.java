package com.example.seko.mwaslaty.android.services.impl;

import android.util.Log;

import com.example.seko.mwaslaty.android.services.IWebServiceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Helal on 05/05/2017.
 */
public class WebServiceManager implements IWebServiceManager {

    public final static int GET = 1;
    //    public final static int POST = 2;
    private static final String TAG = WebServiceManager.class.getSimpleName();

    static HttpURLConnection conn;
    private String cookies = null;

    public WebServiceManager() {
    }

    @Override
    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    @Override
    public String GET(String url) {
        HttpURLConnection c = null;
        InputStream is = null;
        int status = 200;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            //Headers Params
            c.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            if (cookies != null)
                c.setRequestProperty("Cookie", cookies);
            c.setConnectTimeout(5000);
            c.setReadTimeout(50000);
            c.setRequestMethod("GET");
            c.connect();
            status = c.getResponseCode();
            is = c.getInputStream();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (MalformedURLException ex) {
            Log.d(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }

        return null;
    }
}

