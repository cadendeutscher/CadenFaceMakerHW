package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Face
 * Creates a face, randomizes the characteristics and draws it on a canvas
 *
 * @author: Caden Deutscher
 * @version: 03/03/2021
 */
public class Face extends SurfaceView {

    //Variables
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    private Paint paintFace;
    private Paint white;
    private Paint colorEye;
    private Paint black;
    private FaceModel faceInfo;
    private Paint hairC;
    private Paint skinC;
    private float EyeCY;
    private float lEyeCX;
    private float rEyeCX;
    private float mouthAngle;

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        //Initialize variables
        paintFace = new Paint();
        white = new Paint();
        colorEye = new Paint();
        black = new Paint();
        faceInfo = new FaceModel();
        skinC = new Paint();
        hairC = new Paint();
        EyeCY = 700f;
        lEyeCX = 450f;
        rEyeCX = 750f;
        mouthAngle = 180f;
        
        

        //Randomizes face
        randomize();

    }

    /**
     * randomize
     * Randomize all the variables associated with the face
     */
    public void randomize(){

        //Randomize each hair, skin, and eye variable
        faceInfo.faceBlue = (int)(Math.random()*255);
        faceInfo.faceRed = (int)(Math.random()*255);
        faceInfo.faceGreen = (int)(Math.random()*255);
        faceInfo.hairBlue = (int)(Math.random()*255);
        faceInfo.hairRed = (int)(Math.random()*255);
        faceInfo.hairGreen = (int)(Math.random()*255);
        faceInfo.skinBlue = (int)(Math.random()*255);
        faceInfo.skinRed = (int)(Math.random()*255);
        faceInfo.skinGreen = (int)(Math.random()*255);
        faceInfo.hairType = (int)(Math.random()*3);

    }

    /**
     * drawFace
     * Creates eyes, face, and mouth
     */
    public void drawFace(Canvas canvas){
        //turn the individual reds, greens, and blues, into an rgb int
        hairColor= Color.rgb(faceInfo.hairRed,faceInfo.hairGreen,faceInfo.hairBlue);
        skinColor= Color.rgb(faceInfo.skinRed,faceInfo.skinGreen,faceInfo.skinBlue);
        eyeColor = Color.rgb(faceInfo.faceRed,faceInfo.faceGreen,faceInfo.faceBlue);
        hairStyle = faceInfo.hairType;

        //Set colors
        paintFace.setColor(0xffff0000);
        white.setColor(0xffffffff);
        colorEye.setColor(eyeColor);
        skinC.setColor(skinColor);
        hairC.setColor(hairColor);
        black.setColor(0xff000000);

        /**Head**/
        canvas.drawOval(lEyeCX - 250,EyeCY - 300,rEyeCX + 250,EyeCY + 500,skinC);

        /**EYE**/
        //Eye whites
        canvas.drawCircle(lEyeCX,EyeCY,50,white);
        canvas.drawCircle(rEyeCX,EyeCY,50,white);

        //Colored part of eye
        canvas.drawCircle(lEyeCX,EyeCY,30,colorEye);
        canvas.drawCircle(rEyeCX,EyeCY,30,colorEye);

        //Pupil
        canvas.drawCircle(lEyeCX,EyeCY,15,black);
        canvas.drawCircle(rEyeCX,EyeCY,15,black);



        /**Mouth**/
        canvas.drawArc(lEyeCX - 75,EyeCY + 150,rEyeCX+75,EyeCY+325,mouthAngle,-mouthAngle,false,black);
        canvas.drawArc(lEyeCX - 50,EyeCY + 200,rEyeCX + 50,EyeCY+300,mouthAngle,-mouthAngle,false,white);

    }

    /**
     * drawHair
     * draws the hair for the head
     */
    private void drawHair(Canvas canvas){

        //instance variables
        int cx = (int)(rEyeCX-150);
        int cy = (int)(EyeCY+100);
        int r = 400;
        int a = 0;
        int hairX = (int)(cx + r * Math.cos(a));
        int hairY = (int)(cy + r * Math.sin(a));
        int hairX2 = (int)(cx + (r+4) * Math.cos(a));
        int hairY2 = (int)(cy + (r+4) * Math.sin(a));

        /*
        The hair styles were adapted from my eye lash functions in HW1
         */

        //HairStyle 1
        //Draw hair around the head
        if(hairStyle == 0) {
            for (double i = Math.PI; i < 2 * Math.PI; i += 0.01) {
                hairX = (int) (cx + r * Math.cos(i));
                hairY = (int) (cy + r * Math.sin(i));
                hairX2 = (int) (cx + (r + 100 * Math.random()) * Math.cos(i));
                hairY2 = (int) (cy + (r + 100) * Math.sin(i));
                canvas.drawLine(hairX, hairY, hairX2, hairY2, hairC);
            }
        }

        //Hair Style 2
        //Draw hair around the head
        else if(hairStyle == 1) {
            for (double i = Math.PI + 1; i < 2 * Math.PI - 1; i += 0.01) {
                hairX = (int) (cx + r * Math.cos(i));
                hairY = (int) (cy + r * Math.sin(i));
                hairX2 = (int) (cx + (r + 200 * Math.random()) * Math.cos(i));
                hairY2 = (int) (cy + (r + 50 * i) * Math.sin(i));
                canvas.drawLine(hairX, hairY, hairX2, hairY2, hairC);
            }
        }

        //Hair Style 3
        //Draw hair around the head
        else if(hairStyle == 2) {
            for (double i = Math.PI + 1; i < 2 * Math.PI - 1; i += 0.01) {
                hairX = (int) (cx + r * Math.cos(i));
                hairY = (int) (cy + r * Math.sin(i));
                hairX2 = (int) (cx + (r + 100 * Math.random()) * Math.cos(i));
                hairY2 = (int) (cy + (r - 100) * Math.sin(i));
                canvas.drawLine(hairX, hairY, hairX2, hairY2, hairC);

            }
        }

    }

    /**
     * Return the faceInfo
     */
    public FaceModel getFaceModel() {

    return faceInfo;

    }

    /**
     * Draw the face on the canvas
     */
    public void onDraw(Canvas canvas){

     drawFace(canvas);
     drawHair(canvas);

    }


}
