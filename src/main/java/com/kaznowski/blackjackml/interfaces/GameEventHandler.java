package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.simulation.Dealer;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.simulation.PlayerChoice;

public interface GameEventHandler {
  void shuffledDeck( Deck deck );

  void dealtCardToPlayer( Player player, Card card );

  void dealtCardToDealer( Dealer dealer, Card card );

  void startedTurnPlayer( Player player );

  void playerChose( Player player, PlayerChoice choice );

  void playerScore( Player player, int playerScore );

  void playerDoubles( Player player, Card card );

  void playerWon(Player player );

  void playerDrew( Player player );

  void playerLost( Player player );

  void gameEnded();
}
