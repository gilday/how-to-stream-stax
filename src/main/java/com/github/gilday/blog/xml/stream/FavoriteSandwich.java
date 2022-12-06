package com.github.gilday.blog.xml.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
   * @param is stream containing favorite sandwich XML data to parse
   * @return new {@link Stream} of {@link FavoriteSandwich} records parsed from the XML
   */
  public static Stream<FavoriteSandwich> fromXML(final InputStream is) throws IOException {
    throw new UnsupportedEncodingException("Not Yet Supported");
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * @throws NullPointerException when any parameter is {@code null}.
   */
  public FavoriteSandwich {
    Objects.requireNonNull(name);
    Objects.requireNonNull(restaurant);
    Objects.requireNonNull(description);
  }

  public static final class Builder {

    private String name;
    private Restaurant restaurant;
    private String description;

    public Builder name(final String value) {
      this.name = value;
      return this;
    }

    public Builder restaurant(final Restaurant restaurant) {
      this.restaurant = restaurant;
      return this;
    }

    public Builder description(final String description) {
      this.description = description;
      return this;
    }

    public FavoriteSandwich build() {
      return new FavoriteSandwich(name, restaurant, description);
    }
  }
}
