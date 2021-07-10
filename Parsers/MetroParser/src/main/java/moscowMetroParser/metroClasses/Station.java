package moscowMetroParser.metroClasses;


import java.util.ArrayList;
import java.util.List;

public class Station extends MoscowMetro{

  private final String stationNUmber;
  private final int stationName;
  private List<Connection> connections = new ArrayList<>();



  public Station(String stationNUmber, int stationName)
  {
    this.stationNUmber = stationNUmber;
    this.stationName = stationName;
  }

  public void addConnection(Connection connection)
  {
    connections.add(connection);
  }



  // линия и название ветки

    @Override
    public int compareTo(MoscowMetro o) {
      return 0;
    }
}
