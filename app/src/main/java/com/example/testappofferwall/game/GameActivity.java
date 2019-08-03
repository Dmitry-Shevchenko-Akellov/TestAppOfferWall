package com.example.testappofferwall.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.testappofferwall.Base.BaseActivity;
import com.example.testappofferwall.Utils.Bank;
import com.example.testappofferwall.Utils.SQLiteHandler;
import com.example.testappofferwall.R;
import com.example.testappofferwall.Utils.SlotEnd;
import com.example.testappofferwall.Utils.SlotScrolling;
import com.example.testappofferwall.Utils.valueSlot;
import com.example.testappofferwall.start.StartActivity;

import java.util.Objects;
import java.util.Random;

public class GameActivity extends BaseActivity implements GameView, View.OnClickListener {

    private GamePresenter gamePresenter;
    private SQLiteHandler db;
    Button spinBtn;
    TextView myMoney;
    SlotScrolling slotOne, slotTwo, slotThree;

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

        slotOne.setSlotEnd((SlotEnd) GameActivity.this);
        slotTwo.setSlotEnd((SlotEnd) GameActivity.this);
        slotThree.setSlotEnd((SlotEnd) GameActivity.this);

        spinBtn.setOnClickListener(this);

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
    protected void onDestroy() {
        super.onDestroy();
        gamePresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
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
                else {
                    Toast.makeText(getContext(), "Insufficient Funds", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void slotEnd(int result, int i) {
        if (count_item < 2) {
            count_item++;
        }
        else {
            spinBtn.setEnabled(true);

            count_item = 0;

            if (slotOne.getValue() == valueSlot.BELL && slotTwo.getValue() == valueSlot.BELL &&
                    slotThree.getValue() == valueSlot.BELL) {
                
                Bank.money_bank += 100;
                myMoney.setText(String.valueOf(Bank.money_bank));
            }
            else if {

            }
        }
    }
    
}
