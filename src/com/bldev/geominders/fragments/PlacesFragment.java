package com.bldev.geominders.fragments;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bldev.geominders.R;
import com.bldev.geominders.util.Places;

public class PlacesFragment extends Fragment {

	private ViewGroup placeContainer;
	private ArrayList<Places> places;
	private TextView message;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		placeContainer = (ViewGroup) inflater.inflate(R.layout.profile_layout,
				container, false);

		return placeContainer;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		places = new ArrayList<Places>();
		message = new TextView(getActivity());
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (places.size() == 0) {
			message.setText("Agrega nuevos lugares");
			message.setGravity(Gravity.CENTER);
			
			message.setTypeface(Typeface.DEFAULT_BOLD);
			message.setTextSize(30);
			message.setTextColor(Color.rgb(109, 176, 30));
			placeContainer.addView(message);
		}

	}
}
