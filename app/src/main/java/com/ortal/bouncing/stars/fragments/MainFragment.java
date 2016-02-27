package com.ortal.bouncing.stars.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ortal.bouncing.stars.R;
import com.ortal.bouncing.stars.activities.StarsActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    Button submitButton;
    MaterialEditText speed, amount;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        submitButton = (Button) view.findViewById(R.id.submit_button);
        speed = (MaterialEditText) view.findViewById(R.id.speed);
        amount = (MaterialEditText) view.findViewById(R.id.amount);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speed.length() > 0 && amount.length() > 0 && speed.length() < 4 && amount.length() < 4) {
                    Intent myIntent = StarsActivity.createIntent(getActivity(),
                            Integer.valueOf(speed.getText().toString()), Integer.valueOf(amount.getText().toString()));
                    startActivity(myIntent);
                }
            }
        });

        return view;
    }
}
