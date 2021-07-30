package moscowMetro.metroClasses;


import java.util.Objects;
import moscowMetro.metroClasses.contracts.MetroConnection;

public class Connection implements MetroConnection {


  private final String line;
  private final String station;

  public Connection(String lineNumber, String stationName) {
    this.line = lineNumber;
    this.station = stationName;


  }


  @Override
  public String toString() {
    return "Connection{" +
        "line='" + line + '\'' +
        ", station='" + station + '\'' +
        '}';
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

  public String getLine() {
    return line;
  }

  public String getStation() {
    return station;
  }
}
