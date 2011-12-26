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
		OES = OEFetcher.getPopularOES();
		for(int i=0;i<OES.size();i++){
			boolean canObs = OEFetcher.canObserve(OES.get(i).getId());
			OES.get(i).setObservable(canObs);
		}
	}
	@Override
	public int getCount() {
		return OES.size();
	}

	@Override
	public Object getItem(int position) {
		return OES.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater	= LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.popoe_list_item,parent,false);
		}
		TextView PopularOEText = (TextView)convertView.findViewById(R.id.PopularOEName);
		PopularOEText.setText(OES.get(position).getName());
		if(OES.get(position).isObservable())PopularOEText.setTextColor(Color.WHITE);
		else PopularOEText.setTextColor(Color.RED);
		return convertView;
	}

}
