package mass.observation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SearchActivity extends Activity {
	ListView searchList;
	SearchAdapter adapter;
	CheckBox TagBox;
	CheckBox NameBox;
	CheckBox DescBox;
	EditText pattern;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_list);
		TagBox = (CheckBox) findViewById(R.id.search_tags_checkbox);
		NameBox = (CheckBox) findViewById(R.id.search_names_checkbox);
		DescBox = (CheckBox) findViewById(R.id.search_desc_checkbox);
		pattern = (EditText) findViewById(R.id.SearchPatternText);
		Button searchButton = (Button) findViewById(R.id.searchButton);
		searchList = (ListView) findViewById(R.id.searchList);
		searchButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				new SearchResultFetchingTask().execute();
			}
			
		});
	}
	
	class SearchResultFetchingTask extends AsyncTask<Void,Void,SearchAdapter>{
		ProgressDialog dialog;
		@Override
		protected void onPreExecute(){
			dialog = ProgressDialog.show(SearchActivity.this,"","Downloading..",true);
		}
		@Override
		protected SearchAdapter doInBackground(Void... arg0) {
			HashMap<String,String> map = new HashMap<String,String>();
			int value = TagBox.isChecked() ? 1 : 0;
			map.put("tag_selected", String.valueOf(value));
			value = NameBox.isChecked() ? 1 : 0;
			map.put("name_selected", String.valueOf(value));
			value = DescBox.isChecked() ? 1 : 0;
			map.put("desc_selected", String.valueOf(value));
			map.put("pattern",pattern.getText().toString());
			try {
				adapter = new SearchAdapter(SearchActivity.this,map);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return adapter;
		}
		@Override
		protected void onPostExecute(SearchAdapter adapter){
			dialog.dismiss();
			searchList.setAdapter(adapter);
		}
		
	}
}
