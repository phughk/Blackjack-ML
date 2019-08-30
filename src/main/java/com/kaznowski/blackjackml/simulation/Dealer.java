package com.kaznowski.blackjackml.simulation;

class Dealer extends Player {
  boolean isBelowRuleMinimum( int ruleMinimum, int blackjack ) {
    return getHand().potentialScores().stream().mapToInt( i -> i ).filter( i -> i < blackjack ).max().orElse( 0 ) <
        ruleMinimum;
  }
}
