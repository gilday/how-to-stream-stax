package com.github.gilday.blog.xml.stream;

import javax.xml.stream.XMLStreamException;

/**
 * Unchecked exception that wraps an {@link XMLStreamException}. Used for building a {@link
 * java.util.stream.Stream} from data parsed using the {@link javax.xml.stream} API.
 */
public class UncheckedXMLStreamException extends RuntimeException {

  public UncheckedXMLStreamException(final XMLStreamException inner) {
    super(inner);
  }

  public UncheckedXMLStreamException(final String message, final XMLStreamException inner) {
    super(message, inner);
  }
}
