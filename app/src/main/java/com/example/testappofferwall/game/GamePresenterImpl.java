package com.example.testappofferwall.game;

import com.example.testappofferwall.Utils.Bank;
import com.example.testappofferwall.Utils.valueSlot;

public class GamePresenterImpl implements GamePresenter {

    private GameView gameView;

    @Override
    public void attachView(GameView view) {
        gameView = view;
    }

    @Override
    public void detachView() {
        gameView = null;
    }

    @Override
    public void startSpin(int one, int two, int three) {
        int money_won = 0;
        if (one == valueSlot.BELL && two == valueSlot.BELL &&
                three == valueSlot.BELL) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if((one == valueSlot.BELL && two == valueSlot.BELL
                && three != valueSlot.BELL) || (two == valueSlot.BELL
                && three == valueSlot.BELL && one != valueSlot.BELL)) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if(one == valueSlot.BELL && three == valueSlot.BELL
                && two != valueSlot.BELL) {
            Bank.money_bank += 25;
            money_won = 25;
        }
        else if (one == valueSlot.CHERRY && two == valueSlot.CHERRY &&
                three == valueSlot.CHERRY) {
            Bank.money_bank += 250;
            money_won = 250;
        }
        else if((one == valueSlot.CHERRY && two == valueSlot.CHERRY
                && three != valueSlot.CHERRY) || (two == valueSlot.CHERRY
                && three == valueSlot.CHERRY && one != valueSlot.CHERRY)) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if(one == valueSlot.CHERRY && three == valueSlot.CHERRY
                && two != valueSlot.CHERRY) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if (one == valueSlot.CLOVER && two == valueSlot.CLOVER &&
                three == valueSlot.CLOVER) {
            Bank.money_bank += 500;
            money_won = 500;
        }
        else if((one == valueSlot.CLOVER && two == valueSlot.CLOVER
                && three != valueSlot.CLOVER) || (two == valueSlot.CLOVER
                && three == valueSlot.CLOVER && one != valueSlot.CLOVER)) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if(one == valueSlot.CLOVER && three == valueSlot.CLOVER
                && two != valueSlot.CLOVER) {
            Bank.money_bank += 25;
            money_won = 25;
        }
        else if (one == valueSlot.CROWN && two == valueSlot.CROWN &&
                three == valueSlot.CROWN) {
            Bank.money_bank += 1000;
            money_won = 1000;
        }
        else if((one == valueSlot.CROWN && two == valueSlot.CROWN
                && three != valueSlot.CROWN) || (two == valueSlot.CROWN
                && three == valueSlot.CROWN && one != valueSlot.CROWN)) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if(one == valueSlot.CROWN && three == valueSlot.CROWN
                && two != valueSlot.CROWN) {
            Bank.money_bank += 25;
            money_won = 25;
        }
        else if (one == valueSlot.GRAPE && two == valueSlot.GRAPE &&
                three == valueSlot.GRAPE) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if((one == valueSlot.GRAPE && two == valueSlot.GRAPE
                && three != valueSlot.GRAPE) || (two == valueSlot.GRAPE
                && three == valueSlot.GRAPE && one != valueSlot.GRAPE)) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if(one == valueSlot.GRAPE && three == valueSlot.GRAPE
                && two != valueSlot.GRAPE) {
            Bank.money_bank += 25;
            money_won = 25;
        }
        else if (one == valueSlot.HORSESHOE && two == valueSlot.HORSESHOE &&
                three == valueSlot.HORSESHOE) {
            Bank.money_bank += 250;
            money_won = 250;
        }
        else if((one == valueSlot.HORSESHOE && two == valueSlot.HORSESHOE
                && three != valueSlot.HORSESHOE) || (two == valueSlot.HORSESHOE
                && three == valueSlot.HORSESHOE && one != valueSlot.HORSESHOE)) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if(one == valueSlot.HORSESHOE && three == valueSlot.HORSESHOE
                && two != valueSlot.HORSESHOE) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if (one == valueSlot.PLUM && two == valueSlot.PLUM &&
                three == valueSlot.PLUM) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if((one == valueSlot.PLUM && two == valueSlot.PLUM
                && three != valueSlot.PLUM) || (two == valueSlot.PLUM
                && three == valueSlot.PLUM && one != valueSlot.PLUM)) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if(one == valueSlot.PLUM && three == valueSlot.PLUM
                && two != valueSlot.PLUM) {
            Bank.money_bank += 25;
            money_won = 25;
        }
        else if (one == valueSlot.SEVEN && two == valueSlot.SEVEN &&
                three == valueSlot.SEVEN) {
            Bank.money_bank *= 2;
            money_won = 999999;
        }
        else if((one == valueSlot.SEVEN && two == valueSlot.SEVEN
                && three != valueSlot.SEVEN) || (two == valueSlot.SEVEN
                && three == valueSlot.SEVEN && one != valueSlot.SEVEN)) {
            Bank.money_bank += 250;
            money_won = 250;
        }
        else if(one == valueSlot.SEVEN && three == valueSlot.SEVEN
                && two != valueSlot.SEVEN) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if (one == valueSlot.WATERMELON && two == valueSlot.WATERMELON &&
                three == valueSlot.WATERMELON) {
            Bank.money_bank += 100;
            money_won = 100;
        }
        else if((one == valueSlot.WATERMELON && two == valueSlot.WATERMELON
                && three != valueSlot.WATERMELON) || (two == valueSlot.WATERMELON
                && three == valueSlot.WATERMELON && one != valueSlot.WATERMELON)) {
            Bank.money_bank += 50;
            money_won = 50;
        }
        else if(one == valueSlot.WATERMELON && three == valueSlot.WATERMELON
                && two != valueSlot.WATERMELON) {
            Bank.money_bank += 25;
            money_won = 25;
        }

        if(one != two && two != three
                && one != three) {
            if (Bank.money_bank < 100) {
                gameView.lose();
            }
        }

        gameView.setBank(money_won);
    }
}
