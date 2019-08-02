package com.example.testappofferwall.game;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Base.SQLiteHandler;
import com.example.testappofferwall.R;

import java.util.Objects;

public class GameActivity extends BaseActivity implements GameView {

    private GamePresenter gamePresenter;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        gamePresenter = new GamePresenterImpl();
        gamePresenter.attachView(this);

        db = new SQLiteHandler(getContext());

        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_game);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Game");
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
                Toast.makeText(getContext(), "Clear attribute", Toast.LENGTH_SHORT).show();
                db.deleteAllowValue();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gamePresenter.detachView();
    }

    @Override
    public Context getContext() {
        return null;
    }
}
