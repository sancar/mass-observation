package mass.observation;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

public class PollObservationActivity extends Activity{
	Context context;
	private int OEID;
	private PollObservation poll;
	public int count;
	private List<PollChoices> choiceList = new ArrayList<PollChoices>();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
  
		setContentView(R.layout.poll_observation);
		TextView EventName = (TextView)findViewById(R.id.ObservationEventName);
		EventName.setText(getIntent().getExtras().getString("oe_name"));
		TextView EventDesc = (TextView)findViewById(R.id.ObservationEventDesc);
		EventDesc.setText(getIntent().getExtras().getString("oe_desc"));
		OEID = getIntent().getExtras().getInt("oe_id");
		poll=new PollObservation();
	  	try {
				getPoll();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		TextView PollText = (TextView)findViewById(R.id.PollText);
		PollText.setText(poll.getText());

		
    	try {
			getPollChoices();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
    	
    	RadioGroup radioGroup= (RadioGroup)findViewById(R.id.radioGroup1);
    	RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
    	
    	

    	
    	final RadioButton[] rb = new RadioButton[choiceList.size()];

    	
    	for(int i=0;i<choiceList.size();i++)
    	{
    		
    		rb[i]  = new RadioButton(this);
    		rb[i].setText(choiceList.get(i).getText());
    		System.out.println(choiceList.get(i).getText());
    		rb[i].setId(choiceList.get(i).getId());
    		radioGroup.addView(rb[i],layoutParams);
    		
    			
    	}
    
    	
		Button SubmitButton = (Button)findViewById(R.id.button1);
		SubmitButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				
				int selected = 0;
				List<NameValuePair> list = new ArrayList<NameValuePair>(3);
				list.add(new BasicNameValuePair("poll_id",String.valueOf(poll.getId())));
				System.out.println(poll.getId() +"dtabasepollid");
				list.add(new BasicNameValuePair("answered_by",User.getUsername()));
				System.out.println(User.getUsername()+"usernamedatabase");
				for(int i=0;i<choiceList.size();i++)
		    	{
		    	if(rb[i].isChecked()==true)
		    	{
		    		selected=rb[i].getId();
		    	}
		    	}
				System.out.println(selected +"selectedchoiceid");
				list.add(new BasicNameValuePair("choice_id",String.valueOf(selected)));
				String res = "";
				try {
					res = connect(list);
				} catch (IllegalStateException e) {
				} catch (IOException e) {
				}
				System.out.println("database manipul" +res);
				Toast.makeText(PollObservationActivity.this,"your choice is submitted", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		
		
}
	
	
	private void getPollChoices() throws ClientProtocolException, IOException, JSONException {
		StringBuilder builder = new StringBuilder();
		
		List<NameValuePair> choicelist = new ArrayList<NameValuePair>(1);
		choicelist.add(new BasicNameValuePair("poll_id",String.valueOf(poll.getId())));
		System.out.println("poll_id" +String.valueOf(poll.getId()));
		InputStream is = connectToServer(choicelist);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		System.out.println("choicelist" + builder.toString());
		JSONArray jasonarray = new JSONArray(builder.toString());
		int jasonsize = jasonarray.length();
		for(int i=0;i<jasonsize;i++){
			JSONObject jason = jasonarray.getJSONObject(i);
			PollChoices choice = new PollChoices();
			choice.setId(jason.getInt("id"));
			choice.setText(jason.getString("choice_text"));
			System.out.println("deneme choice text icin"+choice.getText());
			choice.setPollID(jason.getInt("pollID"));
			choiceList.add(choice);

		}
	}
	private InputStream connectToServer(List<NameValuePair> choicelist) throws IOException, ClientProtocolException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/getPollChoices");
		//HttpPost post= new HttpPost("http://10.0.2.2:8080/pollServer/getPollChoices");
		post.setEntity(new UrlEncodedFormEntity(choicelist));
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		return is;
	}
	
	private void getPoll() throws ClientProtocolException, IOException, JSONException {
		StringBuilder builder = new StringBuilder();
		
		
		List<NameValuePair> polllist = new ArrayList<NameValuePair>(1);
		polllist.add(new BasicNameValuePair("event_id",String.valueOf(OEID)));
		System.out.println("event_id" +String.valueOf(OEID));
		InputStream is = connectTo(polllist);
		System.out.println("inputStream" +is.toString());
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			
		}
		System.out.println("hata-orcun" + builder.toString());
		JSONArray jasonarray = new JSONArray(builder.toString());
		int jasonsize = jasonarray.length();
		for(int i=0;i<jasonsize;i++){
			JSONObject jason = jasonarray.getJSONObject(i);
			poll.setId(jason.getInt("id"));
			System.out.println(poll.getId());
			poll.setText(jason.getString("text"));
			poll.setEventID(jason.getInt("eventID"));

		}
	}

	private InputStream connectTo(List<NameValuePair> list) throws IOException, ClientProtocolException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/getPoll");
	//	HttpPost post= new HttpPost("http://10.0.2.2:8080/pollServer/getPoll");
		post.setEntity(new UrlEncodedFormEntity(list));
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		return is;
	}
	private String connect(List<NameValuePair> list) throws IllegalStateException, IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/fetchPoll");
		post.setEntity(new UrlEncodedFormEntity(list));
		HttpResponse response = hc.execute(post);
		InputStream is = response.getEntity().getContent();
		String res = MObActivity.convertToString(is);
		return res;
	}
	
}