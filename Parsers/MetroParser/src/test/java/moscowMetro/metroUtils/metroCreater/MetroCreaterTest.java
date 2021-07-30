package moscowMetro.metroUtils.metroCreater;

import static moscowMetro.metroUtils.parser.CssQuery.ATTRIBUTE_LINE_NUMBER;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_LINE_CONTENT;
import static moscowMetro.metroUtils.parser.CssQuery.QUERY_STATION_CONTENT;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import moscowMetro.metroClasses.Connection;
import moscowMetro.metroClasses.Line;
import moscowMetro.metroUtils.parser.CssQuery;
import moscowMetro.metroUtils.parser.MoscowMetroParser;
import moscowMetro.metroUtils.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

class MetroCreaterTest {

  private final Map<String, String[]> stations = new LinkedHashMap<>();
  private final List<Line> lines = new ArrayList<>();
  private final List<Set<Connection>> connections = new ArrayList<>();

  private final static File htmlFIle = new File("src/test/java/testHTML/test.html");
  protected final Document testDocument = Jsoup.parse(htmlFIle, "UTF8");
  //


  private final Parser moscowMetroParser = new MoscowMetroParser(testDocument);

  MetroCreaterTest() throws IOException {
  }

  @Test
  void createStations() {

    List<String> lineNumbers = moscowMetroParser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String[]> stationNames = moscowMetroParser.getStationsNames(
        QUERY_STATION_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      stations.put(lineNumbers.get(i), stationNames.get(i));
    }
    stations.forEach((k,v) -> System.out.println(k + " " + Arrays.toString(v)));

  }

  @Test
  void createLines()
  {
    List<String> lineNumbers = moscowMetroParser.getLineNumber(QUERY_LINE_CONTENT.getIdentifier(),
        ATTRIBUTE_LINE_NUMBER.getIdentifier());
    List<String> lineNames = moscowMetroParser.getLineName(QUERY_LINE_CONTENT.getIdentifier());
    for (int i = 0; i < lineNumbers.size(); i++) {
      lines.add(new Line(lineNumbers.get(i), lineNames.get(i)));
    }
  }

