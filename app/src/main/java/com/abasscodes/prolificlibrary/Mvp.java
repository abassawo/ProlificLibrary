package com.abasscodes.prolificlibrary;

/**
 * Created by C4Q on 11/15/16.
 */

public interface Mvp {

    public interface Model{

    }

    public interface View{

    }

    public interface Presenter{
//        void acceptCommand(Command command);
        void updateUI();
        void onConnectionFailure();
        void showNetworkSettings();
    }
}
