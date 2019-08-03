package com.example.testappofferwall.game;

import com.example.testappofferwall.Base.BaseView;

public interface GameView extends BaseView {

    void spinEnd(int result, int i);

    void lose();

    void setBank(int money_won);
}
