package com.pad.cristina.freely.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pad.cristina.freely.R;
import com.pad.cristina.freely.util.ApiRequestsUtil;
import com.pad.cristina.freely.view.DashboardActivity;
import com.pad.cristina.freely.view.LoginActivity;
import com.pad.cristina.freely.view.ProfileActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_event, container, false);
        final FloatingActionButton interested = rootView.findViewById(R.id.interested);
        final FloatingActionButton notInterested = rootView.findViewById(R.id.notInterested);

        setEventName(rootView);
        setActionInterested(interested, ApiRequestsUtil.REQUEST_TYPES.POST, "Added to favorites");
        setActionInterested(notInterested, ApiRequestsUtil.REQUEST_TYPES.DELETE, "Removed from favorites");

        return rootView;
    }

    private void setActionInterested(FloatingActionButton interested,final ApiRequestsUtil.REQUEST_TYPES post, final String s) {
        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String participant = ApiRequestsUtil.sendRequest(ApiRequestsUtil.BASE_URL + "/api/events/" + "5ce3ca0cd416687156d1ed6e" + "/participants", post, null);
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject ansJson = (JSONObject) parser.parse(participant);
                    String message;
                    message = (String) ansJson.get("message");
                    if (message != null) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setEventName(ViewGroup rootView) {
        String event = ApiRequestsUtil.sendRequest(ApiRequestsUtil.BASE_URL + "/api/events/"+"5ce3ca0cd416687156d1ed6e", ApiRequestsUtil.REQUEST_TYPES.GET, null);
        try {
            JSONParser parser = new JSONParser();
            JSONObject eventJson = (JSONObject) parser.parse(event);
            String name;
            name = (String) eventJson.get("name");
            final TextView eventName = rootView.findViewById(R.id.eventName);
            eventName.setText(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
