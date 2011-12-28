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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoActivity extends Activity {
	private Uri fileUri;
	private int OEID;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private static final int MEDIA_TYPE_VIDEO = 2;
	private static String path="";
	private static TextView t;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		fileUri = (Uri) getLastNonConfigurationInstance();
		OEID = getIntent().getExtras().getInt("oe_id");
		if(fileUri != null){
			File imgFile = new File(fileUri.getPath());
			if(imgFile.exists()){
				Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				ImageView selectedPhoto = (ImageView) findViewById(R.id.ThumbnailPhoto);
				selectedPhoto.setImageBitmap(bitmap);
			}
		}
		
		
		setContentView(R.layout.photo);
		

		t = (TextView)findViewById(R.id.photo_text);
		t.setText("");
		
		t.addTextChangedListener(new TextWatcher() {
		    public void afterTextChanged(Editable s) {
		        // TODO Auto-generated method stub
		    
		    	
		    	if(path.endsWith(".jpg")){
					File imgFile = new File(path);
					if(imgFile.exists()){
						Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
						ImageView selectedPhoto = (ImageView) findViewById(R.id.ThumbnailPhoto);
						selectedPhoto.setImageBitmap(bitmap);
					}
					
				
				}

		    	
		    	
		    }

		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		        // TODO Auto-generated method stub
		    }

		    public void onTextChanged(CharSequence s, int start, int before, int count) {
		        // TODO Auto-generated method stub
		    
		}
		    
		});

		
		
		Button b = (Button)findViewById(R.id.PhotoButton);
		b.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
				startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			
		});
		Button uploadButton = (Button) findViewById(R.id.UploadButton);
		
		
		Button c = (Button)findViewById(R.id.BrowseButton);
		c.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(PhotoActivity.this, AndroidFileBrowser.class);
	            startActivity(i);
			}
			
		});
		
		
		uploadButton.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				new UploadImageTask().execute();
			}
		});
	}
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
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
		File imgFile = new File(fileUri.getPath());
		if(imgFile.exists()){
			Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			ImageView selectedPhoto = (ImageView) findViewById(R.id.ThumbnailPhoto);
			selectedPhoto.setImageBitmap(bitmap);
		}
	}
	
	@Override 
	public Object onRetainNonConfigurationInstance(){
		return fileUri;
	}
	
	class UploadImageTask extends AsyncTask<Void,Void,String>{
		ProgressDialog dialog;
		@Override
		protected String doInBackground(Void... params) {
			try {
				return sendImageFileToServer();
			} catch (IOException e) {
			}
			return null;
		}
		@Override
		protected void onPreExecute(){
			dialog = ProgressDialog.show(PhotoActivity.this, "", "Uploading..",true);
		}
		@Override
		protected void onPostExecute(String result){
			dialog.dismiss();
			Toast.makeText(PhotoActivity.this, result, Toast.LENGTH_LONG).show();
			if(result.equals("Success!")){
				finish();
			}
		}
	}

	public String sendImageFileToServer() throws IOException {
		String result = connectTo("http://titan.cmpe.boun.edu.tr:8082/myServer/FetchImageObservation");
		//String result = connectTo("http://192.168.149.166:8080/myServer/FetchImageObservation");
		return result;
	}
	
	
	
	public static void setPath(String patha)
	{
		
		path=patha;
		String[] temp=path.split("/");
		int a=temp.length;
		t.setText(temp[a-1]+" selected");
		
	    
		
		
		
	}
	
	
	
	
	private String connectTo(String url) throws IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpContext context = new BasicHttpContext();
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		if(path.endsWith(".jpg"))
			entity.addPart("image",new FileBody(new File(path)));
		else
			entity.addPart("image",new FileBody(new File(fileUri.getPath())));
		
		entity.addPart("oe_id",new StringBody(String.valueOf(OEID)));
		entity.addPart("username",new StringBody(User.getUsername()));
		post.setEntity(entity);
		HttpResponse response = hc.execute(post,context);
		InputStream is = response.getEntity().getContent();
		String res = MObActivity.convertToString(is);
		return res;
	}
}
