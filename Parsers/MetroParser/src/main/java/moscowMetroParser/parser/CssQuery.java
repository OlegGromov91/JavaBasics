package moscowMetroParser.parser;

public enum CssQuery {
  MAIN_QUERY_WITH_METRO_CONTENT(""),
  QUERY_LINE_CONTENT("span.js-metro-line"),
  ATTRIBUTE_LINE_NUMBER("data-line"),
  QUERY_STATION_CONTENT("div.js-depend p");

  public final String identifier;

  CssQuery(String identifier) {

    this.identifier = identifier;
  }

}
