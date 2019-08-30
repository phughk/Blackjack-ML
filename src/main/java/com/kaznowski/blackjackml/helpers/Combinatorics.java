package com.kaznowski.blackjackml.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Combinatorics {
  public static <E> List<List<E>> oneFromEach( List<List<E>> entrySets ) {
    if ( entrySets.size() == 0 ) {
      return new ArrayList<>();
    }
    if ( entrySets.size() == 1 ) {
      // turn [[1,2,3]] into [[1],[2],[3]]
      List<List<E>> values =
          entrySets.get( 0 ).stream().map( Arrays::asList ).collect( Collectors.toList() );
      return values;
    }

    List<List<E>> tail = new ArrayList<>( entrySets );
    List<E> head = tail.remove( 0 );
    List<List<E>> values = new ArrayList<>();
    List<List<E>> tailPermutations = oneFromEach( tail );
    for ( int headIndex = 0; headIndex < head.size(); headIndex++ ) {
      for ( int permutationIndex = 0; permutationIndex < tailPermutations.size(); permutationIndex++ ) {
        List<E> permutation = new ArrayList<>( tailPermutations.get( permutationIndex ) );
        permutation.add( 0, head.get( headIndex ) ); // insert head in front of permutation
        values.add( permutation );
      }
    }
    return values;
  }
}
