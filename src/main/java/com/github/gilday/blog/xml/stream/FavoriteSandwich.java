package com.github.gilday.blog.xml.stream;

import java.io.InputStream;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @param name sandwich name
 * @param restaurant restaurant that makes the sandwich
 * @param description description of the sandwich
 */
public record FavoriteSandwich(String name, Restaurant restaurant, String description) {

  /**
   * Streams {@link FavoriteSandwich} records from an XML file where the root element is {@code
   * favorite-sandwiches} and contains a list of {@code favorite-sandwich} element. Parses the XML
   * stream-wise, so the returned stream may be arbitrarily large.
   *
   * @param is input stream containing the XML data
   * @return new {@link Stream} of {@link FavoriteSandwich} records parsed from the XML
   */
  public static Stream<FavoriteSandwich> fromXML(InputStream is) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  /**
   * @throws NullPointerException when any parameter is {@code null}.
   */
  public FavoriteSandwich {
    Objects.requireNonNull(name);
    Objects.requireNonNull(restaurant);
    Objects.requireNonNull(description);
  }
}
