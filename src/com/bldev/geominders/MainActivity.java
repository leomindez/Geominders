package com.bldev.geominders;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class MainActivity extends FragmentActivity {
	private static String FACEBOOK_ID = "362466417232231";
	private static String PARSE_APP_ID = "aHHfMuvMzu8F0M67nWY2GDQZeb00XRaBzhf07zrK";
	private static String CLIENT_PARSE_APP_ID = "NIQ34nlSlh5PA7YzL5nnOT5vH1uzKuaZ58vskaOP";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Parse.initialize(MainActivity.this, PARSE_APP_ID, CLIENT_PARSE_APP_ID);
		ParseFacebookUtils.initialize(FACEBOOK_ID);
	}

	

}
