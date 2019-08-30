package com.kaznowski.blackjackml.domain;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.kaznowski.blackjackml.helpers.TestHelpers.card;
import static com.kaznowski.blackjackml.helpers.TestHelpers.set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

  @Test
  void onlyOnePossibility() {
    // given
    Hand hand = new Hand();
    hand.addCard( card( CardValue.FOUR ) );
    hand.addCard( card( CardValue.KING ) );

    // when
    Set<Integer> scores = hand.potentialScores();

    // then
    assertEquals( set( 14 ), scores );
  }

  @Test
  void doubleAceCheck() {
    Hand hand = new Hand();
    hand.addCard( card( CardValue.THREE ) );
    hand.addCard( card( CardValue.ACE ) );
    hand.addCard( card( CardValue.ACE ) );
    hand.addCard( card( CardValue.SIX ) );

    // when
    Set<Integer> scores = hand.potentialScores();

    // then
    assertEquals( scores, set( 11, 21, 31 ) );
  }
}
