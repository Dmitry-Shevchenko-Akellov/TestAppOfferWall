package com.example.testappofferwall.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Base.SQLiteHandler;
import com.example.testappofferwall.R;
import com.example.testappofferwall.game.GameActivity;
import com.example.testappofferwall.webview.WebActivity;

import java.util.Objects;

public class StartActivity extends BaseActivity implements StartView {

    private StartPresenter startPresenter;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        startPresenter = new StartPresenterImpl();
        startPresenter.attachView(this);

        db = new SQLiteHandler(getContext());

        Integer value = db.getAllowValue().get("allow_value");
        if (value == null) {
            setupToolbar();
        }
        else if (value == 0) {
            Intent goToGame = new Intent(StartActivity.this, GameActivity.class);
            startActivity(goToGame);
            finish();
        }
        else if (value == 1){
            Intent goToWebView = new Intent(StartActivity.this, WebActivity.class);
            startActivity(goToWebView);
            finish();
        }

        startPresenter.sendRequest();
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Error! Try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startNextStep(boolean allowAttribute) {
        if (allowAttribute) {
            Intent goToWebView = new Intent(StartActivity.this, WebActivity.class);
            startActivity(goToWebView);
            finish();
        }
        else {
            Intent goToGame = new Intent(StartActivity.this, GameActivity.class);
            startActivity(goToGame);
            finish();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Start");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startPresenter.detachView();
    }
}
