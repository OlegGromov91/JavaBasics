package moscowMetro.metroClasses.contracts;


import java.util.List;

public interface MetroLine extends AbstractMetro {

  void addStations (MetroStation metroStation);
  void addStations (List<MetroStation> metroStations);


}
