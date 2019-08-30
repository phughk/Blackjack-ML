package com.kaznowski.blackjackml;

import com.kaznowski.blackjackml.helpers.ArtificialPlayerChoices;
import com.kaznowski.blackjackml.interfaces.JavaShuffleMechanism;
import com.kaznowski.blackjackml.simulation.Game;
import com.kaznowski.blackjackml.simulation.Player;
import com.kaznowski.blackjackml.interfaces.ConsoleObserver;

public class Main {

  public static void main( String[] args ) {
    Game game = new Game( new JavaShuffleMechanism( 123 ), new Player( "Red", new ArtificialPlayerChoices.Random() ),
        new Player( "Blue", new ArtificialPlayerChoices.Random() ) );
    game.addGameEventHandler( new ConsoleObserver( System.out ) );
    game.run();
  }
}
