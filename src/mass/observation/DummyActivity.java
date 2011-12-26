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
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DummyActivity extends Activity {
    /** Called when the activity is first created. */
    ListView lv;
    RecentOEAdapter adapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_oe);
    	User.setUsername(getIntent().getExtras().getString("username"));
    	User.setPassword(getIntent().getExtras().getString("password"));
        lv = (ListView) findViewById(R.id.recet_oe_list);
        new RecentOEAdaptingTask().execute();
    }
	class RecentOEAdaptingTask extends AsyncTask<Void,Void,RecentOEAdapter>{
		ProgressDialog dialog;
		@Override 
		protected void onPreExecute(){
			dialog = ProgressDialog.show(DummyActivity.this,"","Downloading..",true);
		}
		@Override
		protected RecentOEAdapter doInBackground(Void... arg0) {
			try {
				adapter = new RecentOEAdapter(DummyActivity.this);
				return adapter;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(RecentOEAdapter adapter){
			dialog.dismiss();
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					final ObservationEvent oe = (ObservationEvent) parent.getItemAtPosition(position);
					if(oe.isObservable()){
						createAlertDialogToSelectObservationType(oe);
					}
					else {
						createAlertDialogToSendRequest(oe);
					}
				}
				private void createAlertDialogToSendRequest(final ObservationEvent oe) {
					AlertDialog.Builder builder = new AlertDialog.Builder(DummyActivity.this);
					builder.setTitle("Request Permission");
					builder.setMessage("Permission is needed to participate in this OE.");
					builder.setPositiveButton("Send request", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int id) {
							try {
								String result = OEFetcher.sendRequestToParticipate(oe.getId());
								dialog.dismiss();
								Toast.makeText(DummyActivity.this, result, Toast.LENGTH_SHORT).show();
							} catch (ClientProtocolException e) {
							} catch (IOException e) {}
						}
					});
					builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
						}
						
					});
					AlertDialog alert = builder.create();
					alert.show();
				}
				private void createAlertDialogToSelectObservationType(final ObservationEvent oe) {
					AlertDialog.Builder builder = new AlertDialog.Builder(DummyActivity.this);
					builder.setTitle("Choose observation type");
					final String[] types = getTypes(oe);
					builder.setSingleChoiceItems(types, -1, new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int item) {
							if(types[item] == "Text"){
								Intent intent = new Intent(DummyActivity.this,TextObservationActivity.class);
								intent.putExtra("oe_id", oe.getId());
								intent.putExtra("oe_name",oe.getName());
								intent.putExtra("oe_desc", oe.getDesc());
								startActivity(intent);
							}
							else if(types[item] == "Poll"){
								
							}
							else if(types[item] == "Image"){
								Intent intent = new Intent(DummyActivity.this,PhotoActivity.class);
								intent.putExtra("oe_id", oe.getId());
								intent.putExtra("oe_name",oe.getName());
								intent.putExtra("oe_desc",oe.getDesc());
								startActivity(intent);
							}
							else if(types[item] == "Audio"){
								Intent intent = new Intent(DummyActivity.this,AudioActivity.class);
								intent.putExtra("oe_id", oe.getId());
								intent.putExtra("oe_name",oe.getName());
								intent.putExtra("oe_desc",oe.getDesc());
								startActivity(intent);
							}
							else if(types[item] == "Video"){
								Intent intent = new Intent(DummyActivity.this,VideoActivity.class);
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
	}
}
