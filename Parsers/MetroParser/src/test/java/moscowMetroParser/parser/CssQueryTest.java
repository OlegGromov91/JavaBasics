package moscowMetroParser.parser;


import java.util.Arrays;
import org.junit.jupiter.api.Test;

class CssQueryTest {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }

  @Test
  void cssEqualsValues()
  {

  String [] enumArray = Arrays.stream(CssQuery.values()).map(s -> s.identifier).toArray(String[]::new);
  Arrays.stream(enumArray).forEach(System.out::println);
  }
}