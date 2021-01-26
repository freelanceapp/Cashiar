package com.cashiar.interfaces;


public interface Listeners {

    interface LoginListener {
        void checkDataLogin(String phone_code, String phone);
    }
    interface TransFerListener {
        void checkData(String amount);
    }
    interface SkipListener
    {
        void skip();
    }
    interface CreateAccountListener
    {
        void createNewAccount();
    }

    interface ShowCountryDialogListener
    {
        void showDialog();
    }

    interface SignUpListener
    {
        void checkDataValid();

    }
    interface EditprofileListener
    {
        void Editprofile(String name);
        void Editprofile(String englishname, String arabicname);


    }

    interface BackListener
    {
        void back();
    }


    interface ContactActions {
        void email();

        void facebook();

        void whats();

        void twitter();
        void instegram();
        void telegram();
        void youtube();
    }

    interface ForgetListner
    {
        void forget();
    }
    interface PasswordListner {
        void checkDatapass(String pass);
    }
    interface ForgetpasswordListner {
        void checkDataForget(String phone_code, String phone);
    }
}
