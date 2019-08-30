package com.kaznowski.blackjackml.domain;

import java.util.Optional;

public enum CardValue {
  TWO( 2, null),
  THREE( 3, null),
  FOUR( 4, null ),
  FIVE( 5, null ),
  SIX( 6, null ),
  SEVEN( 7, null ),
  EIGHT( 8, null ),
  NINE( 9, null ),
  TEN( 10, null ),
  JACK( 10, null ),
  QUEEN( 10, null ),
  KING( 10, null ),
  ACE( 1, 11);

  private final int value;
  private final Integer altValue;

  CardValue( int value, Integer altValue ) {
    this.value = value;
    this.altValue = altValue;
  }

  public int getValue() {
    return value;
  }

  public Optional<Integer> getAltValue() {
    return Optional.ofNullable( altValue );
  }

  public int getHighestValue() {
    return getAltValue().orElse( getValue() );
  }
}
