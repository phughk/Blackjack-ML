package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.CardValue;
import com.kaznowski.blackjackml.interfaces.GameEventHandlerAdapter;
import com.kaznowski.blackjackml.interfaces.PriorityShuffleMechanism;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.kaznowski.blackjackml.helpers.TestHelpers.card;

class GameTest {
  @Test
  void dealerCanBlackjack() {
    Game game = new Game(
        new PriorityShuffleMechanism( card( CardValue.SEVEN ), card( CardValue.SIX ), card( CardValue.EIGHT ),
            card( CardValue.FOUR ) ) );

    // then fail if the dealer goes beyond blackjack
    game.addGameEventHandler( new GameEventHandlerAdapter() {
      @Override
      public void dealtCardToDealer( Dealer dealer, Card card ) {
        if ( card.equals( card( CardValue.FOUR ) ) ) {
          Assertions.fail( "Dealer didnt stop at blackjack" );
        }
      }
    } );

    // when game is run
    game.run();
  }
}
