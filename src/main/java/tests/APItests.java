package tests;
import java.io.IOException;
import java.util.Scanner;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APItests {

	private String url; 
	private String jsonPost; 
	
	public APItests() {
		url = "https://jsonplaceholder.typicode.com/todos";
		jsonPost = "{\r\n" + 
				"\"userId\": 1,\r\n" + 
				"\"id\": 1,\r\n" + 
				"\"title\": \"delectus aut autem\",\r\n" + 
				"\"completed\": false\r\n" + 
				"}";
	}
	
	/*
	Execute an http POST request to the following endpoint:
	https://jsonplaceholder.typicode.com/todos with the following payload:
	{
	"userId": 1,
	"id": 1,
	"title": "delectus aut autem",
	"completed": true
	}
	 */
	@Test
	public void APItest1() throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setHeader("content-type" , "application/x-www-form-urlencoded");
		StringEntity sEntity = new StringEntity(jsonPost); 
		post.setEntity(sEntity);
		HttpResponse response = client.execute(post);
		Assert.assertTrue(response.getStatusLine().toString().contains("201"),"Post request failed.");
	}

	/*
	Execute a GET request to the following endpoint:
	https://jsonplaceholder.typicode.com/todos/1 endpoint and assert
	the data is correct (UserID, id, title and completed).
	 */
	@Test
	public void APItest2() throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url + "/1");
		HttpResponse response = client.execute(get);

		Scanner sc = new Scanner(response.getEntity().getContent());
		boolean flag = true;
		while(sc.hasNext()&&flag) {
			String line = sc.nextLine();
			if(line.contains("\"userId:\"")) {
				flag = line.equals("\"userId\": 1,");
			}
			else if(line.contains("\"id\":")) {
				flag = line.contains("\"id\": 1,");
			}
			else if(line.contains("\"title\":")) {
				flag = line.contains("\"title\": \"delectus aut autem\",");
			}
			else if(line.contains("\"completed\":")) {
				flag = line.contains("\"completed\": false");
			}
		}
		sc.close();
		Assert.assertTrue(flag,"Get request failed");
	}

	/*
	Execute a GET request to the following endpoint:
	https://jsonplaceholder.typicode.com/todos endpoint and assert all
	the objects contain the following keys:
	a. UserId 
	b. Id 
	c. Title 
	d. completed
	 */
	@Test
	public void APItest3() throws ClientProtocolException, IOException {
		
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		Scanner sc = new Scanner(response.getEntity().getContent());
		boolean keyExists = true;
		String line;
		while(sc.hasNext()) {
			line = sc.nextLine();
			if(line.contains("\"userId\":")) {
				if(!(sc.nextLine().contains("\"id\":")
						&& sc.nextLine().contains("\"title\":")
						&& sc.nextLine().contains("\"completed\":")
						)) {
					keyExists=false;	
					break;
				}
			}
		}
		sc.close();
		Assert.assertTrue(keyExists,"Get request failed");
	}
}
