package moscowMetro.metroClasses;

import java.util.List;
import java.util.Map;
import java.util.Set;
import moscowMetro.metroClasses.contracts.AbstractMetro;


public class MoscowMetro extends AbstractMetro {


  public MoscowMetro(Map<String, String[]> stations, Set<Set<Connection>> connections,
      List<Line> lines) {
    super(stations, connections, lines);
  }

  @Override
  public void addStation(String lineNumber, String[] stationNames) {
    getStationsByLine().put(lineNumber, stationNames);
  }

  @Override
  public void addConnections(Set<Connection> connectionSet) {
    getConnections().add(connectionSet);
  }

  @Override
  public void addLine(Line line) {
  getLines().add(line);
  }


}