  @Test
  void createConnections()
  {
   // List<List<Connection>> connectionResult = new ArrayList<>();
    Set<Set<Connection>> connectionResult = new LinkedHashSet<>();
    Map<String, String[]> connections = new LinkedHashMap<>();
      connections.put("1:Охотный ряд", new String[]{"2:Театральная", "333:NuN", "44:DSD"});
      connections.put("2:Театральная", new String[]{"333:NiN","1:Охотный ряд", "445:DadwD"});
    for (String key : connections.keySet()
    ) {
      String [] keyParts = key.split(":");
      Connection generalConnections = new Connection(keyParts[0], keyParts[1]);

      for (String value : connections.get(key)
      ) {
        String [] valueParts = value.split(":");
        Connection minorConnections = new Connection(valueParts[0], valueParts[1]);
       // List<Connection> connectionSet = new ArrayList<>();
        Set<Connection> connectionSet = new LinkedHashSet<>();
        connectionSet.add(generalConnections);
        connectionSet.add(minorConnections);
       Set<Connection> sorted = connectionSet.stream().sorted(Comparator.comparingInt(Connection::hashCode)).collect(
           Collectors.toCollection(LinkedHashSet::new));
        connectionResult.add(sorted);
      }

    }

       Gson gson = new Gson();
      Pojo pojo = new Pojo();
    System.out.println(gson.toJson(pojo));

    /**
     * D:\Programs\JDK\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Programs\IntelliJ IDEA Community Edition 2020.3.2\lib\idea_rt.jar=50968:D:\Programs\IntelliJ IDEA Community Edition 2020.3.2\bin" -Dfile.encoding=UTF-8 -classpath "D:\Programs\IntelliJ IDEA Community Edition 2020.3.2\lib\idea_rt.jar;C:\Users\SuperUser\.m2\repository\org\junit\platform\junit-platform-launcher\1.8.0-M1\junit-platform-launcher-1.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\junit\vintage\junit-vintage-engine\5.8.0-M1\junit-vintage-engine-5.8.0-M1.jar;D:\Programs\IntelliJ IDEA Community Edition 2020.3.2\plugins\junit\lib\junit5-rt.jar;D:\Programs\IntelliJ IDEA Community Edition 2020.3.2\plugins\junit\lib\junit-rt.jar;D:\OlegGromov91\JavaBasics\Parsers\MetroParser\target\test-classes;D:\OlegGromov91\JavaBasics\Parsers\MetroParser\target\classes;C:\Users\SuperUser\.m2\repository\org\jsoup\jsoup\1.14.1\jsoup-1.14.1.jar;C:\Users\SuperUser\.m2\repository\com\google\code\gson\gson\2.8.7\gson-2.8.7.jar;C:\Users\SuperUser\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\SuperUser\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\SuperUser\.m2\repository\org\junit\jupiter\junit-jupiter\5.8.0-M1\junit-jupiter-5.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\junit\jupiter\junit-jupiter-api\5.8.0-M1\junit-jupiter-api-5.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\apiguardian\apiguardian-api\1.1.1\apiguardian-api-1.1.1.jar;C:\Users\SuperUser\.m2\repository\org\opentest4j\opentest4j\1.2.0\opentest4j-1.2.0.jar;C:\Users\SuperUser\.m2\repository\org\junit\platform\junit-platform-commons\1.8.0-M1\junit-platform-commons-1.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\junit\jupiter\junit-jupiter-params\5.8.0-M1\junit-jupiter-params-5.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\junit\jupiter\junit-jupiter-engine\5.8.0-M1\junit-jupiter-engine-5.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\junit\platform\junit-platform-engine\1.8.0-M1\junit-platform-engine-1.8.0-M1.jar;C:\Users\SuperUser\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jdk8\1.4.20\kotlin-stdlib-jdk8-1.4.20.jar;C:\Users\SuperUser\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib\1.4.20\kotlin-stdlib-1.4.20.jar;C:\Users\SuperUser\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-common\1.4.20\kotlin-stdlib-common-1.4.20.jar;C:\Users\SuperUser\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jdk7\1.4.20\kotlin-stdlib-jdk7-1.4.20.jar;C:\Users\SuperUser\.m2\repository\org\jetbrains\annotations\13.0\annotations-13.0.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 moscowMetro.metroUtils.metroCreater.MetroCreaterTest,createConnections
     * Connection{line='1', station='Охотный ряд'} 218057749
     * Connection{line='2', station='Театральная'} 1927178405
     * 97034993
     * __________
     * Connection{line='1', station='Охотный ряд'} 218057749
     * Connection{line='333', station='NuN'} 1649557
     * -1828493855
     * __________
     * Connection{line='2', station='Театральная'} 1927178405
     * Connection{line='333', station='NiN'} 1649185
     * -385361443
     * __________
     * Connection{line='2', station='Театральная'} 1927178405
     * Connection{line='1', station='Охотный ряд'} 218057749
     * -168952879
     *
     */


    //connectionResult.forEach(System.out::println);

//    for (int i = 0; i < connectionResult.size(); i++) {
//
//
////      connectionResult   get(i).forEach(s-> System.out.println(s + " " + s.hashCode()));
//     // connectionResult.get(i) = Collections.sort(Comparator.comparing(Connection::getLine));
////      System.out.println(connectionResult.get(i).hashCode());
//      System.out.println("__________");
//    }






    String [] strings = new String[] {"3 Площадь Революции", "2 Театральная"};
    Set<Connection> connectionSet = new HashSet<>();
    for (int i = 0; i < strings.length; i++) {
      String [] parts = strings[i].split(" ");
      connectionSet.add(new Connection(parts[0], parts[1]));
    }
    //connectionSet.forEach(System.out::println);

  }
}