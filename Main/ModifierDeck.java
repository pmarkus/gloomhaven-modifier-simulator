package Main;

import java.util.ArrayList;
import java.util.Random;

public class ModifierDeck {
    private ArrayList<ModifierCard> deck = new ArrayList<ModifierCard>();
    final private Random random = new Random();

    public ModifierDeck() {
      reshuffleDeck();
    }

    public ModifierCard drawOne() {
        if (deck.size() < 1) {
            reshuffleDeck();
        }
        final int positionToDraw = random.nextInt(deck.size());
        final ModifierCard drawnCard = deck.remove(positionToDraw);
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

    public void reshuffleIfNecessary() {
        if (!deck.contains(ModifierCard.MISS) || !deck.contains(ModifierCard.CRIT)) {
            reshuffleDeck();
        }
    }

    private void reshuffleDeck() {
        deck = new ArrayList<>();
        deck.add(ModifierCard.MISS);
        deck.add(ModifierCard.MINUS_TWO);
        deck.add(ModifierCard.PLUS_TWO);
        deck.add(ModifierCard.CRIT);
        for (int i = 0; i<5; i++) {
            deck.add(ModifierCard.MINUS_ONE);
            deck.add(ModifierCard.PLUS_ONE);
            deck.add(ModifierCard.ZERO);
        }
        deck.add(ModifierCard.ZERO);
    }

    private void reshuffleAllButOne(ModifierCard keepCard) {
        reshuffleDeck();
        deck.remove(keepCard);
    }

    @Override
    public String toString() {
        StringBuilder deckContents = new StringBuilder();
        for (ModifierCard card: deck
             ) {
            deckContents.append(card.toString() + ", ");
        }
        return "Main.ModifierDeck{" +
                deckContents.toString() +
                "}";
    }

    public int size() {
        return deck.size();
    }
}
