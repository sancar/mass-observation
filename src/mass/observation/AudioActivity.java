/**
 * 
 * @author Oðuz Demir
 * 
 * @date 26.11.2011
 * 
 * @lastmodifation 28.12.2011 done by Osman Selçuk Aktepe 
 * 
 * 
 * 
 * */


package mass.observation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AudioActivity extends Activity {
	private static String mFileName = null;
	private MediaRecorder mRecorder = null;
	private boolean mStartRecording = true;
	private int OEID;
	private Uri fileUri;
	private static String path="";
	private static TextView t;
	private TextView pathView;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio);
		Button recordButton = (Button) findViewById(R.id.RecordAudioStartStopButton);
		Button AudioUploadButton = (Button) findViewById(R.id.AudioUploadButton);
		pathView = (TextView) findViewById(R.id.AudioPathText);
		
		
		

		Button browse =(Button)findViewById(R.id.AudioBrowse);
		

		browse.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(AudioActivity.this, AndroidFileBrowser.class);
	            startActivity(i);
			}
			
		});
		
		t = (TextView)findViewById(R.id.AudioPathText);
		t.setText("");
		
		recordButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				onRecord(mStartRecording);
                if (mStartRecording) {
                	((Button)v).setText("Stop");
                } else {
                    ((Button)v).setText("Start");
                }
                mStartRecording = !mStartRecording;
			}
			
		});
		AudioUploadButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				new AudioUploadTask().execute();
			}
			
		});
		
		
		
		
		
	}
	
	
	private void onRecord(boolean start) {
		if (start) {
            startRecording();
        } else {
            stopRecording();
        }
	}
	private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
        }

        mRecorder.start();
    }
	public static void setPath(String patha)
	{
		
		path=patha;
		String[] temp=path.split("/");
		int a=temp.length;
		t.setText(temp[a-1]+" selected");
		
	    
		
		
		
	}
	
	
	private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        pathView.setText("Audio file is saved to " + mFileName);
    }
	public AudioActivity() {
		File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "MOb");
		if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	        }
	    }
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		mFileName = mediaStorageDir.getPath() + File.separator + "AUD_" + timeStamp +".3gp";
    }

	class AudioUploadTask extends AsyncTask<Void,Void,String>{
		ProgressDialog dialog;
		@Override
		protected String doInBackground(Void... arg0) {
			try {
				return sendAudioFileToServer();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPreExecute(){
			dialog = ProgressDialog.show(AudioActivity.this,"","Uploading..",true);
		}
		@Override
		protected void onPostExecute(String res){
			dialog.dismiss();
			Toast.makeText(AudioActivity.this, res, Toast.LENGTH_LONG).show();
			System.out.println(res);
			if(res.equals("Success!")){
				finish();
			}
		}
		
	}
	private String sendAudioFileToServer() throws ClientProtocolException, IOException{
		return connectTo("http://titan.cmpe.boun.edu.tr:8082/myServer/FetchAudioObservation");
		//return connectTo("http://192.168.149.110:8080/myServer/FetchAudioObservation");
	}


	private String connectTo(String url) throws ClientProtocolException, IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpContext context = new BasicHttpContext();
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		if(!path.equals(""))
			entity.addPart("audio",new FileBody(new File(path)));
		else
			entity.addPart("audio",new FileBody(new File(mFileName)));
		
		
		
		entity.addPart("oe_id",new StringBody(String.valueOf(OEID)));
		entity.addPart("username",new StringBody(User.getUsername()));
		post.setEntity(entity);
		HttpResponse response = hc.execute(post,context);
		InputStream is = response.getEntity().getContent();
		String res = MObActivity.convertToString(is);
		return res;
	} 
	
}
