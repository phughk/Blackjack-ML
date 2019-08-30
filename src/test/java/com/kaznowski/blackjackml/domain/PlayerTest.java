package com.kaznowski.blackjackml.domain;

import com.kaznowski.blackjackml.simulation.Player;
import org.junit.jupiter.api.Test;

import static com.kaznowski.blackjackml.helpers.TestHelpers.card;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
  private static final int BLACKJACK = 21;

  @Test
  void trivialHighestValue() {
    Player player = new Player("Player");
    player.deal( card( CardValue.KING ) );
    player.deal( card( CardValue.QUEEN ) );

    // when
    int score = player.getScore( BLACKJACK );

    // then
    assertEquals( 20, score );
  }

  @Test
  void aceTreatedLowWhenOver() {
    Player player = new Player("Player");
    player.deal( card( CardValue.ACE ) );
    player.deal( card( CardValue.NINE ) );
    player.deal( card( CardValue.FOUR ) );

    // when
    int score = player.getScore( BLACKJACK );

    // then
    assertEquals( 14, score );
  }
}
