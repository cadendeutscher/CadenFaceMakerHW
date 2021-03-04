package com.example.facemaker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * FaceController
 * Listens for changes in buttons, and implements those changes
 *
 * @author: Caden Deutscher
 * @version: 03/03/2021
 */
public class FaceController implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    //Variables
    private Face face;
    private FaceModel faceInfo;
    private SeekBar redS;
    private SeekBar greenS;
    private SeekBar blueS;
    private Spinner hs;

    //Constructor
    public FaceController(Face theFace, SeekBar red, SeekBar blue, SeekBar green, Spinner HStyle){
        //Set spinner
        hs = HStyle;

        //Set Seek Bars
        redS = red;
        greenS = green;
        blueS = blue;

        //Add View and Model
        face = theFace;
        faceInfo = face.getFaceModel();

        //Set original SeekBar Progress
        redS.setProgress(faceInfo.faceRed);
        greenS.setProgress(faceInfo.faceGreen);
        blueS.setProgress(faceInfo.faceBlue);

        //Set Spinner to right hair style
        hs.setSelection(faceInfo.hairType,true);

    }

    /**
     *SeekBars
     * Change the color of the selected item in the radio button
     * based on the seekbars
     */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(fromUser){
            //Set Colors to the right values based on the seek bar change
            switch(seekBar.getId()){
                case R.id.redSeekbar:
                    if(faceInfo.selected == 0) {
                        faceInfo.faceRed = progress;
                    }
                    else if(faceInfo.selected == 1) {
                        faceInfo.skinRed = progress;
                    }
                    else if(faceInfo.selected == 2) {
                        faceInfo.hairRed = progress;
                    }
                    break;

                case R.id.greenSeekbar:
                    if(faceInfo.selected == 0) {
                        faceInfo.faceGreen = progress;
                    }
                    else if(faceInfo.selected == 1) {
                        faceInfo.skinGreen = progress;
                    }
                    else if(faceInfo.selected == 2) {
                        faceInfo.hairGreen = progress;
                    }
                    break;

                case R.id.blueSeekbar:
                    if(faceInfo.selected == 0) {
                        faceInfo.faceBlue = progress;
                    }
                    else if(faceInfo.selected == 1) {
                        faceInfo.skinBlue = progress;
                    }
                    else if(faceInfo.selected == 2) {
                        faceInfo.hairBlue = progress;
                    }
                    break;
            }
            //Redraw the face
            face.invalidate();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}

    /**
     * Button
     * What to do when the Random Face button is clicked
     */
    public void onClick(View v) {

        switch(v.getId()){

            //Make sure random button is clicked
            case R.id.random:

                //Randomize face
                face.randomize();

                //set progress bars
                switch (faceInfo.selected){
                    case 0:
                        redS.setProgress(faceInfo.faceRed);
                        greenS.setProgress(faceInfo.faceGreen);
                        blueS.setProgress(faceInfo.faceBlue);
                        break;

                    case 1:
                        redS.setProgress(faceInfo.skinRed);
                        greenS.setProgress(faceInfo.skinGreen);
                        blueS.setProgress(faceInfo.skinBlue);
                        break;

                    case 2:
                        redS.setProgress(faceInfo.hairRed);
                        greenS.setProgress(faceInfo.hairGreen);
                        blueS.setProgress(faceInfo.hairBlue);
                        break;
                }
                //Set spinner to the right hairtype
                hs.setSelection(faceInfo.hairType,true);

                face.invalidate();
                break;
        }
    }

    /**
     * Radio Buttons
     * Reflect the change in the radio group to allow the user to mess with the
     * appropiate face item
     */
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //based on the radio button checked make hte change in FaceModel
        //Also change the progress bars to the right values
        switch (group.getCheckedRadioButtonId()){

           case R.id.Eyes:
               faceInfo.selected = 0;
               redS.setProgress(faceInfo.faceRed);
               greenS.setProgress(faceInfo.faceGreen);
               blueS.setProgress(faceInfo.faceBlue);
               break;

           case R.id.Skin:
               faceInfo.selected = 1;
               redS.setProgress(faceInfo.skinRed);
               greenS.setProgress(faceInfo.skinGreen);
               blueS.setProgress(faceInfo.skinBlue);
               break;

           case R.id.Hair:
               faceInfo.selected = 2;
               redS.setProgress(faceInfo.hairRed);
               greenS.setProgress(faceInfo.hairGreen);
               blueS.setProgress(faceInfo.hairBlue);
               break;
       }

       face.invalidate();
    }

    /**
     * Spinner
     *Reflect the change in the spinner to show the appropriate hair style
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selected = parent.getSelectedItem().toString();
        //Change faceInfo to reflect the Hair Syle selected
        switch (selected){

            case "Hair 1":
                faceInfo.hairType = 0;
                break;

            case "Hair 2":
                faceInfo.hairType = 1;
                break;

            case "Hair 3":
                faceInfo.hairType = 2;
                break;
        }

        face.invalidate();
    }

    public void onNothingSelected(AdapterView<?> parent) {}


}
