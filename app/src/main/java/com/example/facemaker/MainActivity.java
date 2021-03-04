package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * MainActivity
 * Sets everything up upon lauching the app
 *
 * @author: Caden Deutscher
 * @version: 03/03/2021
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Face
        Face theFace = findViewById(R.id.face);

        //Seekbars
        SeekBar redSeekbar = findViewById(R.id.redSeekbar);
        SeekBar blueSeekbar = findViewById(R.id.blueSeekbar);
        SeekBar greenSeekbar = findViewById(R.id.greenSeekbar);

        //Spinner
        Spinner hairSelect = findViewById(R.id.hairSelect);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.face_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSelect.setAdapter(adapter);

        //Controller
        FaceController theController = new FaceController(theFace,redSeekbar,blueSeekbar, greenSeekbar, hairSelect);

        //SpinnerListener
        hairSelect.setOnItemSelectedListener(theController);

        //Seekbar listeners
        redSeekbar.setOnSeekBarChangeListener(theController);
        blueSeekbar.setOnSeekBarChangeListener(theController);
        greenSeekbar.setOnSeekBarChangeListener(theController);

        //Radio Group
        RadioGroup include = findViewById(R.id.includeFace);
        include.setOnCheckedChangeListener(theController);

        //Button
        Button random = findViewById(R.id.random);
        random.setOnClickListener(theController);

    }


}