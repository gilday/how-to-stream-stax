package com.github.gilday.blog.xml.stream;

import com.google.auto.value.AutoBuilder;
import java.util.Objects;

/**
 * Name and address of a restaurant.
 *
 * @param name restaurant name
 * @param street street address for the restaurant
 * @param city where the restaurant is located
 * @param state where the restaurant is located
 * @param zip where the restaurant is located
 */
public record Restaurant(String name, String street, String city, String state, String zip) {

  /**
   * @return new {@link Builder}
   */
  public static Builder builder() {
    return new AutoBuilder_Restaurant_Builder();
  }

  /**
   * @throws NullPointerException when any parameter is {@code null}.
   */
  public Restaurant {
    Objects.requireNonNull(name);
    Objects.requireNonNull(street);
    Objects.requireNonNull(city);
    Objects.requireNonNull(state);
    Objects.requireNonNull(zip);
  }

  /** Builder companion class for {@link Restaurant}. */
  @AutoBuilder(ofClass = Restaurant.class)
  public abstract static class Builder {

    /**
     * Sets the restaurant name.
     *
     * @param name value to set
     * @return this
     */
    public abstract Builder name(String name);

    /**
     * Sets the restaurant street address.
     *
     * @param street value to set
     * @return this
     */
    public abstract Builder street(String street);

    /**
     * Sets the city in which the restaurant is located.
     *
     * @param city value to set
     * @return this
     */
    public abstract Builder city(String city);

    /**
     * Sets the state in which the restaurant is located.
     *
     * @param state value to set
     * @return this
     */
    public abstract Builder state(String state);

    /**
     * Sets the zip code for the address where the restaurant is located.
     *
     * @param zip value to set
     * @return this
     */
    public abstract Builder zip(String zip);

    /**
     * Uses the values set thus far to build a new {@link FavoriteSandwich}.
     *
     * @return new {@link FavoriteSandwich}
     */
    public abstract Restaurant build();
  }
}
