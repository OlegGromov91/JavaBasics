package moscowMetro.metroClasses;



import java.util.List;
import java.util.Map;
import java.util.Set;



public class MoscowMetro {

  private final Map<String, String []> stations;
  private final List<Line> lines;
  private final List<Set<Connection>> connections;


  public MoscowMetro(Map<String, String[]> stations,
      List<Line> lines,
      List<Set<Connection>> connections) {
    this.stations = stations;
    this.lines = lines;
    this.connections = connections;
  }

  ////  public void add(Map<String, String []> stations) {
////    this.stations.putAll(stations);
////  }
//
////  public void add2(List<Line> lineList) {
////    lines.addAll(lineList);
////  }
//
//  public void add3(List<Set<Connection>> connections) {
//    this.connections.addAll(connections);
//  }
//
//
////  public void add(Line line) {
////    lineList.add(line);
////  }


}
