package com.example.dramidi.dramidi4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dramidi on 4/5/2017.
 */

public class DrawingShape extends Fragment {
    Button draw_button,clear_button;
    EditText txtHeight,txtwidth,txtText,txtRadius, txtRadius1;
    TextView txtContent,txtWidthContent;
    private SoundPool soundPool;
    private SparseIntArray soundMap;

    Context context;
    private ArrayList<ImageView> allPetals;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    Flower myFlower;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_draw, container, false);
        myFlower = new Flower();
        allPetals = new ArrayList<ImageView>();

        //INITIALIZE THE GENERATION OF THE FIBONACCI ARTWORK
        initialize();
        configureSounds();
     context=getContext();
        //CREATE A LAYOUT INFLATER TO ADD PETALS TO RELATIVE LAYOUT
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.drawLayout);


        //SET THE CENTER COORDINATE
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myFlower.set_xCenter(metrics.widthPixels / 2 - 100);
        myFlower.set_yCenter(metrics.heightPixels / 2);

        draw_button =(Button) view.findViewById(R.id.drawbtn);
        clear_button =(Button) view.findViewById(R.id.clear);
        txtHeight =(EditText)view.findViewById(R.id.txtHeight);
        txtwidth =(EditText)view.findViewById(R.id.txtWidth);
        txtRadius =(EditText) view.findViewById(R.id.txtRadius);
        txtRadius1 =(EditText) view.findViewById(R.id.txtRadius1);
        txtText =(EditText) view.findViewById(R.id.txtText);
        txtContent =(TextView) view.findViewById(R.id.textContent);
        txtWidthContent =(TextView) view.findViewById(R.id.txtWidthContent);

        draw_button.setOnClickListener(addPetal);
        clear_button.setOnClickListener(clearPetals);

        return view;
    }
    private void initialize(){
        //TASK 1: INITIALIZE THE SETTINGS FOR THE FIRST PETAL

        myFlower.setRotate(0);
        myFlower.setScaleX((float) .3);
        myFlower.setScaleY((float) .3);
        myFlower.setDegenerate((float) 1.001);
        myFlower.initializeAngle();

    }
    private void configureSounds() {

        // CONFIGURE THE SOUNDS USE IN THE JUKEBOX
        // PRE-LOAD THE FIRST FOUR SOUNDS
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundMap = new SparseIntArray(4);
        soundMap.put(1, soundPool.load(this.getContext(), R.raw.bell_clang, 1));
        soundMap.put(2, soundPool.load(this.getContext(), R.raw.funky_gong, 1));
        soundMap.put(3, soundPool.load(this.getContext(), R.raw.spooky_cry, 1));
        soundMap.put(4, soundPool.load(this.getContext(), R.raw.random_ha, 1));

        // FIFTH SOUND WILL BE PLAYED IN MEDIA PLAYER


    }
    private View.OnClickListener clearPetals = new View.OnClickListener() {
        public void onClick(View view) {
            //TASK 1: REMOVE ALL PETAL IMAGE VIEW FROM THE LAYOUT
            for (int i = 0; i < allPetals.size(); i++) {
                ImageView petal = allPetals.get(i);
                relativeLayout.removeView(petal);
            }

            //TASK 2: RESET ALL VARIABLES
            allPetals.clear();
            initialize();
        }
    };
    private View.OnClickListener addPetal = new View.OnClickListener() {
        public void onClick(View view) {


            String empty = "";
            String check,check1,text = "Default",color;
            int Height=10,Width=5,radius=10,radius1=10;
            check = ((EditText) ((MainActivity) getActivity()).viewPager.findViewById(R.id.txtHeight)).getText().toString();
            if (!check.equals(empty))
            {
                Height = Integer.parseInt(check);
            }

            check1 = ((EditText) ((MainActivity) getActivity()).viewPager.findViewById(R.id.txtWidth)).getText().toString();
            if (!check1.equals(empty))
            {
                Width = Integer.parseInt(check1);
            }
            check =((EditText) ((MainActivity) getActivity()).viewPager.findViewById(R.id.txtRadius)).getText().toString();
            if (!check.equals(empty))
            {
                 radius = Integer.parseInt(check);
            }
            check =((EditText) ((MainActivity) getActivity()).viewPager.findViewById(R.id.txtRadius1)).getText().toString();
            if (!check.equals(empty))
            {
                radius1 = Integer.parseInt(check);
            }
            check =((EditText) ((MainActivity) getActivity()).viewPager.findViewById(R.id.txtText)).getText().toString();
            if (!check.equals(empty))
            {
                text = check;
            }
            color=((Spinner) ((MainActivity) getActivity()).viewPager.findViewById(R.id.colorSpinner)).getSelectedItem().toString();


            String shape = ((Button) getActivity().findViewById(R.id.drawbtn)).getText().toString();
            ImageView petal;
            Bitmap b;
            petal = (ImageView) layoutInflater.inflate(R.layout.petal_button, null);


              b = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(b);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            if(color.equals("Red"))
            {
                paint.setColor(Color.RED);
            }
            else if (color.equals("Blue"))
            {
                paint.setColor(Color.BLUE);
            }
            else if (color.equals("Green"))
            {
                paint.setColor(Color.GREEN);
            }
            else if (color.equals("Yellow"))
            {
                paint.setColor(Color.YELLOW);
            }
            else
            {
                paint.setColor(Color.BLACK);
            }
            if(shape.equals("Rectangle")) {
                soundPool.play(1, 1, 1, 1, 0, 1.0f);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(10);
                c.drawRect(0, 0, Height*25, Width*25, paint);
            }
            else if (shape.equals("Circle"))
            {
                soundPool.play(2, 1, 1, 1, 0, 1.0f);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(10);
                c.drawCircle(radius*25, radius*25, radius*25, paint);
            }
            else if (shape.equals("Oval"))
            {
                soundPool.play(3, 1, 1, 1, 0, 1.0f);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(10);
                RectF oval3 = new RectF(0, 0, radius*25, radius1*25);
                c.drawOval(oval3, paint);
            }
            else if (shape.equals("Text"))
            {
                soundPool.play(4, 1, 1, 1, 0, 1.0f);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(10);
                paint.setTextSize(200);
                c.drawText(text, 0, 200, paint);

            }

            petal.setImageBitmap(b);
            petal.setX(myFlower.get_xCenter());
            petal.setY(myFlower.get_yCenter());
            petal.setPivotY(0);
            petal.setPivotX(100);
            petal.setScaleX(myFlower.getScaleX());
            petal.setScaleY(myFlower.getScaleY());
            petal.setRotation(myFlower.getRotate());

            //TASK 3: PLACE THE INFLATED IMAGEVIEW IN THE MAIN LAYOUT
            relativeLayout.addView(petal, 0);

            //TASK 4: ADD THE IMAGEVIEW OF THE PETAL TO THE ARRAYLIST
            allPetals.add(petal);

            //TASK 5: UPDATE THE ANGLE AND SCALE FOR THE NEXT PETAL TO BE ADDED
            myFlower.updatePetalValues();

        }
    };
}
