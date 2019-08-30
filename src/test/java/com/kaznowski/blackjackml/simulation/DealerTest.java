package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.CardValue;
import org.junit.jupiter.api.Test;

import static com.kaznowski.blackjackml.helpers.TestHelpers.card;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {
  private static final int MIN = 17;
  private static final int MAX = 21;

  @Test
  void onBorderMeansTheyStopDealing() {
    // given a hand that is exactly at the point when the dealer should stop
    Dealer dealer = new Dealer();
    dealer.deal( card( CardValue.SEVEN ) );
    dealer.deal( card( CardValue.KING ) );

    // then dealer would stop drawing cards
    assertFalse( dealer.isBelowRuleMinimum( MIN, MAX ) );
  }

  @Test
  void belowBoundMeansHitMe() {
    // given we have a hand that is below the dealer minimum
    Dealer dealer = new Dealer();
    dealer.deal( card( CardValue.FOUR ) );
    dealer.deal( card( CardValue.JACK ) );

    // then we would draw another card
    assertTrue( dealer.isBelowRuleMinimum( MIN, MAX ) );
  }

  @Test
  void acesAreMaximised() {
    // given we have a hand that when ace is treated as 11, totals 17
    Dealer dealer = new Dealer();
    dealer.deal( card( CardValue.ACE ) );
    dealer.deal( card( CardValue.SIX ) );

    // then we would stop drawing
    assertFalse( dealer.isBelowRuleMinimum( MIN, MAX ) );
  }

  @Test
  void acesAreMinimisedIfOverBlackjack() { // TODO parameterise and maybe do test for 2x ace + fives...
    // given we have a hand that when maximised would lose, so we consider the worse option
    Dealer dealer = new Dealer();
    dealer.deal( card( CardValue.ACE ) );
    dealer.deal( card( CardValue.ACE ) );

    // then we are below the legal minimum and can still draw
    assertTrue( dealer.isBelowRuleMinimum( MIN, MAX ) );
  }
}
