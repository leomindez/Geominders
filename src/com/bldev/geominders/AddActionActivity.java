package com.bldev.geominders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bldev.geominders.view.NDAdapter;

public class AddActionActivity extends Activity {

	private int[] icons = { R.drawable.phone, R.drawable.sms, R.drawable.mail,
			R.drawable.facebook, R.drawable.twitter, R.drawable.foursquare,
			R.drawable.chrome, R.drawable.weather, R.drawable.text };
	private String[] names = { "Phonecall", "Message", "Mail", "Post", "Tweet",
			"Check-in", "Open URL", "Weather", "Text" };
	private NDAdapter adapter;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_action);
		

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.rgb(109, 176, 30)));
		listView = (ListView) findViewById(R.id.actions_container);
		adapter = new NDAdapter(AddActionActivity.this, icons, names,
				Color.BLACK);
		listView.setAdapter(adapter);

	}

	@Override
	protected void onResume() {
		super.onResume();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(AddActionActivity.this,
						ActionsActivity.class);
				intent.putExtra("image_resources", icons[arg2]);
				intent.putExtra("action_name", names[arg2]);
				startActivity(intent);
			}
		});

	}

}
