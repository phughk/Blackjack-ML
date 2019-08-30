package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;

import java.util.List;

public class NoShuffleMechanism implements ShuffleMechanism {
  @Override
  public void shuffle( List<Card> list ) {
    // Do nothing
  }
}
