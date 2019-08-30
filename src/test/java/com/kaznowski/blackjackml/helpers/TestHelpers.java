package com.kaznowski.blackjackml.helpers;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.CardColour;
import com.kaznowski.blackjackml.domain.CardValue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestHelpers {

  public static Card card( CardValue cardValue ) {
    return new Card( cardValue, CardColour.HEARTS );
  }

  public static <E> Set<E> set(E... es ) {
    return Stream.of(es ).collect( Collectors.toSet() );
  }
}
