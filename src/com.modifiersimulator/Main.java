package com.modifiersimulator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Main {
  private static int turnsToSimulate = 100000;
  final static private Random random = new Random();

  public static void main(String[] args) {
    LinkedList<String> arguments = new LinkedList<>();
    for (int i = 0; i < args.length; i++) {
      arguments.addLast(args[i]);
    }
    int attacksPerTurn = Integer.getInteger(arguments.poll(), 1);
    int attack = Integer.getInteger(arguments.poll(), 4);
    int shield = Integer.getInteger(arguments.poll(), 2);
    float cursesPerTurn = Float.parseFloat(arguments.peek() != null ? arguments.poll() : "0.5");

    System.out.println("attacksPerTurn: " + attacksPerTurn);
    System.out.println("attack: " + attack);
    System.out.println("shield: " + shield);
    System.out.println("cursesPerTurn: " + cursesPerTurn);
    System.out.println();

    ModifierSimulator simulator = new ModifierSimulator(turnsToSimulate, attacksPerTurn, attack, shield, cursesPerTurn);

    float normalAverage = simulator.simulateNormal();
    float disadvantageAverage = simulator.simulateDisadvantage();
    System.out.println("Attack: " + attack + ", Shield: " + shield);
    System.out.println("Avg.dmg.: Normal: " + normalAverage + "\tDisadv: " + disadvantageAverage);
    System.out.println("Difference in damage: " + (disadvantageAverage - normalAverage));
    System.out.println();
  }
}
