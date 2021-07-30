package moscowMetro.metroClasses;


import moscowMetro.metroClasses.contracts.MetroConnection;

public class Connection implements MetroConnection {


  private final String line;
  private final String station;

  public Connection(String lineNumber, String stationName) {
    this.line = lineNumber;
    this.station = stationName;


  }


}
