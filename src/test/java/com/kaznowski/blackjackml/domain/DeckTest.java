package com.kaznowski.blackjackml.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {
  @Test
  void has52Cards() {
    Deck deck = new Deck();

    assertEquals( 52, deck.getCards().size() );
  }
}
