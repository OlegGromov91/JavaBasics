package moscowMetro.metroUtils.metroCreater;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import moscowMetro.metroClasses.Connection;

public class Pojo {

  Set<Set<Connection>> connections;

  public Pojo() {
    connections = new LinkedHashSet<>();
    Map<String, String[]> temp = new LinkedHashMap<>();
    temp.put("1:Охотный ряд", new String[]{"2:Театральная", "333:NuN", "44:DSD"});
    temp.put("2:Театральная", new String[]{"333:NiN","1:Охотный ряд", "445:DadwD"});
    for (String key : temp.keySet()
    ) {
      String [] keyParts = key.split(":");
      Connection generalConnections = new Connection(keyParts[0], keyParts[1]);

      for (String value : temp.get(key)
      ) {
        String [] valueParts = value.split(":");
        Connection minorConnections = new Connection(valueParts[0], valueParts[1]);
        // List<Connection> connectionSet = new ArrayList<>();
        Set<Connection> connectionSet = new LinkedHashSet<>();
        connectionSet.add(generalConnections);
        connectionSet.add(minorConnections);
        Set<Connection> sorted = connectionSet.stream().sorted(Comparator.comparingInt(Connection::hashCode)).collect(
            Collectors.toCollection(LinkedHashSet::new));
        connections.add(sorted);
      }

    }
  }
}
