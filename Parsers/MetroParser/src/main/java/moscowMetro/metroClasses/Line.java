package moscowMetro.metroClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import moscowMetro.metroClasses.contracts.Metro;


public class Line implements Metro {

  private final String number;
  private final String name;
  private transient final List<Station> stations = new ArrayList<>();

  public Line(String lineNumber, String lineName) {
    this.number = lineNumber;
    this.name = lineName;
  }

  public void addStations(Station metroStation) {
    stations.add(metroStation);
  }

  public void addStations(List<Station> metroStations) {
    stations.addAll(metroStations);
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
    return name == line.name && number.equals(line.number) && stations
        .equals(line.stations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, name, stations);
  }

  @Override
  public String toString() {
    return "Line{" +
        "lineNUmber='" + number + '\'' +
        ", lineName=" + name +
        ", stations=" + stations +
        '}';
  }

  public String getLineNumber() {
    return number;
  }

  public String getLineName() {
    return name;
  }

  public List<Station> getStations() {
    return stations;
  }
}
