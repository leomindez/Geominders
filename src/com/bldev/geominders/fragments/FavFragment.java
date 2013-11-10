package com.bldev.geominders.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class FavFragment extends Fragment{
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Toast.makeText(getActivity(), this.getClass().getName(),
				Toast.LENGTH_SHORT).show();
	}
	

}
