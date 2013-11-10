package com.bldev.geominders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bldev.geominders.view.NDAdapter;

public class PlacesActivity extends FragmentActivity {

	private DrawerLayout drawerLayout;
	private ListView listView;
	private NDAdapter adapter;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private FragmentManager fragmentManager;
	private static String titleFragment = "";
	private int[] icons = { R.drawable.profile, R.drawable.friends,
			R.drawable.favs, R.drawable.settings };
	private String[] actionsNames = { "Profile", "Friends", "Favs", "Settings" };

	private String[] fragmentClasses = {

	"com.bldev.geominders.fragments.PlacesFragment",
			"com.bldev.geominders.fragments.FriendFragment",
			"com.bldev.geominders.fragments.FavFragment",
			"com.bldev.geominders.fragments.SettingFragment" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places);

		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.rgb(109, 176, 30)));
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		listView = (ListView) findViewById(R.id.left_drawer);

		fragmentManager = getSupportFragmentManager();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		adapter = new NDAdapter(PlacesActivity.this, icons, actionsNames,
				Color.WHITE);
		listView.setAdapter(adapter);

		actionBarDrawerToggle = new ActionBarDrawerToggle(PlacesActivity.this,
				drawerLayout, R.drawable.ic_drawer, R.string.action_settings,
				R.string.app_name) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(getTitle());
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);

				getActionBar().setTitle(titleFragment);
			}

		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				drawerLayout
						.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
							@Override
							public void onDrawerClosed(View drawerView) {
								super.onDrawerClosed(drawerView);
								FragmentTransaction fragmentTransaction = fragmentManager
										.beginTransaction();
								fragmentTransaction.replace(R.id.content_frame,
										Fragment.instantiate(
												PlacesActivity.this,
												fragmentClasses[arg2]));
								titleFragment = fragmentClasses[arg2];
								fragmentTransaction.commit();
							}
						});
				drawerLayout.closeDrawer(listView);
			}
		});
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.content_frame,
				Fragment.instantiate(PlacesActivity.this, fragmentClasses[0]));
		fragmentTransaction.commit();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		actionBarDrawerToggle.syncState();
	}

}
