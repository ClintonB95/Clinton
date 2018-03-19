package com.example.robpercival.jsondemo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class InfoDisplay extends Activity {
    /*
        Clinton Bates
        Student Code 15703
         */
    public String LatTranfer;
    public String LngTranfer;
    public String StationTranfer;
    public  String[] myStrings = new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();


        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Station Name : " + intent.getStringExtra("Stat")
                + System.lineSeparator() + "Address : " + intent.getStringExtra("Add")
                + System.lineSeparator() + "Status : " + intent.getStringExtra("stat")
                + System.lineSeparator() + "Bikes avaible : " + intent.getStringExtra("Bike")
                + System.lineSeparator() + "Stands avaible : " + intent.getStringExtra("Stands")
                + System.lineSeparator() + "latitude avaible : " + intent.getStringExtra("Lat")
                + System.lineSeparator() + "Longitude avaible : " + intent.getStringExtra("Lng"));





        //sending it on to the map
        LatTranfer = intent.getStringExtra("Lat");
        LngTranfer = intent.getStringExtra("Lng");
        StationTranfer = intent.getStringExtra("Stat");


    }


    public void onclick2(View veiw) {
        Intent intent1 = new Intent(getApplicationContext(), AllLocationsMap.class);
        //getting data from pervious page
        Bundle b=this.getIntent().getExtras();
        String[] array=b.getStringArray("Lat i");
        String[] array2=b.getStringArray("Lng i");
        String[] array3=b.getStringArray("Name");
        //checking it came
        for (int i = 0; i < 100; i++) {
            Log.i("fkeo", array[i]);
            Log.i("fkeo", array2[i]);
            Log.i("fkeo", array3[i]);
        }
        intent1.putExtras(b);
        startActivity(intent1);

    }

    public void onclick(View veiw) {
        Intent intent = new Intent(getApplicationContext(), SingleLocationMap.class);
        Log.i("Tester", LatTranfer);
        Log.i("Tester", LngTranfer);
        intent.putExtra("Lng", String.valueOf(LngTranfer));
        intent.putExtra("Lat", String.valueOf(LatTranfer));
        intent.putExtra("StationTranfer", String.valueOf(StationTranfer));
        startActivity(intent);
    }

}





