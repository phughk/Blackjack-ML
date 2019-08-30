package com.kaznowski.blackjackml.simulation;

import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class Dealer extends Player {
  public Dealer( String alias ) {
    super( alias, choice -> {
      throw new IllegalStateException( "Dealer cannot make choices" );
    } );
  }

  boolean shouldPickUpCard( int ruleMinimum, int blackjack ) {
    // "An ace is an eleven unless it would bust your hand"
    // https://boardgames.stackexchange.com/questions/35613/if-dealer-is-dealt-an-ace-and-then-dealt-another-card-will-he-bust

    Set<Integer> removedBustedHands = getHand()
        .unfilteredScoresFromCombinations()
        .stream()
        .filter( i -> i <= blackjack )
        .collect( Collectors.toSet() );
    OptionalInt highestValue = removedBustedHands.stream().mapToInt( i -> i ).max();
    if ( !highestValue.isPresent() ) {
      // we either busted or we don't have cards.
      // TODO verify we dont have cards?
      return false; // we busted
    }
    // Basically if we are rule minimum or more, then we shouldn't pick up
    return highestValue.getAsInt() < ruleMinimum;
  }
}
