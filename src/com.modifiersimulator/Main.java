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
    int attacksPerTurn = Integer.getInteger(arguments.poll(), 4);
    int attack = Integer.getInteger(arguments.poll(), 3);
    int shield = Integer.getInteger(arguments.poll(), 2);
    float cursesPerTurn = Float.parseFloat(arguments.peek() != null ? arguments.poll() : "0");

    System.out.println("attacksPerTurn: " + attacksPerTurn);
    System.out.println("attack: " + attack);
    System.out.println("shield: " + shield);
    System.out.println("cursesPerTurn: " + cursesPerTurn);
    System.out.println();

    ModifierSimulator simulator = new ModifierSimulator(turnsToSimulate, attacksPerTurn, attack, shield, cursesPerTurn);

    float normalAverage = simulator.simulate(false);
    float disadvantageAverage = simulator.simulate(true);
    simulator = new ModifierSimulator(turnsToSimulate, attacksPerTurn, attack, shield+1, cursesPerTurn);
    float extraShieldAverage = simulator.simulate(false);
    System.out.println("Average damage per turn:");
    System.out.println("Normal: \t" + normalAverage);
    System.out.println("+1 shield: \t" + extraShieldAverage + "\t(normal " + (extraShieldAverage - normalAverage) + ")");
    System.out.println("Disadv.: \t" + disadvantageAverage + "\t(normal " + (disadvantageAverage - normalAverage) + ")");
  }
}
