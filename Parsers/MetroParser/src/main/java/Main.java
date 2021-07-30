import java.io.IOException;
import moscowMetro.metroClasses.MoscowMetro;
import moscowMetro.metroClasses.contracts.AbstractMetro;
import moscowMetro.metroUtils.jsonCreater.JsonCreater;
import moscowMetro.metroUtils.metroCreater.MoscowMetroCreater;
import moscowMetro.metroUtils.parser.MoscowMetroParser;
import moscowMetro.metroUtils.parser.Parser;
import org.jsoup.nodes.Document;

public class Main {


  public static void main(String[] args) throws IOException {

    String URL = "https://www.moscowmap.ru/metro.html";
    Document document = Parser.DefaultConnector.defaultConnection(URL);
    Parser parser = new MoscowMetroParser(document);
    MoscowMetroCreater moscowMetroCreater = new MoscowMetroCreater(parser);
    MoscowMetro moscowMetro = moscowMetroCreater.autoCreate();
    JsonCreater jsonCreater = new JsonCreater(moscowMetro);
    jsonCreater.createJsonFile("src/main/resources", "metroJson");

  }
}
