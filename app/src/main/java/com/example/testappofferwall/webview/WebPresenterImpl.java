package com.example.testappofferwall.webview;


public class WebPresenterImpl implements WebPresenter {

    private WebView webView;

    @Override
    public void attachView(WebView view) {
        webView = view;
    }

    @Override
    public void detachView() {
        webView = null;
    }
}
