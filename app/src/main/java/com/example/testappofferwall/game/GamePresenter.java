package com.example.testappofferwall.game;

import com.example.testappofferwall.Base.BasePresenter;

public interface GamePresenter extends BasePresenter<GameView> {

    void startSpin(int one, int two, int three);
}
