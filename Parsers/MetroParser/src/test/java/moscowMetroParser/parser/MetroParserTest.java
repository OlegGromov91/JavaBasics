package moscowMetroParser.parser;


import ParserAttribute.MoscowMetroParserAttr;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
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
//t-icon-metroln   "span.js-metro-line"
    ///<span class="t-icon-metroln ln-14" title="переход на станцию «Бульвар Рокоссовского» МЦК"></span>
  // Elements elements = testDocument.select(span.t-icon-metroln);
   Elements elements = testDocument.select("div.js-depend p");
   //elements.forEach(System.out::println);
    System.out.println(elements.get(1).select("span.t-icon-metroln").attr("title"));
    System.out.println(elements.get(1));
  // elements.forEach(i -> System.out.println(i.attr("title")));
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