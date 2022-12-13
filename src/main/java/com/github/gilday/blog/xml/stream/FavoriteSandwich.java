package com.github.gilday.blog.xml.stream;

import com.google.auto.value.AutoBuilder;
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
    return new AutoBuilder_FavoriteSandwich_Builder();
  }

  /**
   * @throws NullPointerException when any parameter is {@code null}.
   */
  public FavoriteSandwich {
    Objects.requireNonNull(name);
    Objects.requireNonNull(restaurant);
    Objects.requireNonNull(description);
  }

  @AutoBuilder(ofClass = FavoriteSandwich.class)
  public abstract static class Builder {

    public abstract Builder name(String value);

    public abstract Builder restaurant(Restaurant restaurant);

    public abstract Builder description(String description);

    public abstract FavoriteSandwich build();
  }
}
