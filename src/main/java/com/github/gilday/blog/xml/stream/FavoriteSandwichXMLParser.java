package com.github.gilday.blog.xml.stream;

import com.github.gilday.blog.xml.stream.FavoriteSandwich.Builder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/** Used to parse {@link FavoriteSandwich} records from XML input. */
public final class FavoriteSandwichXMLParser {

  private final XMLInputFactory factory;

  /**
   * Creates a new parser with a default {@link XMLInputFactory} that does not resolve external
   * entities (to prevent XXE attacks).
   */
  public FavoriteSandwichXMLParser() {
    this(XMLInputFactory.newFactory());
    factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
    factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
  }

  /**
   * Creates a new parser with the given {@link XMLInputFactory}.
   *
   * @param factory the factory to use for reading XML
   */
  public FavoriteSandwichXMLParser(final XMLInputFactory factory) {
    this.factory = Objects.requireNonNull(factory);
  }

  /**
   * Streams {@link FavoriteSandwich} records from an XML file where the root element is {@code
   * favorite-sandwiches} and contains a list of {@code favorite-sandwich} element. Parses the XML
   * stream-wise, so the returned stream may be arbitrarily large.
   *
   * @param is stream containing favorite sandwich XML data to parse
   * @return new {@link Stream} of {@link FavoriteSandwich} records parsed from the XML
   */
  public Stream<FavoriteSandwich> stream(final InputStream is) throws IOException {
    final var spliterator = new FavoriteSandwichXMLSpliterator(is);
    return StreamSupport.stream(spliterator, false).onClose(spliterator::close);
  }

  private final class FavoriteSandwichXMLSpliterator
      implements Spliterator<FavoriteSandwich>, AutoCloseable {

    private final XMLEventReader reader;

    public FavoriteSandwichXMLSpliterator(final InputStream is) throws IOException {
      try {
        reader = factory.createXMLEventReader(is);
      } catch (XMLStreamException e) {
        throw new IOException("Failed to parse favorite sandwiches from XML", e);
      }
    }

    @Override
    public boolean tryAdvance(final Consumer<? super FavoriteSandwich> action) {
      while (reader.hasNext()) {
        final XMLEvent event;
        try {
          event = reader.nextEvent();
        } catch (XMLStreamException e) {
          throw new UncheckedXMLStreamException("Failed to read favorite sandwiches from XML", e);
        }
        if (event.isStartElement()) {
          final StartElement startElement = event.asStartElement();
          if ("favorite-sandwich".equals(startElement.getName().getLocalPart())) {
            final FavoriteSandwich sandwich;
            try {
              sandwich = readSandwich();
            } catch (XMLStreamException e) {
              throw new UncheckedXMLStreamException("Failed to read favorite sandwich from XML", e);
            }
            action.accept(sandwich);
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public Spliterator<FavoriteSandwich> trySplit() {
      return null;
    }

    @Override
    public long estimateSize() {
      return -1;
    }

    @Override
    public int characteristics() {
      return NONNULL | IMMUTABLE | ORDERED;
    }

    @Override
    public void close() {
      try {
        reader.close();
      } catch (XMLStreamException e) {
        throw new UncheckedXMLStreamException("Failed to close XMLEventReader", e);
      }
    }

    private FavoriteSandwich readSandwich() throws XMLStreamException {
      final Builder builder = FavoriteSandwich.builder();
      while (reader.hasNext()) {
        final XMLEvent event = reader.nextEvent();
        if (event.isStartElement()) {
          final StartElement startElement = event.asStartElement();
          switch (startElement.getName().getLocalPart()) {
            case "name" -> builder.name(readElementText());
            case "description" -> builder.description(readElementText());
            case "restaurant" -> {
              final Restaurant restaurant = readRestaurant();
              builder.restaurant(restaurant);
            }
          }
        }
        if (event.isEndElement()) {
          if ("favorite-sandwich".equals(event.asEndElement().getName().getLocalPart())) {
            return builder.build();
          }
        }
      }
      throw new IllegalArgumentException(
          "favorite sandwich XML does not contain expected, required elements");
    }

    private Restaurant readRestaurant() throws XMLStreamException {
      final Restaurant.Builder builder = Restaurant.builder();
      while (reader.hasNext()) {
        final XMLEvent event = reader.nextEvent();
        if (event.isStartElement()) {
          final StartElement startElement = event.asStartElement();
          final String text = readElementText();
          switch (startElement.getName().getLocalPart()) {
            case "name" -> builder.name(text);
            case "street" -> builder.street(text);
            case "city" -> builder.city(text);
            case "state" -> builder.state(text);
            case "zip" -> builder.zip(text);
          }
        }
        if (event.isEndElement()) {
          if ("restaurant".equals(event.asEndElement().getName().getLocalPart())) {
            return builder.build();
          }
        }
      }
      throw new IllegalArgumentException(
          "favorite sandwich XML does not contain expected, required elements");
    }

    private String readElementText() throws XMLStreamException {
      return reader.getElementText().strip();
    }
  }
}
