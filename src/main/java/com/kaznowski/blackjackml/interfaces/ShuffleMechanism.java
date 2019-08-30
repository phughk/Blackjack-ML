package com.kaznowski.blackjackml.interfaces;

import com.kaznowski.blackjackml.domain.Card;

import java.util.List;

public interface ShuffleMechanism {
  void shuffle( List<Card> list );
}
