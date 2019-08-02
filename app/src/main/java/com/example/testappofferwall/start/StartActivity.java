package com.example.testappofferwall.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.R;
import com.example.testappofferwall.game.GameActivity;
import com.example.testappofferwall.webview.WebActivity;

import java.util.Objects;

public class StartActivity extends BaseActivity implements StartView {

    private StartPres startPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        startPresenter = new StartPresImpl();
        startPresenter.attachView(this);

        setupToolbar();
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_send_request:
                Toast.makeText(getContext(), "Send request", Toast.LENGTH_SHORT).show();
                startPresenter.sendRequest();
                return true;
            case R.id.menu_clear_attribute:
                Toast.makeText(getContext(), "Clear attribute", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

}
