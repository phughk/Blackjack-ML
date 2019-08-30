package com.kaznowski.blackjackml.domain;

import com.kaznowski.blackjackml.helpers.Combinatorics;

import java.util.ArrayList;
import java.util.List;
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

  public Set<Integer> potentialScores() {
    List<List<Integer>> allOptions = cards.stream().map( Card::allValues ).collect( Collectors.toList() );
    List<List<Integer>> allSelections = Combinatorics.oneFromEach( allOptions );
    return allSelections
        .stream()
        .flatMap( integerList -> Stream.of( integerList.stream().mapToInt( i -> i ).sum() ) )
        .collect( Collectors.toSet() );
  }

  public int getLowestTotal() {
    return potentialScores().stream().mapToInt( i -> i ).min().orElse( 0 );
  }
}
