package com.kaznowski.blackjackml.domain;

import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card> {
  private final CardValue value;
  private final CardColour colour;

  public Card( CardValue value, CardColour colour ) {
    this.value = value;
    this.colour = colour;
  }

  public int compareTo( Card card ) {
    return card.value.getHighestValue() - value.getHighestValue();
  }

  public List<Integer> allValues() {
    List<Integer> values = new ArrayList<>();
    values.add( this.value.getValue() );
    this.value.getAltValue().ifPresent( values::add );
    return values;
  }

  @Override
  public String toString() {
    return String.format( "%s of %s", value, colour );
  }
}
