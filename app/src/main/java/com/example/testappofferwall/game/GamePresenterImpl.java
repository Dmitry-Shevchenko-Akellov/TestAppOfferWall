package com.example.testappofferwall.game;

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
}
