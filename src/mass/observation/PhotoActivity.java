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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.*;
import android.widget.Toast;

public class PhotoActivity extends Activity {
	private Uri fileUri;
	private int OEID;
	private String OEName;
	private String OEDesc;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int MEDIA_TYPE_IMAGE = 1;
	private static final int MEDIA_TYPE_VIDEO = 2;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		fileUri = (Uri) getLastNonConfigurationInstance();
		OEID = getIntent().getExtras().getInt("oe_id");
		OEName = getIntent().getExtras().getString("oe_name");
		OEDesc = getIntent().getExtras().getString("oe_desc");
		if(fileUri != null){
			File imgFile = new File(fileUri.getPath());
			if(imgFile.exists()){
				Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				ImageView selectedPhoto = (ImageView) findViewById(R.id.ThumbnailPhoto);
				selectedPhoto.setImageBitmap(bitmap);
			}
		}
		
		setContentView(R.layout.photo);
		((TextView)findViewById(R.id.oename_photo)).setText(OEName);
		((TextView)findViewById(R.id.oedesc_photo)).setText(OEDesc);
		Button b = (Button)findViewById(R.id.PhotoButton);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
				startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			
		});
		Button uploadButton = (Button) findViewById(R.id.UploadButton);
		uploadButton.setOnClickListener(new OnClickListener(){
			@Override
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
	private String connectTo(String url) throws IOException {
		HttpClient hc = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		HttpContext context = new BasicHttpContext();
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
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
