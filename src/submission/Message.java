package submission;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.*;

public class Message {

	private String url;
	
	public Message(String str) {
		this.url = str;
		}
	
	public void sendToAPI(String username,String message) {

		String sendUrl =this.url + "postMessage";
		String body = username + "\n" + message;
		ContentType type = ContentType.DEFAULT_TEXT;
		try {
			Request.Post(sendUrl).bodyString(body, type).execute().returnContent().asString();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String getFromAPI() {

		String getURL =this.url +"getMessages?n=40";
		String response = "";

		try{
			response = Request.Get(getURL).execute().returnContent().asString();
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return response;
	}

	public String jsonToFormat(String response) {
		
		JsonValue jsonValue = Json.parse(response);
		JsonArray jsonArray = jsonValue.asArray();
		String toReturn="";

		for(int i=jsonArray.size()-1;i>-1;i--) {
		JsonValue jValue = jsonArray.get(i);
		JsonObject jsonObject = jValue.asObject();
		
		JsonValue username = jsonObject.get("username");
		JsonValue message = jsonObject.get("message");
		JsonValue time = jsonObject.get("timestamp");
		Date time1 = new Date(time.asLong()*1000);
		String readable = new SimpleDateFormat("MMM d @ h:mm:ss a").format(time1);
		toReturn = toReturn + "[" + readable + "]" + " " + username.asString() + ": " + message.asString() + "\n";

		}
		System.out.println(toReturn);
		return toReturn;
	}

}
