package com.bad_java.homework.hyperskill.coffee_machine.part_5;

public class CoffeeShop {
    public static void main(String[] args) {
        CoffeeMachine myMachine = new FirstCoffeeMachine();
        //myMachine.actionSequence();
        //myMachine.countingIngredients();
        //myMachine.countOfAvailableServings();
        //myMachine.printResources();
        //myMachine.printResources();
        Action curAction;
        do {
            curAction = myMachine.getAction();
        } while (curAction.isContinuing() && curAction.modifyCoffeeMachineState());


        //myMachine.printResources();
    }
}
