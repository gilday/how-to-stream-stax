package com.github.gilday.blog.xml.stream;

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
    return new Builder();
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
  public static final class Builder {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;

    /**
     * Sets the restaurant name.
     *
     * @param name value to set
     * @return this
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the restaurant street address.
     *
     * @param street value to set
     * @return this
     */
    public Builder street(String street) {
      this.street = street;
      return this;
    }

    /**
     * Sets the city in which the restaurant is located.
     *
     * @param city value to set
     * @return this
     */
    public Builder city(String city) {
      this.city = city;
      return this;
    }

    /**
     * Sets the state in which the restaurant is located.
     *
     * @param state value to set
     * @return this
     */
    public Builder state(String state) {
      this.state = state;
      return this;
    }

    /**
     * Sets the zip code for the address where the restaurant is located.
     *
     * @param zip value to set
     * @return this
     */
    public Builder zip(String zip) {
      this.zip = zip;
      return this;
    }

    /**
     * Uses the values set thus far to build a new {@link FavoriteSandwich}.
     *
     * @return new {@link FavoriteSandwich}
     */
    public Restaurant build() {
      return new Restaurant(name, street, city, state, zip);
    }
  }
}
