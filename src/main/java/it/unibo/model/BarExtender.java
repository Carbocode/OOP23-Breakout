package it.unibo.model;



public class BarExtender {
    private static final int INCREASE_AMOUNT = 50;

    public static void extendBar(Bar bar){
        bar.setWidth(bar.getSize().width + INCREASE_AMOUNT);
    }

}