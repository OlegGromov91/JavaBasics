package moscowMetroParser.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetroParser implements Parser{


  private final Document document;


  public MetroParser(Document document) {
    this.document = document;
  }

  public Elements getElementsByQuery(String cssQuery) {
    return document.select(cssQuery);
  }

  @Override
  public List<String> getLineName(String cssQuery) {
    return getElementsByQuery(cssQuery).stream().map(Element::text).collect(Collectors.toList());
  }

  /**
   * @param htmlTag work only with tags of kind this: "<span class=\"js-metro-line
   *                t-metrostation-list-header t-icon-metroln ln-1\" data-line=\"1\">Название
   *                линии</span>"
   * @return name of line
   */
  @Override
  public String getLineNumber(String htmlTag) {
    int startShift = 6;
    int endShift = 9;
    String trashRegex = "[\",>]";
    return htmlTag
        .substring(htmlTag.indexOf("line=") + startShift, htmlTag.indexOf("line=") + endShift)
        .replaceAll(trashRegex, "").trim();
  }

//  public Map<Integer, List<String>> getStationsNames(String cssQuery) {
//    String[] cssQueryArray = cssQuery.split("/");
//    Elements partHtmlWithStationsNames = getElementsByQuery(cssQueryArray[0]);
//    Map<Integer, List<String>> stations = new TreeMap<>();
//    int index = 0;
//    for (Element element : partHtmlWithStationsNames) {
//      List<String> parseResult = element.select(cssQueryArray[1])
//          .stream()
//          .map(Element::text)
//          .collect(Collectors.toList());
//      stations.put(index++, parseResult);
//    }
//    return stations;
//  }


  private String[] stationsContent(String cssQuery) {
    StringBuilder sb = new StringBuilder();
    Elements elements = getElementsByQuery(cssQuery);
    String REGEX = "\\d+.\\s[аА-яЯ]+"; // string must look like: 9. имя станции
    String delimiterStations = elements.stream()
        .map(Element::text)
        .filter(string -> !string.matches(REGEX))
        .findFirst()
        .orElse("Подробно о линии")
        .trim();
    elements.forEach(i -> sb.append(i).append("/"));
    return sb.toString().split(delimiterStations);
  }


  public List<String[]> getStationsNames(String cssQuery) {

    String[] stationsNamesAndNumbers = stationsContent(cssQuery);
    List<String[]> stationsNames = new ArrayList<>();
    String delimiter = "/";
    for (String stationsNamesAndNumber : stationsNamesAndNumbers) {
      String[] names = Arrays.stream(stationsNamesAndNumber.split(delimiter))
          .map(is -> is.replaceAll("[\\w.]", "").trim())
          .toArray(String[]::new);
      stationsNames.add(names);
    }
    return stationsNames;
  }


  @Override
  public String getStationName() {
    return null;
  }

  @Override
  public String getStationNumber() {
    return null;
  }






}
