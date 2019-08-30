package com.kaznowski.blackjackml.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private final List<Card> cards = new ArrayList<>();

  public Deck() {
    for ( CardColour cardColour : CardColour.values() ) {
      for ( CardValue cardValue : CardValue.values() ) {
        cards.add( new Card( cardValue, cardColour ) );
      }
    }
  }

  public void shuffle() {
    Collections.shuffle( cards );
  }

  @Override
  public String toString() {
    return String.format( "Deck%s", cards );
  }

  List<Card> getCards() { // TODO this makes it testable, but it is leaking implementation. Games dont know whats in
    // the deck and that can change in multi-pack decks
    return new ArrayList<>( cards );
  }

  public Card pullCard() {
    return cards.remove( 0 );
  }
}
