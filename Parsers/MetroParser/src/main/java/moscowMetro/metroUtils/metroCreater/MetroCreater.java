package moscowMetro.metroUtils.metroCreater;

import static moscowMetro.metroUtils.parser.CssQuery.ATTRIBUTE_LINE_NUMBER;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_LINE_CONTENT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;


public class MetroCreater {

  private final Map<String, String []> stations = new TreeMap<>();
  private final List<Line> lines = new ArrayList<>();
  private final List<Set<Connection>> connections = new ArrayList<>();

  public MetroCreater() throws IOException {
  }

  /**
   * private
   */
  public Map<String, String []> createStations ()
  {

    return null;
  }


}
