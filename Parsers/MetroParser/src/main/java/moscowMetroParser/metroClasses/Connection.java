package moscowMetroParser.metroClasses;

public class Connection extends MoscowMetro{


  private final int lineNumber;
  private final String stationName;

  public Connection(int lineNumber, String stationName) {
    this.lineNumber = lineNumber;
    this.stationName = stationName;
  }



  @Override
  public int compareTo(MoscowMetro o) {
    return 0;
  }
}
