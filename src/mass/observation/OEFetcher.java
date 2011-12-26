package mass.observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OEFetcher {
	
	public static ArrayList<ObservationEvent> getPopularOES() throws JSONException, IOException{
		ArrayList<ObservationEvent> PopularOES = new ArrayList<ObservationEvent>();
		StringBuilder builder = new StringBuilder();
		InputStream is = connectTo("http://titan.cmpe.boun.edu.tr:8082/myServer/getPopularOE");
		//InputStream is = connectTo("http://192.168.149.110:8080/myServer/getPopularOE");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		JSONArray jasonarray = new JSONArray(builder.toString());
		int jasonsize = jasonarray.length();
		for(int i=0;i<jasonsize;i++){
			JSONObject jason = jasonarray.getJSONObject(i);
			ObservationEvent oe = new ObservationEvent();
			oe.setName(jason.getString("name"));
			oe.setDesc(jason.getString("desc"));
			oe.setId(jason.getInt("id"));
			oe.setAudio(jason.getString("audio"));
			oe.setImage(jason.getString("image"));
			oe.setPoll(jason.getString("poll"));
			oe.setText(jason.getString("text"));
			oe.setVideo(jason.getString("video"));
			PopularOES.add(oe);
		}
		return PopularOES;
	}
	public static boolean canObserve(int id) throws ClientProtocolException, IOException{
		StringBuilder builder = new StringBuilder();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("oe_id",String.valueOf(id)));
		list.add(new BasicNameValuePair("username",User.getUsername()));
		InputStream is = connectToWithList("http://titan.cmpe.boun.edu.tr:8082/myServer/canobserveOE",list);
		//InputStream is = connectToWithList("http://192.168.149.110:8080/myServer/canobserveOE",list);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String result = builder.toString();
		if(result.equals("1"))return true;
		else return false;
	}
	private static InputStream connectToWithList(String url,
			List<NameValuePair> list) throws ClientProtocolException, IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(list));
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		return is;
	}
	
	public static String sendRequestToParticipate(int oeid) throws ClientProtocolException, IOException{
		StringBuilder builder = new StringBuilder();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("oe_id",String.valueOf(oeid)));
		list.add(new BasicNameValuePair("username",User.getUsername()));
		InputStream is = connectToWithList("http://titan.cmpe.boun.edu.tr:8082/myServer/ObservationRequest",list);
		//InputStream is = connectToWithList("http://192.168.149.110:8080/myServer/ObservationRequest",list);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String result = builder.toString();
		return result;
	}
	private static InputStream connectTo(String url) throws ClientProtocolException, IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		return is;
	}
}
