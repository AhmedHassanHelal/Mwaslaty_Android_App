package com.example.seko.mwaslaty.android.services;

/**
 * Created by Helal on 05/05/2017.
 */
public interface IWebServiceManager {
    public void setCookies(String cookies);

    String GET(String url);
}
