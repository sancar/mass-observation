package mass.observation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextObservationActivity extends Activity{
	private int OEID;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_observation);
		TextView EventName = (TextView)findViewById(R.id.ObservationEventName);
		EventName.setText(getIntent().getExtras().getString("oe_name"));
		TextView EventDesc = (TextView)findViewById(R.id.ObservationEventDesc);
		EventDesc.setText(getIntent().getExtras().getString("oe_desc"));
		OEID = getIntent().getExtras().getInt("oe_id");
		Button SubmitButton = (Button)findViewById(R.id.TextSubmitButton);
		SubmitButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				String observation = ((EditText)findViewById(R.id.ObservationText)).getText().toString();
				new DataPostTask().execute(observation,String.valueOf(OEID),User.getUsername() ); 
			}
			class DataPostTask extends AsyncTask<String,Void,String>{
				ProgressDialog dialog;
				public DataPostTask(){
					dialog = new ProgressDialog(TextObservationActivity.this);
				}
				@Override
				protected void onPreExecute(){
					dialog = ProgressDialog.show(TextObservationActivity.this,"","Uploading..",true);
				}
				@Override
				protected String doInBackground(String... params) {
					String o = params[0];
					String oeid = params[1];
					String un = params[2];
					List<NameValuePair> list = new ArrayList<NameValuePair>(3);
					list.add(new BasicNameValuePair("observation",o));
					list.add(new BasicNameValuePair("oeid",oeid));
					list.add(new BasicNameValuePair("username",un));
					String res = "";
					try {
						res = connectToServer(list);
					} catch (IllegalStateException e) {
					} catch (IOException e) {
					}
					return res;
				}
				
				@Override
				protected void onPostExecute(String result){
					dialog.dismiss();
					Toast.makeText(TextObservationActivity.this, result, Toast.LENGTH_SHORT).show();
					
				}

				private String connectToServer(List<NameValuePair> list) throws IllegalStateException, IOException {
					HttpClient hc = new DefaultHttpClient();
					HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/fetchTextObservation");
					//HttpPost post = new HttpPost("http://10.0.2.2:8080/myServer/fetchObservation");
					post.setEntity(new UrlEncodedFormEntity(list));
					HttpResponse response = hc.execute(post);
					InputStream is = response.getEntity().getContent();
					String res = MObActivity.convertToString(is);
					return res;
				}
				
			}
		});
	}
}
