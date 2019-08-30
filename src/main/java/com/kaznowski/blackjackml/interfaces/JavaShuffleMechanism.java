package com.kaznowski.blackjackml.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JavaShuffleMechanism implements ShuffleMechanism {
  private final long seed;

  public JavaShuffleMechanism( long seed ) {
    this.seed = seed;
  }

  @Override
  public void shuffle( List<?> list ) {
    Collections.shuffle( list, new Random( seed ) );
  }

}
