package moscowMetroParser.metroClasses;

import java.util.List;
import moscowMetroParser.metroClasses.contracts.MetroContent.MetroLine;
import moscowMetroParser.metroClasses.contracts.MetroContent.MetroStation;

public class Line implements MetroLine {

  private final String lineNUmber;
  private final int lineName;
  private final List<MetroStation> stations;

  public Line(String lineNUmber, int lineName,
      List<MetroStation> stations) {
    this.lineNUmber = lineNUmber;
    this.lineName = lineName;
    this.stations = stations;
  }
}
