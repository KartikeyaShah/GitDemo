package google.data;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	String jsonContent;
    public List<HashMap<String, String>> getJsonDataToMap() throws JsonMappingException, JsonProcessingException {
       
    	// Reading JSON to String
    	try {
            jsonContent = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "\\src\\test\\java\\google\\data\\PurchaseOrder.json"),
                StandardCharsets.UTF_8
            );
            // Process jsonContent here
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	// Reading String to HashMap
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
    	return data;
    }
}