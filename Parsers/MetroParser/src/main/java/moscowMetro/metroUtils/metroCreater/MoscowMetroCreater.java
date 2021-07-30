package moscowMetro.metroUtils.metroCreater;

import static moscowMetro.metroUtils.parser.CssQuery.ATTRIBUTE_LINE_NUMBER;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_LINE_CONTENT;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_STATION_CONTENT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;
import moscowMetro.metroClasses.MoscowMetro;
import moscowMetro.metroUtils.parser.CssQuery;
import moscowMetro.metroUtils.parser.Parser;


public class MoscowMetroCreater {

  private final Parser parser;
  private static final String DELIMITER = ":";

  public MoscowMetroCreater(Parser parser) {
    this.parser = parser;
  }

  public final MoscowMetro autoCreate() {
    return new MoscowMetro(
        this.createStations(),
        this.createConnections(),
        this.createLines()
    );
  }

  public Map<String, String[]> createStations() {
    Map<String, String[]> stations = new LinkedHashMap<>();
    List<String> lineNumbers = parser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String[]> stationNames = parser.getStationsNames(
        QUERY_STATION_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      stations.put(lineNumbers.get(i), stationNames.get(i));
    }
    return stations;
  }

  public List<Line> createLines() {
    List<Line> lines = new ArrayList<>();
    List<String> lineNumbers = parser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String> lineNames = parser.getLineName(QUERY_LINE_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      lines.add(new Line(lineNumbers.get(i), lineNames.get(i)));
    }
    return lines;
  }

  /**
   * In method, loops work with "major connection key" and "minor connections values" for abstract
   * example : key - "1:Черкизовская" values - {"14:Локомотив", "5:Комсомольская"}. DELIMITER is ":"
   * Set<Connection> sortedConnections need for sort major and minor connections by hashCode, and
   * then put it in non duplicated set.
   *
   * @return no duplicate connections
   */
  public Set<Set<Connection>> createConnections() {
    Set<Set<Connection>> noDuplicateConnections = new LinkedHashSet<>();
    Map<String, String[]> parsedConnections = parser
        .getConnections(CssQuery.QUERY_CONNECTIONS.getIdentifier());

    for (String key : parsedConnections.keySet()
    ) {
      String[] keyParts = key.split(DELIMITER);
      Connection majorConnection = new Connection(keyParts[0], keyParts[1]);

      for (String value : parsedConnections.get(key)
      ) {
        String[] valueParts = value.split(DELIMITER);
        Connection minorConnections = new Connection(valueParts[0], valueParts[1]);
        Set<Connection> connectionSet = new LinkedHashSet<>();
        connectionSet.add(majorConnection);
        connectionSet.add(minorConnections);
        Set<Connection> sortedConnections = connectionSet.stream()
            .sorted(Comparator.comparingInt(Connection::hashCode)).collect(
                Collectors.toCollection(LinkedHashSet::new));
        noDuplicateConnections.add(sortedConnections);
      }
    }
    return noDuplicateConnections;
  }

}
