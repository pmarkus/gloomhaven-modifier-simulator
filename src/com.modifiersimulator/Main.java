package com.modifiersimulator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Main {
  private static int turnsToSimulate = 100000;
  final static private Random random = new Random();

  public static void main(String[] args) {
    if (args.length == 0) {
      for (int actions = 1; actions <= 4; actions++) {
        for (int attack = 2; attack <= 6; attack++) {
          for (int shield = 0; shield <= 3; shield++) {
            for (float curses = 0; curses <= 1.5f; curses+=0.5) {
              main(new String[]{
                  String.valueOf(actions),
                  String.valueOf(attack),
                  String.valueOf(shield),
                  String.valueOf(curses)});
            }
          }
        }
      }
    }

    LinkedList<String> arguments = new LinkedList<>();
    for (int i = 0; i < args.length; i++) {
      arguments.add(args[i]);
    }
    int attacksPerTurn = Integer.parseInt(arguments.peek() != null ? arguments.poll() : "3");
    int attack = Integer.parseInt(arguments.peek() != null ? arguments.poll() : "4");
    int shield = Integer.parseInt(arguments.peek() != null ? arguments.poll() : "2");
    float cursesPerTurn = Float.parseFloat(arguments.peek() != null ? arguments.poll() : "0");

    System.out.println("Actions: " + attacksPerTurn +
        "\tAttack: " + attack +
        "\tShield: " + shield +
        "\tCurses/turn: " + cursesPerTurn);

    ModifierSimulator simulator = new ModifierSimulator(turnsToSimulate, attacksPerTurn, attack, shield, cursesPerTurn);

    float normalAverage = simulator.simulate(false);
    float disadvantageAverage = simulator.simulate(true);
    simulator = new ModifierSimulator(turnsToSimulate, attacksPerTurn, attack, shield+1, cursesPerTurn);
    float extraShieldAverage = simulator.simulate(false);
    System.out.println("Average damage per turn:");
    System.out.println("Normal: \t" + normalAverage);
    System.out.println("+1 shield: \t" + extraShieldAverage + "\t(normal " + (extraShieldAverage - normalAverage) + ")");
    System.out.println("Disadv.: \t" + disadvantageAverage + "\t(normal " + (disadvantageAverage - normalAverage) + ")");
    System.out.println();
  }
}
