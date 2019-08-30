package com.kaznowski.blackjackml.simulation;

public class Dealer extends Player {
  public Dealer( String alias ) {
    super( alias );
  }

  boolean isBelowRuleMinimum( int ruleMinimum, int blackjack ) {
    return getHand().potentialScores().stream().mapToInt( i -> i ).filter( i -> i < blackjack ).max().orElse( 0 ) <
        ruleMinimum;
  }
}
