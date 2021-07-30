package moscowMetro.metroClasses;


import java.util.Objects;
import moscowMetro.metroClasses.contracts.Metro;

public class Station implements Metro {

  private final String number;
  private final String name;

  public Station(String stationNumber, String stationName) {
    this.number = stationNumber;
    this.name = stationName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Station)) {
      return false;
    }
    Station station = (Station) o;
    return number.equals(station.number) && name.equals(station.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, name);
  }

  @Override
  public String toString() {
    return "Station{" +
        "number='" + number + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  public String getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }
}
