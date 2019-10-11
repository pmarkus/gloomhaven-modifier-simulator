package com.modifiersimulator;

public class Main {
    private static int attacksToMake = 10000;

    public static void main(String[] args) {

        for (int strengthOfAttack = 2; strengthOfAttack <= 5; strengthOfAttack++) {
            for (int shield = 0; shield <= 4; shield++) {
                float normalAverage = estimateNormal(strengthOfAttack, shield);
                float disadvantageAverage = estimateDisadvantage(strengthOfAttack, shield);
                System.out.println("Attack: " + strengthOfAttack + ", Shield: " + shield);
                System.out.println("Avg.dmg.: Normal: " + normalAverage + "\tDisadv: " + disadvantageAverage);
                System.out.println("Difference in damage: " + (disadvantageAverage - normalAverage));
                System.out.println();
            }
        }
    }

    private static float estimateNormal(int strengthOfAttack, int shield) {
        ModifierDeck modifierDeck = new ModifierDeck();
        ModifierCard drawnCard;

        int totalAttacks = 0;
        int totalDamageMade = 0;
        for (int i = 0; i < attacksToMake; i++) {
            drawnCard = modifierDeck.drawOne();
            modifierDeck.reshuffleIfNecessary();
            totalAttacks++;
            totalDamageMade += drawnCard.apply(strengthOfAttack, shield);
        }

       return (float)totalDamageMade / (float)totalAttacks;
    }

    private static float estimateDisadvantage(int strengthOfAttack, int shield) {
        ModifierDeck modifierDeck = new ModifierDeck();
        ModifierCard drawnCard;

        int totalAttacks = 0;
        int totalDamageMade = 0;
        for (int i = 0; i < attacksToMake; i++) {
            drawnCard = modifierDeck.drawDisadvantage();
            modifierDeck.reshuffleIfNecessary();
            totalAttacks++;
            totalDamageMade += drawnCard.apply(strengthOfAttack, shield);
        }

        return (float)totalDamageMade / (float)totalAttacks;
    }
}
