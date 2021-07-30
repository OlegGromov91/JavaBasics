package moscowMetro.metroClasses.contracts;

import java.util.List;

public interface MetroStation extends AbstractMetro, Comparable<MetroStation>{


  void addConnections(List<MetroConnection> metroConnections);

}
