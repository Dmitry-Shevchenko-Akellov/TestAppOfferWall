package com.example.testappofferwall.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Utils.SQLiteHandler;
import com.example.testappofferwall.R;
import com.example.testappofferwall.start.StartActivity;

import java.util.Objects;

public class WebActivity extends BaseActivity implements com.example.testappofferwall.webview.WebView {

    private WebPresenter webPresenter;
    private WebView myWebView;
    private SQLiteHandler db;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        webPresenter = new WebPresenterImpl();
        webPresenter.attachView(this);

        db = new SQLiteHandler(getContext());

        myWebView = findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://html5test.com/");
        myWebView.setWebViewClient(new WebClient());

        setupToolbar();
    }

    private class WebClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_webView);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("WebView");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_web_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                finish();
                System.exit(0);
                return true;
            case R.id.menu_clear_attribute:
                db.deleteAllowValue();
                Intent goToStart = new Intent(WebActivity.this, StartActivity.class);
                goToStart.putExtra("visit", 1);
                startActivity(goToStart);
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webPresenter.detachView();
    }
}
