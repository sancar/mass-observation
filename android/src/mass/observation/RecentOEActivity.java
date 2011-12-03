package mass.observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecentOEActivity extends ListActivity {
	private String[] OE_Names = new String[5];
	/** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
			getRecentOE();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		setListAdapter(new ArrayAdapter<String>(this,R.layout.list_items,OE_Names));
		ListView lv = (ListView)getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//When clicked, it will redirect to participation view
				Toast.makeText(getApplicationContext(),"halelujah", Toast.LENGTH_LONG).show();
			} 
		});

    }
    
    //this function connects with server and returns recent observation events.
    
	private void getRecentOE() throws ClientProtocolException, IOException, JSONException {
		StringBuilder builder = new StringBuilder();
		HttpClient hc = new DefaultHttpClient();
		//HttpPost post = new HttpPost("http://10.0.2.2:8080/myServer/getRecentOE");
		HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/getRecentOE");
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
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
			OE_Names[i] = jason.getString("name");
			//Toast.makeText(getApplicationContext(), jason.getString("name"), Toast.LENGTH_LONG).show();
			//atm we are just getting the name of the OE, but we need to get all the data according to that OE with use of 
			//ObservationEvent class.
		}
	}
	private static ObservationEvent OES[] = new ObservationEvent[ObservationEvent.OE_MAX];
}
