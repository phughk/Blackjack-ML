package com.kaznowski.blackjackml;

import com.kaznowski.blackjackml.simulation.Game;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.interfaces.ConsoleObserver;

public class Main {

  public static void main( String[] args ) {
    Game game = new Game( 123, new Player( "Red" ), new Player( "Blue" ) );
    game.addGameEventHandler( new ConsoleObserver( System.out ) );
    game.run();
  }
}
