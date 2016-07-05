package com.xin.bladedb.login;

/**
 * Created by Xin on 7/4/2016.
 */
public interface LoginView {
    void hideProgress();
    void showProgress();
    void showErrorMessage(String message);
    void continueToMainActivity();
    void showEmailWarning(String message);
    void showPasswordWarning(String message);
}