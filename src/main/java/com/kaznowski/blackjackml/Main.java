package com.kaznowski.blackjackml;

import com.kaznowski.blackjackml.interfaces.JavaShuffleMechanism;
import com.kaznowski.blackjackml.simulation.Game;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.interfaces.ConsoleObserver;

public class Main {

  public static void main( String[] args ) {
    int blackjack = 21;
    Game game = new Game( new JavaShuffleMechanism( 123 ), new Player( "Red" ), new Player( "Blue" ) );
    game.addGameEventHandler( new ConsoleObserver( System.out, blackjack ) );
    game.run();
  }
}
