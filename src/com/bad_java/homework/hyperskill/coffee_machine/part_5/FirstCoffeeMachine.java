package com.bad_java.homework.hyperskill.coffee_machine.part_5;

import java.io.InputStream;
import java.io.PrintStream;

public class FirstCoffeeMachine extends CoffeeMachine {

    public FirstCoffeeMachine(){
        this(System.in, System.out, 400, 540, 120, 9, 550);
    }

    public FirstCoffeeMachine(InputStream input, PrintStream output){
        this(input, output, 400, 540, 120, 9, 550);
    }

    public FirstCoffeeMachine(InputStream input, PrintStream output, int water, int milk, int beans, int cups, int money){
        super(input, output, water, milk, beans, cups, money);
    }

    @Override
    public void countingIngredients() {
        Coffee coffee = new FirstCoffee();
        console.println("Write how many cups of coffee you will need: ");
        int numOfPortions = getNumberOfPortions();
        console.println(String.format("For %d cups of coffee you will need: %n", numOfPortions));
        console.println(String.format("%d ml of water %n", coffee.countWaterForCups(numOfPortions)));
        console.println(String.format("%d ml of milk %n", coffee.countMilkForCups(numOfPortions)));
        console.println(String.format("%d g of coffee beans %n", coffee.countBeansForCups(numOfPortions)));
    }

    @Override
    public void countOfAvailableServings() {
        addResources();
        Coffee coffee = new FirstCoffee();
        sendMessageAboutPossibleCup(maxAmountOfPossibleCups(coffee), amountOfNeededCups());
    }

    public Coffee getCoffee() {
        console.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        String typeCoffee = this.console.readLine().toLowerCase();
        switch (typeCoffee) {
            case "1":
                return new Espresso();
            case "2":
                return new Latte();
            case "3":
                return new Cappuccino();
            case "back":
                return null;
            default:
                console.println("Have no such a coffee");
                break;
        }
        return null;
    }

    @Override
    public Action getAction() {
        console.println("Write action (buy, fill, take, remaining, exit): ");
        String action = console.readLine().toUpperCase();
        switch (action) {
            case "FILL":
                return new Fill(this);
            case "TAKE":
                return new Take(this);
            case "BUY":
                Coffee coffee = getCoffee();
                if(coffee == null) {
                    return new Continue(this);
                }
                return new Buy(this, coffee);

            case "REMAINING":
                return new Remaining(this);
            case "EXIT":
                return new Exit(this);

        }
        return null;
    }

    private void addResources() {
        console.println("Write how many ml of water the coffee machine has:");
        this.setAmountOfWater(console.readInt());
        console.println("Write how many ml of milk the coffee machine has:");
        this.setAmountOfMilk(console.readInt());
        console.println("Write how many grams of coffee beans the coffee machine has:");
        this.setAmountOfBeans(console.readInt());
    }

    private void sendMessageAboutPossibleCup(int maxAmountOfCups, int neededCups){
        int resultOfComparison = Integer.compare(maxAmountOfCups, neededCups);
        switch (resultOfComparison) {
            case 1:
                console.println(String.format("Yes, I can make that amount of coffee " +
                        "(and even %d more than that)", maxAmountOfCups - neededCups));
                break;
            case 0:
                console.println("Yes, I can make that amount of coffee");
                break;
            case -1:
                console.println(String.format("No, I can make only %d cup(s) of coffee", maxAmountOfCups));
                break;
        }
    }


    private int maxAmountOfPossibleCups(Coffee coffee){
        int possibleByWater = getAmountOfWater().getAmount() / coffee.getWaterAmount();
        int possibleByMilk = getAmountOfMilk().getAmount() / coffee.getMilkAmount();
        int possibleByBeans = getAmountOfBeans().getAmount() / coffee.getBeansAmount();
        return Math.min(possibleByBeans, Math.min(possibleByMilk, possibleByWater));
    }

    private int amountOfNeededCups() {
        console.println("How many cups will you need? ");
        return console.readInt();
    }

    private int getNumberOfPortions() {
        return console.readInt();
    }

    @Override
    public void decResources(int water, int milk, int beans, int cups, int money) {
        if(resources.hasEnoughResources(water, milk, beans, cups, money)){
            addResources(water, milk, beans, cups, money);
        }
    }

    public void addResources(int water, int milk, int beans, int cups, int money) {
        addAmountOfWater(water);
        addAmountOfMilk(milk);
        addAmountOfBeans(beans);
        addAmountOfCups(cups);
        addAmountOfMoney(money);
    }



    @Override
    public Ingredient gettingRidOfMoney() {
        Ingredient giveAmount = getAmountOfMoney();
        this.console.println(String.format("I gave you %s%d", getAmountOfMoney().getUnit(), giveAmount.getAmount()));
        setAmountOfMoney(0);
        return giveAmount;
    }

    @Override
    public void notifyAboutState() {
        super.printResources();
    }
}

