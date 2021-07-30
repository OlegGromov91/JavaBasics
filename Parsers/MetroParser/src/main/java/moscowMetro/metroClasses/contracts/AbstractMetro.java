package moscowMetro.metroClasses.contracts;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;

public abstract class AbstractMetro implements Metro {

  private final Map<String, String[]> stations;
  private final Set<Set<Connection>> connections;
  private final List<Line> lines;

  public AbstractMetro(Map<String, String[]> stations,
      Set<Set<Connection>> connections, List<Line> lines) {
    this.stations = stations;
    this.connections = connections;
    this.lines = lines;
  }

  abstract public void addStation(String lineNumber, String[] stationNames);

  abstract public void addConnections(Set<Connection> connectionSet);

  abstract public void addLine(Line line);


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractMetro)) {
      return false;
    }
    AbstractMetro that = (AbstractMetro) o;
    return stations.equals(that.stations) && connections.equals(that.connections)
        && lines
        .equals(that.lines);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stations, connections, lines);
  }

  @Override
  public String toString() {
    return "AbstractMetro{" +
        "stationsByLine=" + stations +
        ", connections=" + connections +
        ", lines=" + lines +
        '}';
  }

  public Map<String, String[]> getStationsByLine() {
    return stations;
  }

  public Set<Set<Connection>> getConnections() {
    return connections;
  }

  public List<Line> getLines() {
    return lines;
  }
}
