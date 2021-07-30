package moscowMetro.metroUtils.parser;

public enum CssQuery {

  QUERY_LINE_CONTENT("span.js-metro-line"),
  QUERY_CONNECTIONS("div.js-depend"),
  ATTRIBUTE_LINE_NUMBER("data-line"),
  QUERY_STATION_CONTENT("div.js-depend p");


  private final String identifier;

  CssQuery(String identifier) {

    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }
}
