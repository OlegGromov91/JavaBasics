package moscowMetroParser.parser;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public interface Parser {

  String getStationName();

  String getStationNumber();

  List<String> getLineName(String cssQuery);

  String getLineNumber(String htmlTag);

  default Document defaultConnection(String URL) throws IOException
  {
    return Jsoup.connect(URL)
        .maxBodySize(0)
        .get();
  }



}
