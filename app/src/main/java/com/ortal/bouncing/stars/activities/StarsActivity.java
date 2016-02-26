package com.ortal.bouncing.stars.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;

import com.ortal.bouncing.stars.R;
import com.ortal.bouncing.stars.customviews.Bounce;

public class StarsActivity extends ActionBarActivity {

    private static final String TAG = "StarsActivity:";
    private static final String EXTRA_SPEED = "speed";
    private static final String EXTRA_AMOUNT = "amount";
    private Resources resources;
    private SurfaceView view;
    private int mSpeed;
    private int mAmount;

    public static Intent createIntent(Context context, int speed, int amount) {
        Intent intent = new Intent(context, StarsActivity.class);
        intent.putExtra(EXTRA_SPEED, speed);
        intent.putExtra(EXTRA_AMOUNT, amount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        resources = getApplicationContext().getResources();

        if (savedInstanceState != null) {
            mSpeed = savedInstanceState.getInt(EXTRA_SPEED);
            mAmount = savedInstanceState.getInt(EXTRA_AMOUNT);
        } else {
            mSpeed = getIntent().getIntExtra(EXTRA_SPEED, 1);
            mAmount = getIntent().getIntExtra(EXTRA_AMOUNT, 1);
        }

        view = new Bounce(this, mSpeed, mAmount);
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        super.onCreate(savedInstanceState);
        setContentView(view.getRootView());
    } //onCreate

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(EXTRA_AMOUNT, mAmount);
        outState.putInt(EXTRA_SPEED, mSpeed);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } //onCreateOptionsMenu


}
