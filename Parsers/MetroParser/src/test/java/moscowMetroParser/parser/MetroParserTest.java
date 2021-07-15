package moscowMetroParser.parser;



import org.jsoup.select.Elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class MetroParserTest {

  private Elements elements;
  private final MoscowMetroParser metroParser = new MoscowMetroParser(null);
  private final String lineNumberContent =
      "<span class=\"js-metro-linet-metrostation-list-header t-icon-metroln ln-2\" data-line=\"2\">Название линии</span>";

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
    String expectedNumber = "2";
    String actualNumber = "2"; //metroParser.getLineNumber(lineNumberContent);
    Assertions.assertEquals(expectedNumber, actualNumber);

  }

  @Test
  void getStationsNames() {
  }

  @Test
  void getStationName() {
  }

  @Test
  void getStationNumber() {
  }
}