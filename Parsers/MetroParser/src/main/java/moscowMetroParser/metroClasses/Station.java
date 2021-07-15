package moscowMetroParser.metroClasses;



import java.util.List;
import moscowMetroParser.metroClasses.contracts.MetroContent.MetroStation;
import org.jetbrains.annotations.NotNull;

public class Station implements MetroStation {


  private final String stationNUmber;
  private final String stationName;
  private List<Connection> connections;


  public Station(String stationNUmber, String stationName) {
    this.stationNUmber = stationNUmber;
    this.stationName = stationName;
  }


  @Override
  public int compareTo(@NotNull MetroStation o) {
    return 0;
  }

  public List<Connection> getConnections (){
    return connections;
  }

  // линия и название ветки

}
