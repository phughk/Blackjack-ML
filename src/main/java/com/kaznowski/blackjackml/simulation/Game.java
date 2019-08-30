package com.kaznowski.blackjackml.simulation;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.interfaces.GameEventHandler;
import com.kaznowski.blackjackml.interfaces.GameEventHandlerCollection;
import com.kaznowski.blackjackml.interfaces.GameEventHandlerMulticast;
import com.kaznowski.blackjackml.interfaces.ShuffleMechanism;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import static com.kaznowski.blackjackml.simulation.PlayerChoice.DOUBLE;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.HITME;
import static com.kaznowski.blackjackml.simulation.PlayerChoice.STAY;

public class Game implements Runnable, GameEventHandlerCollection {
  public static final int BLACKJACK = 21;
  public static final int DEALER_LOW = 17;

  private final Deck deck;
  private final Dealer dealer;
  private final List<Player> players;
  private final GameEventHandlerMulticast gameEventHandlers;

  private final int dealerLow;
  private final int blackjack;

  public Game( ShuffleMechanism shuffleMechanism, Player... players ) {
    this( DEALER_LOW, BLACKJACK, shuffleMechanism, players );
  }

  Game( int dealerLow, int blackjack, ShuffleMechanism shuffleMechanism, Player... players ) {
    dealer = new Dealer( "Dealer" );
    this.players = Arrays.asList( players );
    deck = new Deck( shuffleMechanism );
    gameEventHandlers = new GameEventHandlerMulticast();
    this.blackjack = blackjack;
    this.dealerLow = dealerLow;
  }

  @Override
  public void run() {
    deck.shuffle();
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
    OptionalInt dealerScore = dealer.getHand().getHighestScore( blackjack );
    gameEventHandlers.playerScore( dealer, dealerScore.getAsInt() );
    for ( Player player : players ) {
      OptionalInt playerScore = player.getHand().getHighestScore( blackjack );
      gameEventHandlers.playerScore( player, playerScore.getAsInt() );
      // TODO logic if disqualified etc
      if ( playerScore.getAsInt() > dealerScore.getAsInt() ) {
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
    while ( player.getHand().getLowestScore() <= blackjack) { // and they havent stopped
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
    while ( dealer.shouldPickUpCard( dealerLow, blackjack) ) {
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
