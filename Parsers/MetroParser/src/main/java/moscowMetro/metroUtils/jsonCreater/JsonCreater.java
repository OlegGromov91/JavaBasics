package moscowMetro.metroUtils.jsonCreater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import moscowMetro.metroClasses.contracts.AbstractMetro;

public class JsonCreater {

  private final Gson prettyViewJson = new GsonBuilder().setPrettyPrinting().create();
  private final AbstractMetro metro;


  public JsonCreater(AbstractMetro metro) {
    this.metro = metro;
  }

  public void printJsonFile() {
    System.out.println(getJsonFromObject());
  }

  public boolean createJsonFile(String filePath, String fileName) {
    String path = createPathFile(filePath, fileName);
    String json = getJsonFromObject();
    List<String> jsonLines = Arrays.stream(json.split("\n")).collect(Collectors.toList());
    try {
      Files.write(Paths.get(path), jsonLines,
          StandardOpenOption.CREATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new File(path).exists();
  }

  private String getJsonFromObject() {
    return prettyViewJson.toJson(metro);
  }

  private String createPathFile(String filePath, String fileName)
  {
    if(!filePath.endsWith("/") | !filePath.endsWith("\\"))
    { filePath = filePath.trim().concat("/"); }

    if(!fileName.endsWith(".json"))
    {
      fileName = fileName.trim().concat(".json");
    }

    return filePath.trim().concat(fileName.trim());
  }
}
