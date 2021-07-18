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


    // line numbers


//t-icon-metroln   "span.js-metro-line"
    ///<span class="t-icon-metroln ln-14" title="переход на станцию «Бульвар Рокоссовского» МЦК"></span>

  Elements GENERAL = testDocument.select("div.js-depend");
   // span.t-icon-metroln
  Elements elements = testDocument.select("div.js-depend p");
    Map<String,Map<String,Elements>> conn = new HashMap<>();
//    String [] lineNumbers = GENERAL.stream().map(element -> element.attr("data-line"))
//        .filter(number -> !number.isEmpty()).toArray(String[]::new);

  //  GENERAL.forEach(i-> System.out.println(i.select("div.js-depend p")));
   // System.out.println(GENERAL.get(GENERAL.size()-1).attr("data-depend-set").replaceAll("lines-",""));

    for (Element element : GENERAL) {
      String lineNmae = element
          .attr("data-depend-set").replaceAll("lines-", "");
      System.out.println("\n" + lineNmae + "\n");
      for (Element value : elements) {
        //System.out.println(elements.get(j).select("span.name").text() + " = " + elements.get(j).select("span.t-icon-metroln").attr("title"));
        Map<String, Elements> elementMap = new HashMap<>();
        elementMap.put(value.select("span.name").text(),
            value.select("span.t-icon-metroln"));
        elementMap = elementMap.entrySet().stream().filter(e -> e.getValue().size() > 0)
            .collect(Collectors.toMap(
                Entry::getKey, Entry::getValue));
        System.out.println(elementMap);
      }

    }

//    for (String key: conn.keySet()
//    ) {
//      System.out.println(key);
//      System.out.println(conn.values());
//    }

  // Arrays.stream(lineNumbers).forEach(System.out::println);

//    elementMap = elementMap.entrySet().stream().filter(e-> e.getValue().size()>0).collect(Collectors.toMap(
//        Entry::getKey,Entry::getValue));

    //elementMap.forEach((k,v) -> System.out.println("key " + k + " value " + v));

   // System.out.println(elementMap);
 // elements.forEach(System.out::println);





    System.out.println("_____________\n");

 //  Elements elements = testDocument.select("div.js-depend p");
 // elements.forEach(System.out::println);
    System.out.println("_____________\n");

//    System.out.println(elements.get(1).select("span.t-icon-metroln").attr("title"));
//    System.out.println(elements.get(1));

 //  elements.forEach(i -> System.out.println(i.attr("title")));
    System.out.println("______");
//    System.out.println(elements.get(3).attributes().asList().get(0).getValue());


//<span class="js-metro-line t-metrostation-list-header t-icon-metroln ln-1" data-line="1">Сокольническая линия</span>
//<span class="t-icon-metroln ln-14" title="переход на станцию «Бульвар Рокоссовского» МЦК"></span>
    System.out.println( elements.get(0).attr("title"));
    System.out.println( elements.get(1).attr("title"));
    System.out.println( elements.get(7).attributes());

//   elements
//       .stream().map(Element::text).forEach(System.out::println);

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
          actualStation, "in line of number D2 (МЦД-2) should be " + testStationsNumbers.length + " stations");
    }
  }
}