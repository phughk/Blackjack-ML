package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;
import com.kaznowski.blackjackml.domain.Deck;
import com.kaznowski.blackjackml.simulation.Dealer;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.simulation.PlayerChoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEventHandlerMulticast implements GameEventHandler, GameEventHandlerCollection {
  private final List<GameEventHandler> gameEventHandlers = new ArrayList<>();

  public GameEventHandlerMulticast( GameEventHandler... handlers ) {
    gameEventHandlers.addAll( Arrays.asList( handlers ) );
  }

  public void addGameEventHandler( GameEventHandler gameEventHandler ) {
    gameEventHandlers.add( gameEventHandler );
  }

  public void removeGameEventHandler( GameEventHandler gameEventHandler ) {
    gameEventHandlers.remove( gameEventHandler );
  }

  @Override
  public void shuffledDeck( Deck deck ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.shuffledDeck( deck );
    }
  }

  @Override
  public void dealtCardToPlayer( Player player, Card card ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.dealtCardToPlayer( player, card );
    }
  }

  @Override
  public void dealtCardToDealer( Dealer dealer, Card card ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.dealtCardToDealer( dealer, card );
    }
  }

  @Override
  public void startedTurnPlayer( Player player ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.startedTurnPlayer( player );
    }
  }

  @Override
  public void playerChose( Player player, PlayerChoice choice ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerChose( player, choice );
    }
  }

  @Override
  public void playerScore( Player player, int playerScore ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerScore( player, playerScore );
    }
  }

  @Override
  public void playerDoubles( Player player, Card card ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerDoubles( player, card );
    }
  }

  @Override
  public void playerWon( Player player ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerWon( player );
    }
  }

  @Override
  public void playerDrew( Player player ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerDrew( player );
    }
  }

  @Override
  public void playerLost( Player player ) {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.playerLost( player );
    }
  }

  @Override
  public void gameEnded() {
    for ( GameEventHandler gameEventHandler : gameEventHandlers ) {
      gameEventHandler.gameEnded();
    }
  }
}
