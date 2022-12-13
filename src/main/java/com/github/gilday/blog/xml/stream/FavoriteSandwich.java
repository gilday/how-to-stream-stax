package com.github.gilday.blog.xml.stream;

import com.google.auto.value.AutoBuilder;
import java.util.Objects;

/**
 * @param name sandwich name
 * @param restaurant restaurant that makes the sandwich
 * @param description description of the sandwich
 */
public record FavoriteSandwich(String name, Restaurant restaurant, String description) {

  /**
   * @return new {@link Builder}
   */
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
