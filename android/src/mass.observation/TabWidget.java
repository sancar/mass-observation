package mass.observation;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabWidget extends TabActivity {
	private String username;
	private String password;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.welcome);
	    getCredentials();
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, PopularOEActivity.class);
	    intent.putExtra("username", username);
	    intent.putExtra("password", password);
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("popoe").setIndicator("Popular",
	                      res.getDrawable(R.drawable.tab_photos))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, RecentOEActivity.class);
	    intent.putExtra("username", username);
	    intent.putExtra("password", password);
	    spec = tabHost.newTabSpec("recoe").setIndicator("Recent",
	                      res.getDrawable(R.drawable.tab_photos))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(1);
	}
	private void getCredentials() {
		username = getIntent().getExtras().getString("username");
	    password = getIntent().getExtras().getString("password");
	}
}
