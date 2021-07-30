package moscowMetro.metroClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import moscowMetro.metroClasses.contracts.MetroLine;
import moscowMetro.metroClasses.contracts.MetroStation;

public class Line  implements MetroLine{

  private final String number;
  private final String name;

  /**
   * gsonBuilder.addSerializationExclusionStrategy(new ExclusionStrategy() {
   *       @Override
   *       public boolean shouldSkipField(FieldAttributes fieldAttributes) {
   *         return fieldAttributes.getName().contains("stations");
   *       }
   *
   *       @Override
   *       public boolean shouldSkipClass(Class<?> aClass) {
   *         return false;
   *       }
   *     });
   */
  private final List<MetroStation> stations = new ArrayList<>();

  public Line(String lineNumber, String lineName) {
    this.number = lineNumber;
    this.name = lineName;
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
        "lineNUmber='" + number + '\'' +
        ", lineName=" + name +
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
    return name == line.name && number.equals(line.number) && stations
        .equals(line.stations);
  }
  @Override
  public int hashCode() {
    return Objects.hash(number, name, stations);
  }

  public String getLineNUmber() {
    return number;
  }

  public String getLineName() {
    return name;
  }

  public List<MetroStation> getStations() {
    return stations;
  }
}
