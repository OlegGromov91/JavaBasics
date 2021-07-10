package moscowMetroParser.MetroClasses;

import java.util.List;

public class Line extends MoscowMetro{



  private final String lineNUmber;
  private final int lineName;
  private final List<Station> stations;

      public Line(String lineNUmber, int lineName, List<Station> stations) {
        this.lineNUmber = lineNUmber;
        this.lineName = lineName;
        this.stations = stations;
      }




  @Override
  public int compareTo(MoscowMetro o) {
    return 0;
  }

      public String getLineNUmber() {
        return lineNUmber;
      }

      public int getLineName() {
        return lineName;
      }

      public List<Station> getStation() {
        return stations;
      }
    }
