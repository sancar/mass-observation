package mass.observation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MObActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void myLoginHandler(View view){
    	TextView tv = (TextView) findViewById(R.id.editText1);
    	Toast.makeText(this, tv.getText(), Toast.LENGTH_LONG).show();
    	return;
    }
}