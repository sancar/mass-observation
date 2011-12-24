package mass.observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecentOEActivity extends ListActivity {
	private ObservationEvent OES[] = new ObservationEvent[10];
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        for(int i=0;i<10;i++)
        	OES[i] = new ObservationEvent();
        getCredentials();
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
		
		setListAdapter(new ArrayAdapter<ObservationEvent>(this,R.layout.list_items,OES));
		
		final ListView lv = (ListView)getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				final ObservationEvent oe = (ObservationEvent)lv.getItemAtPosition(position);
				AlertDialog.Builder builder = new AlertDialog.Builder(RecentOEActivity.this);
				builder.setTitle("Choose observation type");
				final String[] types = getTypes(oe);
				builder.setSingleChoiceItems(types, -1, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int item) {
						if(types[item] == "Text"){
							Intent intent = new Intent(RecentOEActivity.this,TextObservationActivity.class);
							intent.putExtra("oe_id", oe.getId());
							intent.putExtra("oe_name",oe.getName());
							intent.putExtra("oe_desc", oe.getDesc());
							startActivity(intent);
						}
						else if(types[item] == "Poll"){
							
						}
						else if(types[item] == "Image"){
							Intent intent = new Intent(RecentOEActivity.this,PhotoActivity.class);
							intent.putExtra("oe_id", oe.getId());
							intent.putExtra("oe_name",oe.getName());
							intent.putExtra("oe_desc",oe.getDesc());
							startActivity(intent);
						}
						else if(types[item] == "Audio"){
							
						}
						else if(types[item] == "Video"){
							Intent intent = new Intent(RecentOEActivity.this,VideoActivity.class);
							intent.putExtra("oe_id",oe.getId());
							intent.putExtra("oe_name",oe.getName());
							intent.putExtra("oe_desc",oe.getDesc());
							startActivity(intent);
						}
						else {
							//WHAT THE F.K IS THIS?
						}
					}});
				AlertDialog alert = builder.create();
				alert.show();
			}

			private String[] getTypes(final ObservationEvent oe) {
				ArrayList<String> types = new ArrayList<String>();
				if(oe.getPoll().equals("1"))types.add("Poll");
				if(oe.getText().equals("1"))types.add("Text");
				if(oe.getImage().equals("1"))types.add("Image");
				if(oe.getVideo().equals("1"))types.add("Video");
				if(oe.getAudio().equals("1"))types.add("Audio");
				String[] t = new String[types.size()];
				for(int i=0;i<types.size();i++){
					t[i] = types.get(i);
				}
				return t;
			}
		});
    }

	private void getCredentials() {
		User.setUsername(getIntent().getExtras().getString("username"));
	    User.setPassword(getIntent().getExtras().getString("password"));
	}
  
    //this function connects with server and returns recent observation events.
    
	private void getRecentOE() throws ClientProtocolException, IOException, JSONException {
		StringBuilder builder = new StringBuilder();
		InputStream is = connectTo("http://titan.cmpe.boun.edu.tr:8082/myServer/getRecentOE");
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
			OES[i].setName(jason.getString("name"));
			OES[i].setDesc(jason.getString("desc"));
			OES[i].setId(jason.getInt("id"));
			OES[i].setAudio(jason.getString("audio"));
			OES[i].setImage(jason.getString("image"));
			OES[i].setPoll(jason.getString("poll"));
			OES[i].setText(jason.getString("text"));
			OES[i].setVideo(jason.getString("video"));
		}
	}

	private InputStream connectTo(String url) throws IOException, ClientProtocolException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		//HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/getRecentOE");
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		return is;
	}
}
