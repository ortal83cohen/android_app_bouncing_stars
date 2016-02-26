package com.ortal.bouncing.stars.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ortal.bouncing.stars.R;
import com.ortal.bouncing.stars.activities.StarsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    Button submitButton;
    EditText speed, amount;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        submitButton = (Button) view.findViewById(R.id.submit_button);
        speed = (EditText) view.findViewById(R.id.speed);
        amount = (EditText) view.findViewById(R.id.amount);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = StarsActivity.createIntent(getActivity(),
                        Integer.valueOf(speed.getText().toString()), Integer.valueOf(amount.getText().toString()));
                startActivity(myIntent);
            }
        });

        return view;
    }
}
