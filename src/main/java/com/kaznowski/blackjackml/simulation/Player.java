package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Hand;

public class Player {
  private final String alias;
  private final Hand hand = new Hand();

  public Player(String alias) {
    this.alias = alias;
  }

  public void deal( Card card ) {
    hand.addCard( card );
  }

  public Hand getHand() {
    return hand;
  }

  public PlayerChoice giveChoices( PlayerChoice... choices ) {
    return choices[0];
  }

  public int getScore( int blackjack ) {
    return hand.potentialScores().stream().mapToInt( i -> i ).filter( i -> i < blackjack ).max().orElse( 0 );
  }

  @Override
  public String toString() {
    return alias;
  }
}
