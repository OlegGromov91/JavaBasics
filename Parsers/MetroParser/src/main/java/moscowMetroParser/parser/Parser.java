package moscowMetroParser.parser;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Parser {

  List<String[]> getStationsNames(String cssQueryStationContent);

  List<String[]> getStationNumber(String cssQueryStationContent);

  List<String> getLineName(String cssQuery);

  List<String> getLineNumber(String cssQueryContent, String attributeNumber);

  default Document defaultConnection(String URL) throws IOException
  {
    return Jsoup.connect(URL)
        .maxBodySize(0)
        .get();
  }



}
