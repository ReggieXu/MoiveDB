package edu.udayton.moivedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.udayton.moivedb.moviefragment.InfoFragment;

public class movieinfo extends AppCompatActivity {

    public static final String NAME_KEY = "NAME_ID";
    public static final String REVIVE_KEY = "REVIVE_ID";
    public static final String IMAGE_KEY = "IMAGE_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieinfo);

        Intent intent = getIntent();

        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (intent.getExtras() != null) {

            String name = intent.getExtras().getString(NAME_KEY);
            String revive = intent.getExtras().getString(REVIVE_KEY);
            String imagePath = intent.getExtras().getString(IMAGE_KEY);

            Bundle bundle = new Bundle();
            bundle.putString("NAME", name);
            bundle.putString("REVIVE", revive);
            bundle.putString("IMAGEPATH", imagePath);

            InfoFragment infoFragment = new InfoFragment();
            infoFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.Infofragment, infoFragment).commit();

        }

    }
}
