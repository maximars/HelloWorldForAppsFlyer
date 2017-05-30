package com.example.maximars.myfirstapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .build();

        System.setProperty("https.proxyHost", "192.168.1.29");
        System.setProperty("https.proxyPort", "8888");



        AppsFlyerLib.getInstance().setImeiData("860898020931552");
        AppsFlyerLib.getInstance().setCollectAndroidID(false);
        AppsFlyerLib.getInstance().startTracking(this.getApplication(), "7bro7WYn34S5VpakPfDwCm");

        Context applicationContext = getApplicationContext();

        Map<String, Object> purchaseValue = new HashMap<String, Object>();
        purchaseValue.put(AFInAppEventParameterName.REVENUE,200);
        purchaseValue.put(AFInAppEventParameterName.CONTENT_TYPE,"category_a");
        purchaseValue.put(AFInAppEventParameterName.CONTENT_ID,"1234567");
        purchaseValue.put(AFInAppEventParameterName.CURRENCY,"USD");

        AppsFlyerLib.getInstance().trackEvent(applicationContext, AFInAppEventType.PURCHASE, purchaseValue);

        Map<String, Object> eventValue = new HashMap<String, Object>();
        eventValue.put(AFInAppEventParameterName.LEVEL,9);
        eventValue.put(AFInAppEventParameterName.SCORE,100);

        AppsFlyerLib.getInstance().trackEvent(applicationContext, AFInAppEventType.LEVEL_ACHIEVED, eventValue);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently

        // ...
    }
}
