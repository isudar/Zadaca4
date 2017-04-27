package com.example.sudo.lv4;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Sudo on 27.4.2017..
 */

public class Vijest extends Activity{
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vijest);

        webView = (WebView) findViewById(R.id.wvVijest);

        Bundle bundle = getIntent().getExtras();
        webView.loadUrl(bundle.getString("url"));
        finish();
    }
}
