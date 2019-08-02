package com.example.testappofferwall.start;

import com.example.testappofferwall.Base.BaseView;

public interface StartView extends BaseView {

    void error();

    void startNextStep(boolean allowAttribute);
}
