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
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VideoActivity extends Activity {
	private Uri fileUri;
	private int OEID;
	private String OEName;
	private String OEDesc;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private static final int MEDIA_TYPE_VIDEO = 2;
	private static String path="";
	private static TextView t;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		OEID = getIntent().getExtras().getInt("oe_id");
		OEName = getIntent().getExtras().getString("oe_name");
		OEDesc = getIntent().getExtras().getString("oe_desc");
		//((TextView)findViewById(R.id.oename_video)).setText(OEName);
		//((TextView)findViewById(R.id.oedesc_video)).setText(OEDesc);
		Button VideoRecordButton = (Button) findViewById(R.id.VideoRecordButton);
		//record button onclick handler
		VideoRecordButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
				System.out.println(fileUri.getPath());
				intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				startActivityForResult(intent,CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE); 
			}
			
		});
		
		t = (TextView)findViewById(R.id.VideoName);
		t.setText("");
		
		Button Browse = (Button) findViewById(R.id.VideoRecordBrowseButton);
		Browse.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(VideoActivity.this, AndroidFileBrowser.class);
	            startActivity(i);
			}
			
		});
		
		//upload button onclick handler
		Button VideoRecordUploadButton = (Button) findViewById(R.id.VideoRecordUploadButton);
		VideoRecordUploadButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				new UploadVideoTask().execute();
				
			}
			
		});
	}
	
	
	
	public static void setPath(String patha)
	{
		
		path=patha;
		String[] temp=path.split("/");
		int a=temp.length;
		t.setText(temp[a-1]+" selected");
		
	    
		
		
		
	}
	
	
	public class UploadVideoTask extends AsyncTask<Void,Void,String> {
		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute(){
			dialog = ProgressDialog.show(VideoActivity.this,"","Uploading..",true);
		}
		@Override
		protected String doInBackground(Void... params) {
			try {
				return sendVideoFileToServer();
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
		protected void onPostExecute(String res){
			dialog.dismiss();
			Toast.makeText(VideoActivity.this, res, Toast.LENGTH_LONG).show();
			System.out.println(res);
			if(res.equals("Success!")){
				finish();
			}
		}
	}
	
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}
	private String sendVideoFileToServer() throws ClientProtocolException, IOException {
		return connectTo("http://titan.cmpe.boun.edu.tr:8082/myServer/FetchVideoObservation");
		//return connectTo("http://192.168.1.113:8080/myServer/FetchVideoObservation");
	}

	private String connectTo(String url) throws ClientProtocolException, IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpContext context = new BasicHttpContext();
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		if(!path.equals(""))
			entity.addPart("video",new FileBody(new File(path)));
		else
			entity.addPart("video",new FileBody(new File(fileUri.getPath())));
		
		
		entity.addPart("oe_id",new StringBody(String.valueOf(OEID)));
		entity.addPart("username",new StringBody(User.getUsername()));
		post.setEntity(entity);
		HttpResponse response = hc.execute(post,context);
		InputStream is = response.getEntity().getContent();
		String res = MObActivity.convertToString(is);
		return res;
	}

	private static File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MOb");
		
		if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            return null;
	        }
	    }
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	    	System.out.println("OGUZZZ");
	        return null;
	    }
	    return mediaFile;

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE){
			File videoFile = new File(fileUri.getPath());
			if(videoFile.exists()){
				TextView VideoResult = (TextView) findViewById(R.id.VideoName);
				VideoResult.setText("Video is saved as " + videoFile.getName());
			}
		}
	}
}
