package moscowMetroParser.metroClasses;



import java.util.ArrayList;
import java.util.List;
import moscowMetroParser.metroClasses.contracts.MetroConnection;
import moscowMetroParser.metroClasses.contracts.MetroStation;
import org.jetbrains.annotations.NotNull;

public class Station implements MetroStation {


  private final String stationNUmber;
  private final String stationName;
  private final List<MetroConnection> connections = new ArrayList<>();


  public Station(String stationNumber, String stationName) {
    this.stationNUmber = stationNumber;
    this.stationName = stationName;
  }


  @Override
  public void addConnections(List<MetroConnection> metroConnections) {
    connections.addAll(metroConnections);
  }

  @Override
  public int compareTo(@NotNull MetroStation o) {
    return 0;
  }

  public List<MetroConnection> getConnections (){
    return connections;
  }



}
