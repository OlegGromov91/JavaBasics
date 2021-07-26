package moscowMetroParser.metroClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import moscowMetroParser.metroClasses.contracts.MetroLine;
import moscowMetroParser.metroClasses.contracts.MetroStation;

public class Line implements MetroLine {

  private final String lineNUmber;
  private final String lineName;
  private final List<MetroStation> stations = new ArrayList<>();

  public Line(String lineNumber, String lineName) {
    this.lineNUmber = lineNumber;
    this.lineName = lineName;
  }

  @Override
  public void addStations (MetroStation metroStation)
  {
    stations.add(metroStation);
  }

  @Override
  public void addStations (List<MetroStation> metroStations)
  {
    stations.addAll(metroStations);
  }

  @Override
  public String toString() {
    return "Line{" +
        "lineNUmber='" + lineNUmber + '\'' +
        ", lineName=" + lineName +
        ", stations=" + stations +
        '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Line)) {
      return false;
    }
    Line line = (Line) o;
    return lineName == line.lineName && lineNUmber.equals(line.lineNUmber) && stations
        .equals(line.stations);
  }
  @Override
  public int hashCode() {
    return Objects.hash(lineNUmber, lineName, stations);
  }

  public String getLineNUmber() {
    return lineNUmber;
  }

  public String getLineName() {
    return lineName;
  }

  public List<MetroStation> getStations() {
    return stations;
  }
}
