package com.kaznowski.blackjackml.domain;

import com.kaznowski.blackjackml.helpers.ArtificialPlayerChoices;
import com.kaznowski.blackjackml.simulation.Player;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static com.kaznowski.blackjackml.helpers.TestHelpers.card;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
  private static final int BLACKJACK = 21;

  @Test
  void trivialHighestValue() {
    Player player = new Player( "Player", new ArtificialPlayerChoices.Throwing() );
    player.deal( card( CardValue.KING ) );
    player.deal( card( CardValue.QUEEN ) );

    // when
    OptionalInt score = player.getHand().getHighestScore( BLACKJACK );

    // then
    assertEquals( OptionalInt.of( 20 ), score ); // TODO verify testing player
  }

  @Test
  void aceTreatedLowWhenOver() {
    Player player = new Player( "Player", new ArtificialPlayerChoices.Throwing() );
    player.deal( card( CardValue.ACE ) );
    player.deal( card( CardValue.NINE ) );
    player.deal( card( CardValue.FOUR ) );

    // when
    OptionalInt score = player.getHand().getHighestScore( BLACKJACK );

    // then
    assertEquals( OptionalInt.of( 14 ), score );
  }
}
