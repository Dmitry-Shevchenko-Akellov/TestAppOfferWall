package com.example.testappofferwall.game;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Utils.Bank;
import com.example.testappofferwall.Utils.SQLiteHandler;
import com.example.testappofferwall.R;
import com.example.testappofferwall.Utils.SlotScrolling;
import com.example.testappofferwall.start.StartActivity;

import java.util.Objects;
import java.util.Random;

public class GameActivity extends BaseActivity implements GameView, View.OnClickListener {

    private GamePresenter gamePresenter;
    private SQLiteHandler db;
    Button spinBtn;
    TextView myMoney;
    SlotScrolling slotOne, slotTwo, slotThree;
    int last_money = 0;

    int count_item = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        gamePresenter = new GamePresenterImpl();
        gamePresenter.attachView(this);

        db = new SQLiteHandler(getContext());

        spinBtn = findViewById(R.id.spinButton);
        slotOne = findViewById(R.id.item_1);
        slotTwo = findViewById(R.id.item_2);
        slotThree = findViewById(R.id.item_3);
        myMoney = findViewById(R.id.my_money);

        slotOne.setSlotEndInt(GameActivity.this);
        slotTwo.setSlotEndInt(GameActivity.this);
        slotThree.setSlotEndInt(GameActivity.this);

        spinBtn.setOnClickListener(this);

        if (last_money == 0 ) {
            myMoney.setText(String.valueOf(Bank.money_bank));
        }
        else {
            myMoney.setText(String.valueOf(last_money));
        }

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
                return true;
            case R.id.menu_clear_attribute:
                db.deleteAllowValue();
                Intent goToStart = new Intent(GameActivity.this, StartActivity.class);
                goToStart.putExtra("visit", 1);
                startActivity(goToStart);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spinButton:
                if (Bank.money_bank >= 100) {
                    spinBtn.setEnabled(false);

                    slotOne.randomValue(new Random().nextInt(9), new Random().nextInt((18-1) + 1) + 5);
                    slotTwo.randomValue(new Random().nextInt(9), new Random().nextInt((18-1) + 1) + 5);
                    slotThree.randomValue(new Random().nextInt(9), new Random().nextInt((18-1) + 1) + 5);

                    Bank.money_bank -= 100;
                    myMoney.setText(String.valueOf(Bank.money_bank));
                }
                break;
        }
    }

    @Override
    public void spinEnd(int result, int i) {
        if (count_item < 2) {
            count_item++;
        }
        else {
            spinBtn.setEnabled(true);
            count_item = 0;

            gamePresenter.startSpin(slotOne.getValue(), slotTwo.getValue(), slotThree.getValue());
        }
    }

    @Override
    public void lose() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage("You lose! Try again?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Bank.money_bank = 1000;
                        finish();
                        startActivity(getIntent());
                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goToStart = new Intent(GameActivity.this, StartActivity.class);
                goToStart.putExtra("visit", 1);
                Bank.money_bank = 1000;
                db.deleteAllowValue();
                startActivity(goToStart);
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void setBank(int money_won) {
        myMoney.setText(String.valueOf(Bank.money_bank));
        if (money_won == 0) {
            Toast toast = Toast.makeText(GameActivity.this, "You did not win.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(GameActivity.this, "You got " + money_won + " money!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gamePresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
