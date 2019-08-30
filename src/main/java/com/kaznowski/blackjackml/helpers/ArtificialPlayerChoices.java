package com.kaznowski.blackjackml.helpers;

import com.kaznowski.blackjackml.simulation.PlayerChoice;

import java.util.List;
import java.util.function.Function;

public class ArtificialPlayerChoices {
  public static class Random implements Function<List<PlayerChoice>,PlayerChoice> {

    @Override
    public PlayerChoice apply( List<PlayerChoice> playerChoice ) {
      int selection = (int) (Math.random() * playerChoice.size());
      return playerChoice.get( selection );
    }
  }

  public static class AlwaysHit implements Function<List<PlayerChoice>,PlayerChoice> {

    @Override
    public PlayerChoice apply( List<PlayerChoice> playerChoices ) {
      return PlayerChoice.HITME;
    }
  }

  public static class Throwing implements Function<List<PlayerChoice>,PlayerChoice> {

    @Override
    public PlayerChoice apply( List<PlayerChoice> playerChoices ) {
      throw new UnsupportedOperationException( "This has deliberately been chosen not to be interacted with" );
    }
  }
}
