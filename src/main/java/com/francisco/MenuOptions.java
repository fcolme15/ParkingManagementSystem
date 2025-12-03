package com.francisco;

public enum MenuOptions{
    PARK(1),
    REMOVE(2),
    SEARCH(3),
    PRINT(4),
    EXIT(5);

    private final int option;

    MenuOptions(int option){
        this.option = option;
    }

    //Convert the integer input into Enum representation
    public static MenuOptions fromInt(int input){
        MenuOptions[] options = MenuOptions.values();

        if (input >= 0 && input < options.length){ //Check bounds
            return options[input - 1];
        }
        return null;
    }


}
