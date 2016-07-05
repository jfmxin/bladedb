package com.xin.bladedb.signup;

/**
 * Created by Xin on 7/4/2016.
 */
public interface SignUpView {
    void hideProgress();
    void showProgress();
    void showErrorMessage(String message);
}
