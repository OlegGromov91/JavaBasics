package moscowMetro.metroClasses;


import java.util.Objects;
import moscowMetro.metroClasses.contracts.Metro;


public class Connection implements Metro {


  private final String line;
  private final String station;

  public Connection(String lineNumber, String stationName) {
    this.line = lineNumber;
    this.station = stationName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Connection)) {
      return false;
    }
    Connection that = (Connection) o;
    return line.equals(that.line) && station.equals(that.station);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line, station);
  }

  @Override
  public String toString() {
    return "Connection{" +
        "line='" + line + '\'' +
        ", station='" + station + '\'' +
        '}';
  }

  public String getLine() {
    return line;
  }

  public String getStation() {
    return station;
  }

}
