package moscowMetro.metroUtils.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Parser {

  List<String[]> getStationsNames(String cssQueryStationContent);

  List<String[]> getStationsNumber(String cssQueryStationContent);

  List<String> getLineName(String cssQuery);

  List<String> getLineNumber(String cssQueryContent, String attributeNumber);

  Map<String, String[]> getConnections(String connections_query);

  default Document defaultConnection(String URL) throws IOException {
    return Jsoup.connect(URL)
        .maxBodySize(0)
        .get();
  }


}
