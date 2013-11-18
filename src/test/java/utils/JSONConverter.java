package utils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.addicks.helpdesk.domain.AppUser;

public class JSONConverter<T> {
  public static String APP_USER = "/mockedJsonData/AppUser.json";

  public AppUser getAppUser() throws JsonGenerationException, JsonMappingException, IOException {

    AppUser user = new AppUser();
    ObjectMapper mapper = new ObjectMapper();

    mapper.writeValue(new File(APP_USER), user);

    return user;
  }
}
