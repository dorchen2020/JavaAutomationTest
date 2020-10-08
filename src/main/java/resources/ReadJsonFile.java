package resources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJsonFile {

	public static String[] getData() 
	{
		//JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try 
		{
			// Read JSON file, obj type is JSONObject
			Object obj = parser.parse(new FileReader("C:\\Users\\Dor\\workspace\\HotAuto\\Test90\\src\\main\\java\\resources\\insertDetails.json"));
			JSONObject jsonObject = (JSONObject) obj;

			String name = (String)jsonObject.get("name");
			String email = (String)jsonObject.get("email");	
			String company = (String)jsonObject.get("company");
			String title = (String)jsonObject.get("title");
			String country = (String)jsonObject.get("country");
			String inquiryTypeOption = (String)jsonObject.get("inquiryTypeOption");		
			String message = (String)jsonObject.get("message");
			String[]arr = {name,email,company,title,country,inquiryTypeOption,message};
			return arr;
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		return null;
	}
}


