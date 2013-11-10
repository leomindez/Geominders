package com.bldev.geominders.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bldev.geominders.R;

public class NDAdapter extends BaseAdapter {
	private Activity mActivity;
	private int[] icons;
	private String[] actionsNames;

	public NDAdapter(Activity activity, int[] icons, String[] actionsNames) {
		this.mActivity = activity;
		this.icons = icons;
		this.actionsNames = actionsNames;
	}

	@Override
	public int getCount() {
		return icons.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View container = convertView;
		ViewHolder holder = null;

		if (container == null) {
			LayoutInflater inflater = mActivity.getLayoutInflater();
			container = inflater.inflate(R.layout.activity_list_row, parent,
					false);
			holder = new ViewHolder(container);
			container.setTag(holder);

		} else {
			holder = (ViewHolder) container.getTag();
		}

		holder.populateFrom(icons[position], actionsNames[position]);
		return container;
	}

	private class ViewHolder {
		private ImageView pictureView;
		private TextView userName;

		public ViewHolder(View row) {
			pictureView = (ImageView) row
					.findViewById(R.id.drawer_activity_icon);
			userName = (TextView) row.findViewById(R.id.drawer_activity);

		}

		public void populateFrom(int icon, String name) {
			pictureView.setImageResource(icon);
			userName.setText(name);
		}

	}

}
