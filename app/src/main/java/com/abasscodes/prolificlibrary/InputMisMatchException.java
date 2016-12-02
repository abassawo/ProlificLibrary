package com.abasscodes.prolificlibrary;

/**
 * Created by C4Q on 12/2/16.
 */

public class InputMisMatchException extends Exception {

    public InputMisMatchException(){
        super("The text entered did not match expected/valid type");
    }

}
