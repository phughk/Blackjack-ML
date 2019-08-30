package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.simulation.Dealer;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.simulation.PlayerChoice;

public class GameEventHandlerAdapter implements GameEventHandler {
  @Override
  public void shuffledDeck( Deck deck ) {
  }

  @Override
  public void dealtCardToPlayer( Player player, Card card ) {
  }

  @Override
  public void dealtCardToDealer( Dealer dealer, Card card ) {
  }

  @Override
  public void startedTurnPlayer( Player player ) {
  }

  @Override
  public void playerChose( Player player, PlayerChoice choice ) {
  }

  @Override
  public void playerScore( Player player, int playerScore ) {
  }

  @Override
  public void playerDoubles( Player player, Card card ) {
  }

  @Override
  public void playerWon( Player player ) {
  }

  @Override
  public void playerDrew( Player player ) {
  }

  @Override
  public void playerLost( Player player ) {
  }

  @Override
  public void gameEnded() {
  }
}
