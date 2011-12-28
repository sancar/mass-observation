package mass.observation;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecentOEAdapter extends BaseAdapter {
	ArrayList<ObservationEvent> oe;
	Context context;
	public RecentOEAdapter(Context c ) throws JSONException, IOException{
		context = c;
		oe = OEFetcher.getOES("http://titan.cmpe.boun.edu.tr:8082/myServer/getRecentOE");
		//oe = OEFetcher.getOES("http://192.168.149.244:8080/myServer/getRecentOE");
	}
	public int getCount() {
		return oe.size();
	}

	public Object getItem(int position) {
		return oe.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater	= LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.recoe_list_item,parent,false);
		}
		TextView RecentOEText = (TextView)convertView.findViewById(R.id.RecentOEName);
		RecentOEText.setText(oe.get(position).getName());
		TextView RecentOEDesc = (TextView) convertView.findViewById(R.id.RecentOEDesc);
		RecentOEDesc.setText(oe.get(position).getDesc());
		if(oe.get(position).isObservable()){
			RecentOEText.setTextColor(Color.WHITE);
			RecentOEDesc.setTextColor(Color.WHITE);
		}
		else {
			RecentOEText.setTextColor(Color.DKGRAY);
			RecentOEDesc.setTextColor(Color.DKGRAY);
		}
		return convertView;
	}

}
