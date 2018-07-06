package com.example.dramidi.dramidi4;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    PageAdaptor sectionsPagerAdapter;
    ViewPager viewPager;

    Button draw_button, clear_button;
    EditText txtHeight,txtwidth,txtText,txtRadius, txtRadius1;
    TextView txtContent,txtWidthContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sectionsPagerAdapter = new PageAdaptor(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);



        draw_button =(Button) findViewById(R.id.drawbtn);
        clear_button=(Button) findViewById(R.id.clear);

        txtHeight =(EditText) findViewById(R.id.txtHeight);
        txtwidth =(EditText) findViewById(R.id.txtWidth);
        txtRadius =(EditText) findViewById(R.id.txtRadius);
        txtRadius1 =(EditText) findViewById(R.id.txtRadius1);
        txtText =(EditText) findViewById(R.id.txtText);
        txtContent =(TextView) findViewById(R.id.textContent);
        txtWidthContent =(TextView) findViewById(R.id.txtWidthContent);



        ((GlobalVariables) this.getApplication()).setShape("Rectangle");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        draw_button = (Button)this.findViewById(R.id.drawbtn);
        txtHeight =(EditText) findViewById(R.id.txtHeight);
        txtwidth =(EditText) findViewById(R.id.txtWidth);
        txtRadius =(EditText) findViewById(R.id.txtRadius);
        txtText =(EditText) findViewById(R.id.txtText);
        txtContent =(TextView) findViewById(R.id.textContent);
        txtWidthContent =(TextView) findViewById(R.id.txtWidthContent);
        txtRadius1 =(EditText) findViewById(R.id.txtRadius1);
      String s="";
        switch (item.getItemId()) {

            case R.id.menu1:
                draw_button.setText("Rectangle");
                ((GlobalVariables) this.getApplication()).setShape("Rectangle");
                txtHeight.setVisibility(View.VISIBLE);
                txtwidth.setVisibility(View.VISIBLE);
                txtWidthContent.setVisibility(View.VISIBLE);
                txtContent.setText("Height");
                txtWidthContent.setText("Width");
                txtRadius.setVisibility(View.INVISIBLE);
                txtText.setVisibility(View.INVISIBLE);

                return true;

            case R.id.menu2:
                s = ((GlobalVariables) this.getApplication()).getShape();
                Log.d("Shape:", s);
                draw_button.setText("Circle");
                ((GlobalVariables) this.getApplication()).setShape("Circle");
                txtHeight.setVisibility(View.INVISIBLE);
                txtwidth.setVisibility(View.INVISIBLE);
                txtRadius1.setVisibility(View.INVISIBLE);
                txtText.setVisibility(View.INVISIBLE);

                txtRadius.setVisibility(View.VISIBLE);
                txtContent.setText("Radius");
                txtWidthContent.setVisibility(View.INVISIBLE);
                return true;

            case R.id.menu3:
                s = ((GlobalVariables) this.getApplication()).getShape();
                Log.d("Shape:", s);
                draw_button.setText("Oval");
                ((GlobalVariables) this.getApplication()).setShape("Oval");
                txtHeight.setVisibility(View.INVISIBLE);
                txtwidth.setVisibility(View.INVISIBLE);
                txtRadius1.setVisibility(View.VISIBLE);
                txtText.setVisibility(View.INVISIBLE);
                txtRadius.setVisibility(View.VISIBLE);
                txtContent.setText("Radius 1");
                txtWidthContent.setText("Radius 2");
                txtWidthContent.setVisibility(View.VISIBLE);
                return true;

            case R.id.menu4:
                s = ((GlobalVariables) this.getApplication()).getShape();
                Log.d("Shape:", s);
                draw_button.setText("Text");
                ((GlobalVariables) this.getApplication()).setShape("Text");
                txtHeight.setVisibility(View.INVISIBLE);
                txtwidth.setVisibility(View.INVISIBLE);
                txtRadius1.setVisibility(View.INVISIBLE);
                txtText.setVisibility(View.VISIBLE);
                txtRadius.setVisibility(View.INVISIBLE);
                txtContent.setText("Text");
                txtWidthContent.setVisibility(View.INVISIBLE);
                return true;


            default:
                draw_button.setText("Rectangle");
                ((GlobalVariables) this.getApplication()).setShape("Rectangle");
                txtHeight.setVisibility(View.VISIBLE);
                txtwidth.setVisibility(View.VISIBLE);
                txtWidthContent.setVisibility(View.VISIBLE);
                txtContent.setText("Height");
                txtWidthContent.setText("Width");
                txtRadius.setVisibility(View.INVISIBLE);
                txtText.setVisibility(View.INVISIBLE);
                return super.onOptionsItemSelected(item);
        }

    }
}