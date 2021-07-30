package ParserAttribute;

import java.io.File;
import java.io.IOException;

import java.util.stream.Stream;

import moscowMetro.metroUtils.parser.MoscowMetroParser;
import moscowMetro.metroUtils.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MoscowMetroParserAttr {

  private final static File htmlFIle = new File("src/test/java/testHTML/test.html");
  protected final Document testDocument = Jsoup.parse(htmlFIle, "UTF8");
  protected final Parser parser = new MoscowMetroParser(testDocument);

  //Калужско-Рижская линия
  protected final String[] testStationsNames = new String[]{
      "Новоясеневская",
      "Ясенево",
      "Теплый Стан",
      "Коньково",
      "Беляево",
      "Калужская",
      "Новые Черёмушки",
      "Профсоюзная",
      "Академическая",
      "Ленинский проспект",
      "Шаболовская",
      "Октябрьская",
      "Третьяковская",
      "Китай-Город",
      "Тургеневская",
      "Сухаревская",
      "Проспект Мира",
      "Рижская",
      "Алексеевская",
      "ВДНХ",
      "Ботанический Сад",
      "Свиблово",
      "Бабушкинская",
      "Медведково"
  };
  private final static int NUMBER_OF_STATIONS = 35; // МЦД-2
  protected final String[] testStationsNumbers = Stream.iterate(1, i  -> i + 1).limit(NUMBER_OF_STATIONS).map(String::valueOf).toArray(String[]::new);


  public MoscowMetroParserAttr() throws IOException {
  }
}
