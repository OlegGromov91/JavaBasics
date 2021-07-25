package moscowMetroParser.parser;


import ParserAttribute.MoscowMetroParserAttr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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



/*
    Map<String, Map<String, Elements>> connections = new TreeMap<>();
    //* its work
    for (int i = 0; i < GENERAL.size(); i++) {
      //System.out.println("Line " + (i+1));
      Elements el = GENERAL.get(i).select("p");
      // System.out.println(GENERAL.get(i).attr("data-depend-set").replaceAll("lines-", ""));
      Map<String, Elements> connect = new HashMap<>();
      for (Element element : el) {
        if (element.getElementsByAttribute("title").size() != 0) {

          connect.put(element.select("span.name").text(), element.getElementsByAttribute("title"));
        }
      }
      connections.put(GENERAL.get(i).attr("data-depend-set").replaceAll("lines-", ""), connect);
    }


    for (String lineNumber : connections.keySet()
    ) {
      System.out.println("\n" + lineNumber);
      Set<List<String>> lists = new HashSet<>();
      for (String station : connections.get(lineNumber).keySet()
      ) {
        System.out.println("\n" + station);
        System.out.println(connections.get(lineNumber).get(station));
        List<String> stringList = connections.get(lineNumber).get(station).stream().map(moscowMetroParser::getConnectionContent).collect(
            Collectors.toList());
        lists.add(stringList);
      }

      }
*/
   /*
    List<String > st = new ArrayList<>();
    Map<String, Elements> connect = new TreeMap<>();
    //Map<List<String>, Elements> connect = new HashMap<>();
    for (int i = 0; i < GENERAL.size(); i++) {
      Elements el = GENERAL.get(i).select("p");
      List<String> strings = new ArrayList<>();
      for (Element element : el) {
        if (element.getElementsByAttribute("title").size() != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(GENERAL.get(i).attr("data-depend-set").replaceAll("lines-", ""))
              .append(" ")
              .append(element.select("span.name").text())
              .append("!")
              .append(element.getElementsByAttribute("title"));


          st.add(stringBuilder.toString());


          connect.put(GENERAL.get(i).attr("data-depend-set").replaceAll("lines-", "").
                  concat(" ")
                  .concat(
                      element.select("span.name").text()
                  ),
              element.getElementsByAttribute("title"));
        }
      }
    }
*/


/*
    Elements GENERAL = testDocument.select("div.js-depend");
    List<String > st = new ArrayList<>();
    for (int i = 0; i < GENERAL.size(); i++) {
      Elements el = GENERAL.get(i).select("p");
      for (Element element : el) {
        if (element.getElementsByAttribute("title").size() != 0) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(GENERAL.get(i).attr("data-depend-set").replaceAll("lines-", ""))
              .append(" ")
              .append(element.select("span.name").text())
              .append("!")
          .append(element.getElementsByAttribute("title"));
         st.add(stringBuilder.toString());
        }
      }
    }
*/


    moscowMetroParser.getConnections(CssQuery.QUERY_CONNECTIONS.identifier).forEach(System.out::println);


    /**
     * get all attribute!!!!!!
     System.out.println(GENERAL.get(0).select("p").get(6).getElementsByAttribute("title"));
     */

  }

  @Test
  void getStationsNames() {
    List<String[]> stationNames = parser
        .getStationsNames(CssQuery.QUERY_STATION_CONTENT.identifier);
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
        .getStationsNumber(CssQuery.QUERY_STATION_CONTENT.identifier);
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