package outputWriter;

import application.Settings;
import commons.Format;

public class ContentWriterFactory  {

  public static IContentWriter getContentWriter(Format format) {

    switch (format) {
      case JSON:
        return new JsonWriter();
      case XML:
         return new XmlWriter();
      default:
        throw new IllegalArgumentException(Settings.ERROR_WRITER_NOT_FOUND);
    }
  }

}
