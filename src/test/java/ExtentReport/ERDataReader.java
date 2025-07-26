package ExtentReport;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ERDataReader {
	
	public List<HashMap<String, String>> ERdataReaderFromJson() throws IOException {
		
		File file = new File(System.getProperty("user.dir")+"/src//main//java//DataPackage//data.json");
		
		//String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src//main//java//DataPackage//data.json"));
		String jsonContent=FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
		}
		
	}

