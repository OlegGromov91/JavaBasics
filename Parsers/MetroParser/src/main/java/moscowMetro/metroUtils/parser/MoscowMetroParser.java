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
  private static final String DELIMITER_STATION = "/";


  public MoscowMetroParser(Document document) {
    this.document = document;
  }

  public Elements getElementsByQuery(String cssQuery) {
    return document.select(cssQuery);
  }

  @Override
  public List<String> getLineName(String cssQueryContent) {
    return getElementsByQuery(cssQueryContent).stream().map(Element::text)
        .filter(name -> !name.isEmpty())
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getLineNumber(String cssQueryLineContent, String attributeNumber) {
    return getElementsByQuery(cssQueryLineContent).stream()
        .map(element -> element.attr(attributeNumber))
        .filter(number -> !number.isEmpty())
        .collect(Collectors.toList());
  }


  @Override
  public List<String[]> getStationsNames(String cssQueryStationContent) {
    return getStationPart("[\\w.]", cssQueryStationContent);
  }

  @Override
  public List<String[]> getStationsNumber(String cssQueryStationContent) {
    return getStationPart("[\\D.]", cssQueryStationContent);
  }


  /**
   * @param connections_query @see CssQuery.QUERY_CONNECTIONS.getIdentifier()
   * @return build map where key is "lineNumber:station" and value is array of "lineNumbers:stationConnections"
   */
  @Override
  public Map<String, String []> getConnections(String connections_query) {

    Map<String, String []> connections = new LinkedHashMap<>();
    List<String> parseConnectionsResult = parseConnections(connections_query);

    for (String value : parseConnectionsResult) {
      String line = value.split("!")[0];
      String[] connectionsArray = value
          .substring(value.indexOf("!")).split("<span");
      String [] connectionsHtmlFormat = Arrays.stream(connectionsArray).filter(s -> s.length() > 1)
          .map(this::getConnectionContent)
          .toArray(String[]::new);
      connections.put(line, connectionsHtmlFormat);

    }
    return connections;
  }


  /**
   * REGEX_NUM_NAME_STATIONS "\d+.\s[аА-яЯ]+" - stations content should look like: "9. имя станции",
   * if not take this as a separator delimiterStations usually its "Подробно о линии"
   * @return String as array, with number and station name splited by @delimiterStations
   */
  private String[] stationsContent(String cssQuery) {
    StringBuilder sb = new StringBuilder();
    List<String> elements = getElementsByQuery(cssQuery).stream().map(Element::text)
        .collect(Collectors.toList());
    String REGEX_NUM_NAME_STATIONS = "\\d+.\\s[аА-яЯ]+";
    String delimiterStations = elements.stream()
        .filter(string -> !string.matches(REGEX_NUM_NAME_STATIONS))
        .limit(1)
        .collect(Collectors.joining())
        .trim();
    elements.forEach(i -> sb.append(i).append(DELIMITER_STATION));
    return Arrays.stream(sb.toString().split(delimiterStations))
        .filter(filterEmptyContent -> filterEmptyContent.length() > 1).toArray(String[]::new);
  }

  /**
   * @param regexp   on station name or station number
   * @param cssQuery CssQuery.QUERY_STATION_CONTENT
   * @return station names or numbers depending on regexp
   */
  private List<String[]> getStationPart(String regexp, String cssQuery) {
    String[] stationsContent = stationsContent(cssQuery);
    List<String[]> stationsNames = new ArrayList<>();
    for (String stationsNamesAndNumber : stationsContent) {
      String[] names = Arrays.stream(stationsNamesAndNumber.split(DELIMITER_STATION))
          .map(content -> content.replaceAll(regexp, "").trim())
          .filter(content -> !content.isEmpty())
          .toArray(String[]::new);
      stationsNames.add(names);
    }
    return stationsNames;
  }


  /**
   * @param connections_query @see CssQuery.QUERY_CONNECTIONS.getIdentifier()
   * @return raw connections list with line number + station name + connections in raw view @see
   * getConnectionContent(String content) @param content
   */
  private List<String> parseConnections(String connections_query) {
    Elements connections = getElementsByQuery(connections_query);
    List<String> parseConnectionsResult = new ArrayList<>();

    for (Element connection : connections) {
      Elements connectionElements = connection.select("p");
      for (Element stationWithConnections : connectionElements) {
        if (stationWithConnections.getElementsByAttribute("title").size() != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(connection.attr("data-depend-set").replaceAll("lines-", ""))
              .append(":")
              .append(stationWithConnections.select("span.name").text())
              .append("!")
              .append(stationWithConnections.getElementsByAttribute("title"));
          parseConnectionsResult.add(stringBuilder.toString());
        }
      }
    }
    return parseConnectionsResult;
  }

  /**
   * @param content is string in raw view "<span class=\"t-icon-metroln ln-5\" title=\"переход на
   *                станцию «Комсомольская» Кольцевой линии\"></span>" method get number, and line
   *                name, and
   * String NUMBER_REGEX - remove ln- and " from a number that looks like ln-5"
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
    sb.append(number).append(":").append(name);
    return sb.toString();
  }


}
