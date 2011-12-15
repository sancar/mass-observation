package mass.observation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PhotoActivity extends Activity {
	private Uri fileUri;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		Button b = (Button)findViewById(R.id.PhotoButton);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
			
		});
	}
	
	protected void onActivityResult(){
		//cekilen foto save edilecek 
	}
}
