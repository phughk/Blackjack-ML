package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;

import java.util.Arrays;
import java.util.List;

public class PriorityShuffleMechanism implements ShuffleMechanism {

  private final List<Card> priorityCards;

  public PriorityShuffleMechanism( Card... priorityCards ) {
    this.priorityCards = Arrays.asList( priorityCards );
  }

  @Override
  public void shuffle( List<Card> list ) {
    // Reverse order to maintain priority
    for ( int priorityCardIndex = priorityCards.size() - 1; priorityCardIndex >= 0; priorityCardIndex-- ) {
      int deckIndex = list.indexOf( priorityCards.get( priorityCardIndex ) );
      Card stackedCard = list.remove( deckIndex );
      list.add( 0, stackedCard );
    }
  }
}
