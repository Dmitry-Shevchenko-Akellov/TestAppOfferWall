package com.example.testappofferwall.Base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
