package com.modifiersimulator;

import java.util.ArrayList;
import java.util.Random;

public class ModifierDeck {
  final private static int MAX_BLESS_CARDS = 10;
  final private static int MAX_CURSE_CARDS = 10;
  private int blesses = 0;
  private int curses = 0;

  final private ArrayList<ModifierCard> deck = new ArrayList<ModifierCard>();
  final private ArrayList<ModifierCard> drawnCards = new ArrayList<ModifierCard>();
  final private Random random = new Random();

  public ModifierDeck() {
    deck.add(ModifierCard.MISS);
    deck.add(ModifierCard.MINUS_TWO);
    deck.add(ModifierCard.PLUS_TWO);
    deck.add(ModifierCard.CRIT);
    for (int i = 0; i < 5; i++) {
      deck.add(ModifierCard.MINUS_ONE);
      deck.add(ModifierCard.PLUS_ONE);
      deck.add(ModifierCard.ZERO);
    }
    deck.add(ModifierCard.ZERO);
  }

  public ModifierCard drawOne() {
    if (deck.size() < 1) {
      reshuffleDeck();
    }
    final int positionToDraw = random.nextInt(deck.size());
    final ModifierCard drawnCard = deck.remove(positionToDraw);
    if (drawnCard == ModifierCard.BLESS) {
      blesses--;
    } else if (drawnCard == ModifierCard.CURSE) {
      curses--;
    } else {
      drawnCards.add(drawnCard);
    }
    return drawnCard;
  }

  public ModifierCard drawDisadvantage() {
    final ModifierCard firstCard = drawOne();
    final ModifierCard secondCard = drawOne();
    if (firstCard.value() <= secondCard.value()) {
      return firstCard;
    } else {
      return secondCard;
    }
  }

  public void addBless() {
    if (blesses < MAX_BLESS_CARDS) {
      blesses++;
      deck.add(ModifierCard.BLESS);
    }
  }

  public void addCurse() {
    if (curses < MAX_CURSE_CARDS) {
      curses++;
      deck.add(ModifierCard.CURSE);
    }
  }

  public void reshuffleIfNecessary() {
    if (drawnCards.contains(ModifierCard.MISS) || drawnCards.contains(ModifierCard.CRIT)) {
      reshuffleDeck();
    }
  }

  private void reshuffleDeck() {
    deck.addAll(drawnCards);
    drawnCards.clear();
  }

  private void reshuffleAllButOne(ModifierCard keepCard) {
    reshuffleDeck();
    deck.remove(keepCard);
    drawnCards.add(keepCard);
  }

  public int size() {
    return deck.size();
  }

  @Override
  public String toString() {
    StringBuilder deckContents = new StringBuilder();
    for (ModifierCard card : deck
    ) {
      deckContents.append(card.toString() + ", ");
    }
    return "Main.ModifierDeck{" +
        deckContents.toString() +
        "}";
  }
}
