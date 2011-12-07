package mass.observation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class MObActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View view) {
				String username = ((EditText) findViewById(R.id.UsernameInput)).getText().toString();
		        String password = ((EditText) findViewById(R.id.PasswordInput)).getText().toString();
		        List<NameValuePair> n = new ArrayList<NameValuePair>(2);
		        n.add(new BasicNameValuePair("email",username));
		        n.add(new BasicNameValuePair("password",password));
		
				try {
					String res = connectToServer(n);
					if(res.equals("OK")){
						Intent myIntent = new Intent(view.getContext(),TabWidget.class);
						startActivityForResult(myIntent,0);
					}
					else if(res.equals("NOT-AUTHORIZED")){
						Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();						
					}
					else if(res.equals("ERROR")){
						Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					}
					else {
						Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					}
					
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

			public String connectToServer(List<NameValuePair> n)
					throws UnsupportedEncodingException, IOException,
					ClientProtocolException {
				HttpClient hc = new DefaultHttpClient();
				//HttpPost post = new HttpPost("http://10.0.2.2:8080/myServer/MyServlet");
				HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/MyServlet");
				post.setEntity(new UrlEncodedFormEntity(n));
				HttpResponse response = hc.execute(post);
				InputStream is = response.getEntity().getContent();
				String res = convertToString(is);
				return res;
			}
		});
		
    }
    
    static String convertToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
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