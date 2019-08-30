package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Player {
  private final String alias;
  private final Hand hand = new Hand();
  private final Function<List<PlayerChoice>,PlayerChoice> decisionAlgorithm;

  public Player( String alias, Function<List<PlayerChoice>,PlayerChoice> decisionAlgorithm ) {
    this.alias = alias;
    this.decisionAlgorithm = decisionAlgorithm;
  }

  public void deal( Card card ) {
    hand.addCard( card );
  }

  public Hand getHand() {
    return hand;
  }

  public PlayerChoice giveChoices( PlayerChoice... choices ) {
    return decisionAlgorithm.apply( Arrays.asList( choices ) );
  }

  @Override
  public String toString() {
    return alias;
  }
}
