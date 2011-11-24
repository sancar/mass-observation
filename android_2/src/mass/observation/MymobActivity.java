package mass.observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MymobActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpClient hc = new DefaultHttpClient();
				try {
					HttpResponse response = hc.execute(new HttpPost("http://10.0.2.2:8080/myserver/myservlet"));
					InputStream is = response.getEntity().getContent();
					//output.append(convertToString(is));
					Toast.makeText(getApplicationContext(), convertToString(is), Toast.LENGTH_LONG).show();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					//output.append("Exception 1");
					Toast.makeText(getApplicationContext(), "Exception 1", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Exception 2", Toast.LENGTH_LONG).show();
				}
			}
		});
		
    }
    
    private String convertToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
		
	}
    
    
}