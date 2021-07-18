package moscowMetroParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MoscowMetroParser implements Parser {

  private final Document document;
  private static final String DELIMITER = "/";


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
   * REGEX_NUM_NAME_STATIONS "\d+.\s[аА-яЯ]+" - stations content should look like: "9. имя станции", if not take this
   * as a separator
   * delimiterStations usually its "Подробно о линии"
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
    elements.forEach(i -> sb.append(i).append(DELIMITER));
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
      String[] names = Arrays.stream(stationsNamesAndNumber.split(DELIMITER))
          .map(content -> content.replaceAll(regexp, "").trim())
          .filter(content -> !content.isEmpty())
          .toArray(String[]::new);
      stationsNames.add(names);
    }
    return stationsNames;
  }


}
