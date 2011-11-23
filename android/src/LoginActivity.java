package mass.observation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button logoutButton = (Button)findViewById(R.id.button2);
        logoutButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View view){
        		Intent myIntent = new Intent(view.getContext(),InitActivity.class);
        		startActivityForResult(myIntent,0);
        	}
        });
     
    }
}
