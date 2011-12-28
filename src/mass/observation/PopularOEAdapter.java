package mass.observation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopularOEAdapter extends BaseAdapter {
	Context context;
	ArrayList<ObservationEvent> OES;
	public PopularOEAdapter(Context c) throws JSONException, IOException{
		context = c;
		OES = OEFetcher.getOES("http://titan.cmpe.boun.edu.tr:8082/myServer/getPopularOE");
		//OES = OEFetcher.getOES("http://192.168.149.244:8080/myServer/getPopularOE");
	}
	public int getCount() {
		return OES.size();
	}

	public Object getItem(int position) {
		return OES.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater	= LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.popoe_list_item,parent,false);
		}
		TextView PopularOEText = (TextView)convertView.findViewById(R.id.PopularOEName);
		PopularOEText.setText(OES.get(position).getName());
		TextView PopularOEDesc = (TextView) convertView.findViewById(R.id.PopularOEDesc);
		PopularOEDesc.setText(OES.get(position).getDesc());
		if(OES.get(position).isObservable()){
			PopularOEText.setTextColor(Color.WHITE);
			PopularOEDesc.setTextColor(Color.WHITE);
		}
		else {
			PopularOEText.setTextColor(Color.DKGRAY);
			PopularOEDesc.setTextColor(Color.DKGRAY);
		}
		return convertView;
	}

}
