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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
			    new LoginTask().execute(username,password);
			}
			class LoginTask extends AsyncTask<String,Void,String>{
				ProgressDialog dialog;
				String username;
				String password;
				public LoginTask() {
					dialog = new ProgressDialog(MObActivity.this);
					username = "";
					password =" "
;				}
				@Override
				protected String doInBackground(String... params) {
					username = params[0];
					password = params[1];
					List<NameValuePair> n = new ArrayList<NameValuePair>(2);
			        n.add(new BasicNameValuePair("email",username));
			        n.add(new BasicNameValuePair("password",password));
					String res="";
					try {
						res = connectToServer(n);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return res;
					
				}
				@Override
				protected void onPreExecute(){
					dialog = ProgressDialog.show(MObActivity.this,"","Logging in..",true);
				}
				@Override
				protected void onPostExecute(String res){
					if(res.equals("OK")){
						if(dialog.isShowing())
							dialog.dismiss();
						//Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
						Intent myIntent = new Intent(MObActivity.this,TabWidget.class);
						myIntent.putExtra("username", username);
						myIntent.putExtra("password", password);
						startActivity(myIntent);
					}
					else if(res.equals("NOT-AUTHORIZED")){
						dialog.dismiss();
					}
					else if(res.equals("ERROR")){
						dialog.dismiss();
						//Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					}
					else {
						dialog.dismiss();
						//Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					}
				}
				public String connectToServer(List<NameValuePair> n)
				throws UnsupportedEncodingException, IOException,
				ClientProtocolException {
					HttpParams params = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(params, 60000);
					HttpClient hc = new DefaultHttpClient(params);
					//HttpPost post = new HttpPost("http://10.0.2.2:8080/myServer/MyServlet");
					HttpPost post = new HttpPost("http://titan.cmpe.boun.edu.tr:8082/myServer/MyServlet");
					post.setEntity(new UrlEncodedFormEntity(n));
					HttpResponse response = hc.execute(post);
					InputStream is = response.getEntity().getContent();
					String res = MObActivity.convertToString(is);
					return res;
			}
			}
        });
    }
    
    public static String convertToString(InputStream is) {
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