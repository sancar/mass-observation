package mass.observation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class InitActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init);
        final TextView output = (TextView)findViewById(R.id.output);
        Button loginButton = (Button)findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View view){
        		final String username = ((EditText)findViewById(R.id.UsernameInput)).getText().toString();
				final String password = ((EditText)findViewById(R.id.PasswordInput)).getText().toString();
				new Thread(new Runnable(){
					@Override
					public void run() {
						sendData(username,password);
					}}).start();
        	}

			private void sendData(String username, String password) {

		        
				// TODO Auto-generated method stub
				HttpParams p = new BasicHttpParams();
				p.setParameter("email", username);
				p.setParameter("password", password);
				HttpClient hc = new DefaultHttpClient(p);
				try {
					HttpResponse response = hc.execute(new HttpPost("http://10.0.2.2:8080/myServer/MyServlet"));
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
        });
 
    }
}