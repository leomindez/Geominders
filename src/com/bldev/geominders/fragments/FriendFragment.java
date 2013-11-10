package com.bldev.geominders.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bldev.geominders.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FriendFragment extends Fragment implements OnMapClickListener {
	private ViewGroup group = null;
	private GoogleMap googleMap;
	private TextView locationPlace;
	private TextView reminderTo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (group != null) {
			ViewGroup parent = (ViewGroup) group.getParent();
			if (parent != null)
				parent.removeView(group);
		}
		try {
			group = (ViewGroup) inflater.inflate(
					R.layout.layout_fragment_friends, container, false);
			locationPlace = (TextView) group.findViewById(R.id.place_name_text);
			reminderTo = (TextView) group.findViewById(R.id.reminder_to);
		} catch (InflateException e) {
			e.printStackTrace();
		}

		return group;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.add_action, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.add_friend:
			createListDialog();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		googleMap = ((SupportMapFragment) getActivity()
				.getSupportFragmentManager()
				.findFragmentById(R.id.map_fragment)).getMap();

		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(37.3860500, -122.0838500));
		markerOptions.title("Happy Hacking!");
		markerOptions.snippet("San Francisco, See you soon!");

		googleMap.addMarker(markerOptions);

		googleMap.addCircle(new CircleOptions()
				.center(new LatLng(37.3860500, -122.0838500)).radius(1000)
				.strokeWidth(6).strokeColor(Color.BLUE));

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
				37.3860500, -122.0838500), 14));
		googleMap.setMyLocationEnabled(true);

		googleMap.setOnMapClickListener(this);
		locationPlace.setVisibility(View.VISIBLE);
		reminderTo.setVisibility(View.VISIBLE);
		locationPlace.setText(markerOptions.getSnippet() + " "
				+ markerOptions.getTitle());

	}

	private void createListDialog() {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				getActivity());
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Select One Name:-");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.select_dialog_singlechoice);
		arrayAdapter.add("Jose Luis");
		arrayAdapter.add("Memo");
		arrayAdapter.add("Luis");
		arrayAdapter.add("Armando");
		arrayAdapter.add("Oscar");
		builderSingle.setNegativeButton("cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderSingle.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						final String strName = arrayAdapter.getItem(which);
						AlertDialog.Builder builderInner = new AlertDialog.Builder(
								getActivity());
						builderInner
								.setMessage("The reminder will be sent to: "
										+ " " + strName);
						builderInner.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										reminderTo.setText("Send to: "
												+ strName);
										dialog.dismiss();
									}
								});
						builderInner.show();
					}
				});
		builderSingle.show();
	}

	@Override
	public void onMapClick(LatLng point) {

		googleMap.clear();
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(point);
		markerOptions.title("Happy Hacking!");
		markerOptions.snippet("San Francisco, See you soon!");

		googleMap.addMarker(markerOptions);

		googleMap.addCircle(new CircleOptions().center(point).radius(1000)
				.strokeWidth(7).strokeColor(Color.BLUE));

	}
}
