package moscowMetro.metroUtils.parser;


import ParserAttribute.MoscowMetroParserAttr;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;
import moscowMetro.metroClasses.MoscowMetro;
import moscowMetro.metroClasses.Station;
import moscowMetro.metroClasses.contracts.MetroConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class MetroParserTest extends MoscowMetroParserAttr {

  MetroParserTest() throws IOException {
  }

  @BeforeEach
  void setUp() {

  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getElementsByQuery() {
  }

  @Test
  void getLineName() {
  }

  @Test
  void getLineNumber() {


    List<String> connections = parser.getConnections(CssQuery.QUERY_CONNECTIONS.getIdentifier());

   connections.forEach(System.out::println);


//    String rehex = "(ln-|\")";
//
//    String match = "11A Комсомольская:5 Комсомольская/D2 Каланчёвская";
//    String match2 = "<span class=\"t-icon-metroln ln-11A\" title=\"переход на станцию «Комсомольская» Кольцевой линии\"></span>";
//
//    Arrays.stream(match2.split(" ")).filter(i -> i.contains("ln-")).map(str -> str.replaceAll(rehex, ""))
//    .forEach(System.out::println);














//    Line line = new Line("1", "LineOne");
//    Station station = new Station("2","stationOne");
//    Connection connection = new Connection("3", "ConnectionOne");
//    List<MetroConnection> connectionList = new ArrayList<>();
//    connectionList.add(connection);
//    //station.addConnections(connectionList);
//
//    MoscowMetro moscowMetro = new MoscowMetro();
//   // moscowMetro.add(line);
//
//    Line line2 = new Line("2", "LineTwo");
//    List<Line> lineList = new ArrayList<>();
//    lineList.add(line);
//    lineList.add(line2);
//
//    Gson gson = new Gson();
//    GsonBuilder gsonBuilder = new GsonBuilder();
//    gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
//      @Override
//      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
//        return fieldAttributes.getName().contains("stations");
//      }
//
//      @Override
//      public boolean shouldSkipClass(Class<?> aClass) {
//        return false;
//      }
//    });
//
//    System.out.println(gsonBuilder.create().toJson(line));
//    System.out.println(gson.toJson(line));


/**
 * Stations should look like!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Map<String, String []> stations = new TreeMap<>();
 *     stations.put("1", new String[] {"One", "Two", "Three"});
 *     stations.put("2", new String[] {"3", "4", "5"});
 */


/**
 * Connections should look like!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * List<Set<Connection>> connections = new ArrayList<>();
 *     Set<Connection> connectionSet = new HashSet<>();
 *     Set<Connection> connectionSet2 = new HashSet<>();
 *
 *     connectionSet.add(new Connection("1","OneStation"));
 *     connectionSet.add(new Connection("2","TwoStation"));
 *     connectionSet2.add(new Connection("3","3Station"));
 *     connectionSet2.add(new Connection("4","4Station"));
 *
 * connections.add(connectionSet);
 * connections.add(connectionSet2);
 * moscowMetro.add3(connections);
 */

  }

  @Test
  void getStationsNames() {
    List<String[]> stationNames = parser
        .getStationsNames(CssQuery.QUERY_STATION_CONTENT.getIdentifier());
    Random randomStationName = new Random();
    int key = 5;
    for (int i = 0; i < testStationsNames.length - 1; i++) {
      int randomStation = randomStationName.nextInt(testStationsNames.length - 1);
      String expectedStation = stationNames.get(key)[randomStation].toLowerCase(Locale.ROOT);
      String actualStation = testStationsNames[randomStation].toLowerCase(
          Locale.ROOT);
      Assertions.assertEquals(expectedStation,
          actualStation,
          "Калужско-Рижская линия");
    }
  }

  @Test
  void getStationsNumber() {
    List<String[]> stationsNumbers = parser
        .getStationsNumber(CssQuery.QUERY_STATION_CONTENT.getIdentifier());
    String[] testNumber = stationsNumbers.get(stationsNumbers.size() - 1);
    Random randomNumber = new Random();
    for (int i = 0; i < testNumber.length - 1; i++) {
      int randomStationNumber = randomNumber.nextInt(testNumber.length - 1);
      String expectedStation = testNumber[randomStationNumber].toLowerCase(Locale.ROOT);
      String actualStation = testStationsNumbers[randomStationNumber].toLowerCase(
          Locale.ROOT);
      Assertions.assertEquals(expectedStation,
          actualStation,
          "in line of number D2 (МЦД-2) should be " + testStationsNumbers.length + " stations");
    }
  }
}