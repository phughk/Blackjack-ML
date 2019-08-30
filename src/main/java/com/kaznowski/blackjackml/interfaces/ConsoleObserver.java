package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.simulation.Dealer;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.simulation.PlayerChoice;

import java.io.PrintStream;

public class ConsoleObserver implements GameEventHandler {
  private final PrintStream printStream;
  private final int blackjack;

  public ConsoleObserver( PrintStream printStream, int blackjack ) {
    this.printStream = printStream;
    this.blackjack = blackjack;
  }

  @Override
  public void shuffledDeck( Deck deck ) {
    // TODO can actually see the deck here. Return a immutable, safe-view copy
    printStream.printf( "Deck was shuffled\n" );
  }

  @Override
  public void dealtCardToPlayer( Player player, Card card ) {
    printStream.printf( "Player %s was dealt card %s. Their score is now %d\n", player, card,
        player.getHand().getHighestScore( blackjack ).getAsInt() );
  }

  @Override
  public void dealtCardToDealer( Dealer dealer, Card card ) {
    printStream.printf( "Dealer %s was dealt card %s. Their score is now %d\n", dealer, card,
        dealer.getHand().getHighestScore( blackjack ).getAsInt() );
  }

  @Override
  public void startedTurnPlayer( Player player ) {
    printStream.printf( "Player %s started their turn\n", player );
  }

  @Override
  public void playerChose( Player player, PlayerChoice choice ) {
    printStream.printf( "Player %s chose %s\n", player, choice );
  }

  @Override
  public void playerScore( Player player, int playerScore ) {
    printStream.printf( "Player %s score was %d\n", player, playerScore );
  }

  @Override
  public void playerDoubles( Player player, Card card ) {
    printStream.printf( "Player %s doubled and drew %s\n", player, card );
  }

  @Override
  public void playerWon( Player player ) {
    printStream.printf( "Player %s won\n", player );
  }

  @Override
  public void playerDrew( Player player ) {
    printStream.printf( "Player %s drew against the dealer\n", player );
  }

  @Override
  public void playerLost( Player player ) {
    printStream.printf( "Player %s lost the round\n", player );
  }

  @Override
  public void gameEnded() {
    printStream.printf( "The game has ended\n" );
  }
}
