package moscowMetro.metroUtils.metroCreater;

import static moscowMetro.metroUtils.parser.CssQuery.ATTRIBUTE_LINE_NUMBER;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_LINE_CONTENT;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_STATION_CONTENT;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import metroAttribute.MoscowMetroAttr;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;
import moscowMetro.metroUtils.jsonCreater.JsonCreater;
import moscowMetro.metroUtils.parser.CssQuery;
import org.junit.jupiter.api.Test;

class MoscowMetroCreaterTest extends MoscowMetroAttr {

  private final Map<String, String[]> stations = new LinkedHashMap<>();
  private final List<Line> lines = new ArrayList<>();
  private final List<Set<Connection>> connections = new ArrayList<>();


  MoscowMetroCreaterTest() throws IOException {
  }


  @Test
  void createStations() {
    Map<String, String[]> stations = new LinkedHashMap<>();
    List<String> lineNumbers = parser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String[]> stationNames = parser.getStationsNames(
        QUERY_STATION_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      stations.put(lineNumbers.get(i), stationNames.get(i));
    }
    stations.forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));

  }

  @Test
  void createLines() {
    List<String> lineNumbers = parser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String> lineNames = parser.getLineName(QUERY_LINE_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      lines.add(new Line(lineNumbers.get(i), lineNames.get(i)));
    }
  }

  @Test
  void createConnections() {

    Set<Set<Connection>> noDuplicateConnections = new LinkedHashSet<>();
    Map<String, String[]> connections = parser
        .getConnections(CssQuery.QUERY_CONNECTIONS.getIdentifier());
    for (String key : connections.keySet()
    ) {
      String[] keyParts = key.split(":");
      Connection generalConnections = new Connection(keyParts[0], keyParts[1]);
      for (String value : connections.get(key)
      ) {
        String[] valueParts = value.split(":");
        Connection minorConnections = new Connection(valueParts[0], valueParts[1]);
        Set<Connection> connectionSet = new LinkedHashSet<>();
        connectionSet.add(generalConnections);
        connectionSet.add(minorConnections);
        Set<Connection> sorted = connectionSet.stream()
            .sorted(Comparator.comparingInt(Connection::hashCode)).collect(
                Collectors.toCollection(LinkedHashSet::new));
        noDuplicateConnections.add(sorted);
      }
    }

  }

  @Test
  void autoCreate() {
    MoscowMetroCreater moscowMetroCreater = new MoscowMetroCreater(parser);
    Gson gson = new Gson();
    GsonBuilder gsonBuilder = new GsonBuilder();
    JsonObject a = gson.toJsonTree(moscowMetroCreater.autoCreate()).getAsJsonObject();
    Gson gsons = new GsonBuilder().setPrettyPrinting().create();
    String s = gsons.toJson(moscowMetroCreater.autoCreate());

//    Arrays.stream(s.split("\n")).forEach(System.out::println);
    JsonCreater jsonCreater = new JsonCreater(moscowMetroCreater.autoCreate());
     jsonCreater.printJsonFile();
    //System.out.println(jsonCreater.createJsonFile("src/main/resources","metroMoscow.txt"));
    //"src/main/resources/metroMoscow.txt"
  }

}