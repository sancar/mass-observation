package mass.observation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {
	ArrayList<ObservationEvent> events;
	Context context;
	public SearchAdapter(Context c, HashMap<String,String> map) throws ClientProtocolException, IOException, JSONException{
		context = c;
		//events = OEFetcher.getSearchResult("http://titan.cmpe.boun.edu.tr:8082/myServer/getSearchOE",map);
		events = OEFetcher.getSearchResult("http://192.168.150.150:8080/myServer/getSearchOE",map);
	}
	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater	= LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.search_list_item,parent,false);
		}
		TextView SearchOEText = (TextView)convertView.findViewById(R.id.SearchOEName);
		SearchOEText.setText(events.get(position).getName());
		TextView SearchOEDesc = (TextView) convertView.findViewById(R.id.SearchOEDesc);
		SearchOEDesc.setText(events.get(position).getDesc());
		if(events.get(position).isObservable()){
			SearchOEText.setTextColor(Color.WHITE);
			SearchOEDesc.setTextColor(Color.WHITE);
		}
		else {
			SearchOEText.setTextColor(Color.DKGRAY);
			SearchOEDesc.setTextColor(Color.DKGRAY);
		}
		return convertView;
	}

}
