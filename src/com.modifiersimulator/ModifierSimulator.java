package com.modifiersimulator;

import java.util.LinkedList;
import java.util.Random;

public class ModifierSimulator {
  final private Random random = new Random();

  final private int turnsToSimulate;
  final private int attacksPerTurn;
  final private int attack;
  final private int shield;
  final private float cursesPerTurn;

  ModifierDeck modifierDeck;

  public ModifierSimulator(int turnsToSimulate, int attacksPerTurn, int attack, int shield, float cursesPerTurn) {
    this.turnsToSimulate = turnsToSimulate;
    this.attacksPerTurn = attacksPerTurn;
    this.attack = attack;
    this.shield = shield;
    this.cursesPerTurn = cursesPerTurn;

    modifierDeck = new ModifierDeck();
  }

  public float simulate(boolean disadvantage) {
    modifierDeck = new ModifierDeck();
    ModifierCard drawnCard;

    int totalTurns = 0;
    int totalDamageMade = 0;
    for (int turn = 0; turn < turnsToSimulate; turn++) {
      totalTurns++;
      addCurses();
      for (int action = 0; action < attacksPerTurn; action++) {
        if (disadvantage) {
          drawnCard = modifierDeck.drawDisadvantage();
        } else {
          drawnCard = modifierDeck.drawOne();
        }
        totalDamageMade += drawnCard.apply(attack, shield);
      }
      modifierDeck.reshuffleIfNecessary();
    }

    return (float) totalDamageMade / (float) totalTurns;
  }

  private void addCurses() {
    float cursesLeftToAdd = cursesPerTurn;

    while (cursesLeftToAdd > 1) {
      cursesLeftToAdd--;
      modifierDeck.addCurse();
    }
    if (random.nextFloat() <= cursesLeftToAdd) {
      modifierDeck.addCurse();
    }
  }
}
