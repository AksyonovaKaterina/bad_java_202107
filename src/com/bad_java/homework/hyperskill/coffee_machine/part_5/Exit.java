package com.bad_java.homework.hyperskill.coffee_machine.part_5;

public class Exit extends Action{

    public Exit(CoffeeMachine curMachine) {
        super(System.in, System.out, curMachine);
    }

    @Override
    public boolean modifyCoffeeMachineState() {
        return false;
    }

    @Override
    public boolean isContinuing() {
        return false;
    }
}
