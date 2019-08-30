package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Deck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.kaznowski.blackjackml.simulation.PlayerChoice.DOUBLE;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.HITME;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.STAY;

public class Game implements Runnable {
  private static final int BLACKJACK = 21;
  private static final int DEALER_LOW = 17;

  private final Deck deck;
  private final Dealer dealer;
  private final List<Player> players;
  private final Set<Player> doubledPlayers;

  public Game( int numberPlayers ) {
    dealer = new Dealer();
    doubledPlayers = new HashSet<>();
    players = new ArrayList<>();
    for ( int i = 0; i < numberPlayers; i++ ) {
      players.add( new Player() );
    }
    deck = new Deck();
  }

  @Override
  public void run() {
    deck.shuffle();
    dealCardsToEveryone();
    for ( Player player : players ) {
      simulatePlayer( player );
    }
    simulateDealer();
    for ( Player player : players ) {
      calculateScore( player );
    }
  }

  private void calculateScore( Player player ) {
    throw new UnsupportedOperationException();
  }

  private void simulatePlayer( Player player ) {
    PlayerChoice choice = player.giveChoices( STAY, HITME, DOUBLE );
    if ( choice == STAY ) {
      return;
    }
    if ( choice == DOUBLE ) {
      doubledPlayers.add( player );
    }
    else if ( choice == HITME ) {
      player.deal( deck.pullCard() );
    }
    else {
      throw new IllegalStateException( "Unknown state " + choice );
    }
    while ( player.getHand().getLowestTotal() <= BLACKJACK ) { // and they havent stopped
      choice = player.giveChoices( STAY, HITME );
      if ( choice == STAY ) {
        return;
      }
      if ( choice == HITME ) {
        player.deal( deck.pullCard() );
      }
      else {
        throw new IllegalStateException( "Unknown state " + choice );
      }
    }
  }

  private void simulateDealer() {
    while ( dealer.isBelowRuleMinimum( DEALER_LOW, BLACKJACK ) ) {
      dealer.deal( deck.pullCard() );
    }
  }

  private void dealCardsToEveryone() {
    roundRobin();
    roundRobin();
  }

  private void roundRobin() {
    for ( Player player : players ) {
      player.deal( deck.pullCard() );
    }
    dealer.deal( deck.pullCard() );
  }
}
