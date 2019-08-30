package com.kaznowski.blackjackml.domain;

import com.kaznowski.blackjackml.helpers.Combinatorics;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {
  private final List<Card> cards;

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public void addCard( Card card ) {
    cards.add( card );
  }

  public Set<Integer> unfilteredScoresFromCombinations() {
    List<List<Integer>> allOptions = cards.stream().map( Card::allValues ).collect( Collectors.toList() );
    List<List<Integer>> allSelections = Combinatorics.oneFromEach( allOptions );
    return allSelections
        .stream()
        .flatMap( integerList -> Stream.of( integerList.stream().mapToInt( i -> i ).sum() ) )
        .collect( Collectors.toSet() );
  }

  public int getLowestScore() {
    return unfilteredScoresFromCombinations().stream().mapToInt( i -> i ).min().orElse( 0 );
  }

  /**
   * @param blackjack the highest possible value
   * @return empty if player is out, otherwise 0 and up indicate their highest score
   */
  public OptionalInt getHighestScore( int blackjack ) {
    Set<Integer> scores = unfilteredScoresFromCombinations();
    if ( scores.size() == 0 ) {
      return OptionalInt.of( 0 );
    }
    return scores.stream().mapToInt( i -> i ).filter( i -> i <= blackjack ).max();
  }

  public int getHighestUnboundeScore() {
    return unfilteredScoresFromCombinations().stream().mapToInt( i->i ).max().orElse( 0 );
  }

}
