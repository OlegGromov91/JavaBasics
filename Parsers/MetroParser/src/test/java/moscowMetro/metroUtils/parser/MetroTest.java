package moscowMetro.metroUtils.parser;


import metroAttribute.MoscowMetroAttr;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class MetroTest extends MoscowMetroAttr {

  MetroTest() throws IOException {
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

    Map<String, String[]> connections = parser
        .getConnections(CssQuery.QUERY_CONNECTIONS.getIdentifier());

    connections.forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v)));
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