package com.github.gilday.blog.xml.stream;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link FavoriteSandwich}. */
public final class FavoriteSandwichXMLParserTest {

  @Test
  void stream_all_valid_sandwiches() throws IOException {
    final FavoriteSandwichXMLParser reader = new FavoriteSandwichXMLParser();
    try (var is =
            FavoriteSandwichXMLParserTest.class.getResourceAsStream("/favorite-sandwiches.xml");
        var sandwiches = reader.stream(is)) {
      final Map<String, Long> countByState =
          sandwiches.collect(
              groupingBy(sandwich -> sandwich.restaurant().state(), Collectors.counting()));

      final Map<String, Long> expected = Map.of("NJ", 2L, "MD", 3L);
      assertThat(countByState).containsExactlyInAnyOrderEntriesOf(expected);
    }
  }

  @Test
  void stream_some_valid_sandwiches() throws IOException {
    final FavoriteSandwichXMLParser reader = new FavoriteSandwichXMLParser();
    try (var is =
            FavoriteSandwichXMLParserTest.class.getResourceAsStream("/favorite-sandwiches.xml");
        var sandwiches = reader.stream(is)) {
      final Optional<FavoriteSandwich> first = sandwiches.findFirst();

      final Restaurant taliercios =
          Restaurant.builder()
              .name("Taliercio's Gourmet Deli")
              .street("500 NJ-35")
              .city("Red Bank")
              .state("NJ")
              .zip("07701")
              .build();
      final FavoriteSandwich lucaBrasi =
          new FavoriteSandwich(
              "Luca Brasi",
              taliercios,
              "Chicken cutlet, long hot peppers, burrata, and vodka sauce.");
      assertThat(first).contains(lucaBrasi);
    }
  }
}
