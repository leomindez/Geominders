package com.bldev.geominders.fragments;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bldev.geominders.PlacesActivity;
import com.bldev.geominders.R;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.parse.ParseFacebookUtils.Permissions;

public class LoginFragment extends Fragment implements Session.StatusCallback {

	private UiLifecycleHelper lifecycleHelper;
	private LoginButton loginButton;
	private static final List<String> PERMISSIONS = Arrays.asList("email",
			Permissions.Friends.ABOUT_ME);
	private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
	private boolean pendingPublishReauthorization = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup group = (ViewGroup) inflater.inflate(
				R.layout.layout_fragment_login, container, false);

		loginButton = (LoginButton) group.findViewById(R.id.login_button);
		// postButton = (Button) group.findViewById(R.id.button1);
		return group;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		lifecycleHelper = new UiLifecycleHelper(getActivity(), this);
		lifecycleHelper.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();

		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionState(session, session.getState(), null);

		}

		lifecycleHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		lifecycleHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		lifecycleHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			pendingPublishReauthorization = savedInstanceState.getBoolean(
					PENDING_PUBLISH_KEY, false);
		}
		loginButton.setFragment(this);
		// loginButton.setPublishPermissions(PERMISSIONS);
		loginButton.setReadPermissions(PERMISSIONS);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		lifecycleHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(PENDING_PUBLISH_KEY, pendingPublishReauthorization);
		lifecycleHelper.onSaveInstanceState(outState);
	}

	private void onSessionState(Session session, SessionState sessionState,
			Exception exception) {

		if (session == Session.getActiveSession() && session.isOpened()) {
			
			Intent intent = new Intent(getActivity(),PlacesActivity.class); 
			getActivity().startActivity(intent);
			getUserName(session);
			getActivity().finish();
		}else{
			
		}

		// if (sessionState.isOpened()) {
		// if (pendingPublishReauthorization
		// && sessionState.equals(SessionState.OPENED_TOKEN_UPDATED)) {
		// pendingPublishReauthorization = false;
		// publishStory();
		// }
		// // postButton.setVisibility(View.VISIBLE);
		//
		// Log.i("LoginFragment", "Log in...." + PERMISSIONS.toString() +
		// session.getPermissions());
		// } else if (sessionState.isClosed()) {
		// Log.i("LoginFragment", "Log out....");
		// // postButton.setVisibility(View.INVISIBLE);
		// }

	}

	@Override
	public void call(Session session, SessionState state, Exception exception) {
		onSessionState(session, state, exception);
	}

	
	private void getUserName(final Session session) {

		Request request = Request.newMeRequest(session,
				new Request.GraphUserCallback() {

					@Override
					public void onCompleted(GraphUser user, Response response) {
						if (session == Session.getActiveSession()) {
							if (user != null) {
								Log.d("TAG:USER_NAME", user.getFirstName());
								Log.d("TAG:USER_LAST_NAME", user.getLastName());
								

							}
						}

						if (response.getError() != null) {

						}

					}

				});
		request.executeAsync();
	}
}