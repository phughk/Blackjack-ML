package com.kaznowski.blackjackml.helpers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CombinatoricsTest {
  @Test
  void allCombinationsSimple() {
    List<List<Integer>> input = asList( asList( 1, 2 ), asList( 3, 4 ), asList( 5, 6 ) );

    //when
    List<List<Integer>> output = Combinatorics.oneFromEach( input );

    // then
    assertEquals( asList( asList( 1, 3, 5 ), asList( 1, 3, 6 ), asList( 1, 4, 5 ), asList( 1, 4, 6 ), asList( 2, 3, 5 ),
        asList( 2, 3, 6 ), asList( 2, 4, 5 ), asList( 2, 4, 6 ) ), output );
  }

  @Test
  void withVariableLength() {
    List<Integer> nullList = new ArrayList<>(  );
    nullList.add( null );
    List<List<Integer>> input = asList( asList( 1, 2, 3 ), nullList, asList( 5, 6 ) );

    //when
    List<List<Integer>> output = Combinatorics.oneFromEach( input );

    // then
    assertEquals( asList( asList( 1, null, 5 ), asList( 1, null, 6 ), asList( 2, null, 5 ), asList( 2, null, 6 ),
        asList( 3, null, 5 ), asList( 3, null, 6 ) ), output );
  }
}
