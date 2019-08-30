package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.interfaces.GameEventHandler;
import com.kaznowski.blackjackml.interfaces.GameEventHandlerCollection;
import com.kaznowski.blackjackml.interfaces.GameEventHandlerMulticast;
import com.kaznowski.blackjackml.interfaces.ShuffleMechanism;

import java.util.Arrays;
import java.util.List;

import static com.kaznowski.blackjackml.simulation.PlayerChoice.DOUBLE;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.HITME;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.STAY;

public class Game implements Runnable, GameEventHandlerCollection {
  private static final int BLACKJACK = 21;
  private static final int DEALER_LOW = 17;

  private final Deck deck;
  private final Dealer dealer;
  private final List<Player> players;
  private final GameEventHandlerMulticast gameEventHandlers;

  public Game( ShuffleMechanism shuffleMechanism, Player... players ) {
    dealer = new Dealer("Dealer");
    this.players = Arrays.asList( players );
    deck = new Deck(shuffleMechanism);
    gameEventHandlers = new GameEventHandlerMulticast();
  }

  @Override
  public void run() {
    deck.shuffle( );
    gameEventHandlers.shuffledDeck( deck );
    dealCardsToEveryone();
    for ( Player player : players ) {
      simulatePlayer( player );
    }
    simulateDealer();
    calculateScores();
    gameEventHandlers.gameEnded();
  }

  private void calculateScores() {
    int dealerScore = dealer.getScore( BLACKJACK );
    gameEventHandlers.playerScore( dealer, dealerScore );
    for ( Player player : players ) {
      int playerScore = player.getScore( BLACKJACK );
      gameEventHandlers.playerScore( player, playerScore );
      // TODO logic if disqualified etc
      if ( playerScore > dealerScore ) {
        gameEventHandlers.playerWon( player ); // TODO Scores?
      }
      else if ( playerScore == dealerScore ) {
        gameEventHandlers.playerDrew( player );
      }
      else {
        gameEventHandlers.playerLost( player );
      }
    }
  }

  private void simulatePlayer( Player player ) {
    gameEventHandlers.startedTurnPlayer( player );
    PlayerChoice choice = player.giveChoices( STAY, HITME, DOUBLE );
    gameEventHandlers.playerChose( player, choice );
    if ( choice == STAY ) {
      return;
    }
    if ( choice == DOUBLE ) {
      Card card = deck.pullCard();
      player.deal( card );
      gameEventHandlers.playerDoubles( player, card );
    }
    else if ( choice == HITME ) {
      Card card = deck.pullCard();
      player.deal( card );
      gameEventHandlers.dealtCardToPlayer( player, card );
    }
    else { // TODO game event handler for failure?
      throw new IllegalStateException( "Unknown state " + choice );
    }
    // TODO game event handler for blackjack?
    while ( player.getHand().getLowestTotal() <= BLACKJACK ) { // and they havent stopped
      choice = player.giveChoices( STAY, HITME );
      gameEventHandlers.playerChose( player, choice );
      if ( choice == STAY ) {
        return;
      }
      if ( choice == HITME ) {
        Card card = deck.pullCard();
        player.deal( card );
        gameEventHandlers.dealtCardToPlayer( player, card );
      }
      else {
        throw new IllegalStateException( "Unknown state " + choice );
      }
    }
  }

  private void simulateDealer() {
    // TODO game event handler reveal dealer face down card
    while ( dealer.isBelowRuleMinimum( DEALER_LOW, BLACKJACK ) ) {
      Card card = deck.pullCard();
      dealer.deal( card );
      gameEventHandlers.dealtCardToDealer( dealer, card );
    }
  }

  private void dealCardsToEveryone() {
    roundRobin();
    roundRobin();
  }

  private void roundRobin() {
    for ( Player player : players ) {
      Card card = deck.pullCard();
      player.deal( card );
      gameEventHandlers.dealtCardToPlayer( player, card );
    }
    Card card = deck.pullCard();
    dealer.deal( card );
    gameEventHandlers.dealtCardToDealer( dealer, card );
  }

  @Override
  public void addGameEventHandler( GameEventHandler gameEventHandler ) {
    gameEventHandlers.addGameEventHandler( gameEventHandler );
  }

  @Override
  public void removeGameEventHandler( GameEventHandler gameEventHandler ) {
    gameEventHandlers.addGameEventHandler( gameEventHandler );
  }
}
