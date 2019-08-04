package com.example.testappofferwall.start;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Utils.SQLiteHandler;
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

        Integer visit = getIntent().getIntExtra("visit", 0);

        startPresenter = new StartPresenterImpl();
        startPresenter.attachView(this);

        db = new SQLiteHandler(getContext());

        boolean isConn = isNetworkConnected();
        if (isConn) {
            startQuest(visit);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Check internet connection.");
            builder.setCancelable(false);
            builder.setNeutralButton("Refresh", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            });
            builder.show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private void startQuest(Integer visit) {
        Integer valueAllow = db.getAllowValue().get("allow_value");
        if (valueAllow == null) {
            if (visit == 0) {
                setupToolbar();
                startPresenter.sendRequest();
            }
            else if (visit == 1) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setMessage("Play again?");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        setupToolbar();
                                        startPresenter.sendRequest();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
        else if (valueAllow == 0) {
            Intent goToGame = new Intent(StartActivity.this, GameActivity.class);
            startActivity(goToGame);
            finish();
        }
        else if (valueAllow == 1) {
            Intent goToWebView = new Intent(StartActivity.this, WebActivity.class);
            startActivity(goToWebView);
            finish();
        }
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
