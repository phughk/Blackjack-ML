package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Hand;

public class Player {
  private final Hand hand = new Hand();

  public void deal( Card card ) {
    hand.addCard(card);
  }

  public Hand getHand() {
    return hand;
  }

  public PlayerChoice giveChoices(PlayerChoice... choices) {
    return choices[0];
  }
}
