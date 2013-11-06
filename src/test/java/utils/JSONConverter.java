package utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JSONConverter<T> {
  public static String APP_USER = "/mockedJsonData/AppUser.json";

  public List<T> getObjectsFromData(final String jsonFileName, final Class<?> clazz)
      throws JsonParseException, JsonMappingException, IOException {

    ObjectMapper mapper = new ObjectMapper();

    JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
    List<T> fileObjects = mapper.readValue(this.getClass().getResource(jsonFileName), type);

    return fileObjects;
  }

  public T getObjectFromData(final String jsonFileName, final Class<?> clazz)
      throws JsonParseException, JsonMappingException, IOException {

    ObjectMapper mapper = new ObjectMapper();

    JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
    T fileObject = mapper.readValue(this.getClass().getResource(jsonFileName), type);

    return fileObject;
  }
}
