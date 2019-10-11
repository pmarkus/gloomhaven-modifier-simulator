package com.modifiersimulator;

import java.util.ArrayList;
import java.util.Random;

public class ModifierDeck {
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
    if (drawnCard != ModifierCard.BLESS || drawnCard != ModifierCard.CURSE) {
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
    deck.add(ModifierCard.BLESS);
  }

  public void addCurse() {
    deck.add(ModifierCard.CURSE);
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
