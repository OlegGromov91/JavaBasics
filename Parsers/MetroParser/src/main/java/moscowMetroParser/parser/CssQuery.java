package moscowMetroParser.parser;

public enum CssQuery {
  QUERY_LINE_NAME("span.js-metro-line"),
  QUERY_LINE_NUMBER("span.js-metro-line"),
  QUERY_STATION_NAME("div.js-depend p"),
  QUERY_STATION_NUMBER("");

  public final String identifier;

  CssQuery(String identifier) {

    this.identifier = identifier;
  }

}
