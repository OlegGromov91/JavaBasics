package moscowMetro.metroUtils.parser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MoscowMetroParser implements Parser {

  private final Document document;
  private static final String DELIMITER_COLON = ":";
  private static final String DELIMITER_EXCLAMATION = "!";


  public MoscowMetroParser(Document document) {
    this.document = document;
  }

  public Elements getElementsByQuery(String cssQuery) {
    return document.select(cssQuery);
  }

  /**
   * @param QUERY_LINE_CONTENT @see CssQuery.QUERY_LINE_CONTENT
   * @return list with line names
   */
  @Override
  public List<String> getLineName(String QUERY_LINE_CONTENT) {
    return getElementsByQuery(QUERY_LINE_CONTENT).stream().map(Element::text)
        .filter(name -> !name.isEmpty())
        .collect(Collectors.toList());
  }

  /**
   * @param QUERY_LINE_CONTENT    @see CssQuery.QUERY_LINE_CONTENT
   * @param ATTRIBUTE_LINE_NUMBER @see CssQuery.ATTRIBUTE_LINE_NUMBER
   * @return list with line names
   */
  @Override
  public List<String> getLineNumber(String QUERY_LINE_CONTENT, String ATTRIBUTE_LINE_NUMBER) {
    return getElementsByQuery(QUERY_LINE_CONTENT).stream()
        .map(element -> element.attr(ATTRIBUTE_LINE_NUMBER))
        .filter(number -> !number.isEmpty())
        .collect(Collectors.toList());
  }

  /**
   * @param QUERY_STATION_CONTENT @see CssQuery.QUERY_STATION_CONTENT
   * @return list of arrays with station names
   */
  @Override
  public List<String[]> getStationsNames(String QUERY_STATION_CONTENT) {
    return getStationPart("[\\w.]", QUERY_STATION_CONTENT);
  }

  /**
   * @param QUERY_STATION_CONTENT @see CssQuery.QUERY_STATION_CONTENT
   * @return list of arrays with station numbers
   */
  @Override
  public List<String[]> getStationsNumber(String QUERY_STATION_CONTENT) {
    return getStationPart("[\\D.]", QUERY_STATION_CONTENT);
  }


  /**
   * @param QUERY_CONNECTIONS @see CssQuery.QUERY_CONNECTIONS.getIdentifier()
   * @return build map where key is major "lineNumber:station" and value is array of minor
   * "lineNumbers:stationConnections"
   */
  @Override
  public Map<String, String[]> getConnections(String QUERY_CONNECTIONS) {

    Map<String, String[]> connections = new LinkedHashMap<>();
    List<String> parseConnectionsResult = parseConnections(QUERY_CONNECTIONS);

    for (String value : parseConnectionsResult) {
      String line = value.split(DELIMITER_EXCLAMATION)[0];
      String[] connectionsArray = value
          .substring(value.indexOf(DELIMITER_EXCLAMATION)).split("<span");
      String[] connectionsHtmlFormat = Arrays.stream(connectionsArray).filter(s -> s.length() > 1)
          .map(this::getConnectionContent)
          .toArray(String[]::new);
      connections.put(line, connectionsHtmlFormat);

    }
    return connections;
  }

  /**
   * @param regexp                on station name or station number
   * @param QUERY_STATION_CONTENT CssQuery.QUERY_STATION_CONTENT
   * @return station names or numbers depending on regexp
   */
  private List<String[]> getStationPart(String regexp, String QUERY_STATION_CONTENT) {
    String[] stationsContent = stationsContent(QUERY_STATION_CONTENT);
    List<String[]> stationsNames = new ArrayList<>();
    for (String stationsNamesAndNumber : stationsContent) {
      String[] names = Arrays.stream(stationsNamesAndNumber.split(DELIMITER_COLON))
          .map(content -> content.replaceAll(regexp, "").trim())
          .filter(content -> !content.isEmpty())
          .toArray(String[]::new);
      stationsNames.add(names);
    }
    return stationsNames;
  }

  /**
   * REGEX_NUM_NAME_STATIONS "\d+.\s[аА-яЯ]+" - stations content should look like: "9. имя станции",
   * if not take this as a separator delimiterStations usually its "Подробно о линии"
   *
   * @return String as array, with number and station name splited by @delimiterStations
   */
  private String[] stationsContent(String QUERY_STATION_CONTENT) {
    StringBuilder sb = new StringBuilder();
    List<String> elements = getElementsByQuery(QUERY_STATION_CONTENT).stream().map(Element::text)
        .collect(Collectors.toList());
    String REGEX_NUM_NAME_STATIONS = "\\d+.\\s[аА-яЯ]+";
    String delimiterStations = elements.stream()
        .filter(string -> !string.matches(REGEX_NUM_NAME_STATIONS))
        .limit(1)
        .collect(Collectors.joining())
        .trim();
    elements.forEach(i -> sb.append(i).append(DELIMITER_COLON));
    return Arrays.stream(sb.toString().split(delimiterStations))
        .filter(filterEmptyContent -> filterEmptyContent.length() > 1).toArray(String[]::new);
  }

  /**
   * @param QUERY_CONNECTIONS @see CssQuery.QUERY_CONNECTIONS.getIdentifier()
   * @return raw connections list with line number + station name + connections in raw view @see
   * getConnectionContent(String content) @param content
   */
  private List<String> parseConnections(String QUERY_CONNECTIONS) {
    Elements connections = getElementsByQuery(QUERY_CONNECTIONS);
    List<String> parseConnectionsResult = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    for (Element connection : connections) {
      Elements connectionElements = connection.select("p");
      for (Element stationWithConnections : connectionElements) {
        if (stationWithConnections.getElementsByAttribute("title").size() != 0) {
          stringBuilder.append(connection.attr("data-depend-set").replaceAll("lines-", ""))
              .append(DELIMITER_COLON)
              .append(stationWithConnections.select("span.name").text())
              .append(DELIMITER_EXCLAMATION)
              .append(stationWithConnections.getElementsByAttribute("title"));
          parseConnectionsResult.add(stringBuilder.toString());
          stringBuilder.delete(0, Integer.MAX_VALUE);
        }
      }
    }
    return parseConnectionsResult;
  }

  /**
   * @param content is string in raw view "<span class=\"t-icon-metroln ln-5\" title=\"переход на
   *                станцию «Комсомольская» Кольцевой линии\"></span>" method get number, and line
   *                name, and String NUMBER_REGEX - remove ln- and " from a number that looks like
   *                ln-5"
   * @return string like "5:Комсомольская"  (example lineNumber and lineName)
   */
  private String getConnectionContent(String content) {
    StringBuilder sb = new StringBuilder();
    String NUMBER_REGEX = "(ln-|\")";
    String number = Arrays.stream(content.split(" "))
        .filter(i -> i.contains("ln-"))
        .map(str -> str.replaceAll(NUMBER_REGEX, ""))
        .collect(Collectors.joining());
    String name = content.substring(content.indexOf("«") + 1, content.indexOf("»"));
    sb.append(number).append(DELIMITER_COLON).append(name);
    return sb.toString();
  }


}
