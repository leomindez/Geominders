package com.bldev.geominders;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ActionsActivity extends FragmentActivity implements
		OnMapClickListener {

	private ImageView actionIcon;
	private TextView actionName;
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actions);

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.rgb(109, 176, 30)));
		actionIcon = (ImageView) findViewById(R.id.action_image);
		actionName = (TextView) findViewById(R.id.action_name);

		googleMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.action_map)).getMap();

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			actionIcon.setImageResource(bundle.getInt("image_resources"));
			actionName.setText(bundle.getString("action_name"));
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actions, menu);
		return true;
	}

	@Override
	public void onMapClick(LatLng point) {
		
		
	}

}
