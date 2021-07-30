package moscowMetro.metroUtils.metroCreater;

import static moscowMetro.metroUtils.parser.CssQuery.ATTRIBUTE_LINE_NUMBER;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_LINE_CONTENT;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_STATION_CONTENT;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;
import moscowMetro.metroUtils.parser.MoscowMetroParser;
import moscowMetro.metroUtils.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

class MetroCreaterTest {

  private final Map<String, String[]> stations = new LinkedHashMap<>();
  private final List<Line> lines = new ArrayList<>();
  private final List<Set<Connection>> connections = new ArrayList<>();

  private final static File htmlFIle = new File("src/test/java/testHTML/test.html");
  protected final Document testDocument = Jsoup.parse(htmlFIle, "UTF8");
  //


  private final Parser moscowMetroParser = new MoscowMetroParser(testDocument);

  MetroCreaterTest() throws IOException {
  }

  @Test
  void createStations() {

    List<String> lineNumbers = moscowMetroParser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String[]> stationNames = moscowMetroParser.getStationsNames(
        QUERY_STATION_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      stations.put(lineNumbers.get(i), stationNames.get(i));
    }
    stations.forEach((k,v) -> System.out.println(k + " " + Arrays.toString(v)));

  }

  @Test
  void createLines()
  {
    List<String> lineNumbers = moscowMetroParser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String> lineNames = moscowMetroParser.getLineName(QUERY_LINE_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      lines.add(new Line(lineNumbers.get(i), lineNames.get(i)));
    }
  }

}