package com.kaznowski.blackjackml.interfaces;

public interface GameEventHandlerCollection {
  void addGameEventHandler( GameEventHandler gameEventHandler );

  void removeGameEventHandler( GameEventHandler gameEventHandler );
}
