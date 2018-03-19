package com.example.robpercival.jsondemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Test extends Activity {


    /*
    Clinton Bates
    Student Code 15703
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       Test.DownloadTask task = new Test.DownloadTask();
        task.execute("https://api.jcdecaux.com/vls/v1/stations?contract=Dublin&apiKey=5bb43f341db582155668bdd1dd629688c8f76635");


    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        private String result = "";
        public String Pos;
        @Override
        protected String doInBackground(String... urls) {


            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("testing", result);


            try {
                //Creates a jason object.
                final JSONArray arr = new JSONArray(result);
                //Finds the List.
                ListView friendsListView = (ListView)findViewById(R.id.myList);
                //Creates an array list
                final  ArrayList<String> stations = new ArrayList<>();


                //Starts a for lopp that lenght of arr
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    //Searchs for Name in json object.
                    String stationName = jsonPart.getString("name");
                    //logs it
                    Log.i("stationName String" , stationName);
                    //Gets the status
                    String status = jsonPart.getString("status");
                    //get amout bikes avaible.
                    String AvaBikestand = jsonPart.getString("available_bike_stands");
                    //Add all this data to station.
                    stations.add(stationName  + System.lineSeparator() + "status: " + status  + System.lineSeparator() + "Bike Avaible: " + AvaBikestand);
                    //Testing to get lat.
                    JSONObject pos = jsonPart.getJSONObject("position");
                    Log.i("lat", pos.getString("lat"));

                }

                //Sets List View to use stations.
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Test.this, android.R.layout.simple_list_item_1, stations);
                friendsListView.setAdapter(arrayAdapter);

                //Creating String's to store the value.

                //creating a list for the values to be stored in.

                //Creating Arrays lists to store data.
                final  ArrayList<String> Station = new ArrayList<>();
                final  ArrayList<String> Address = new ArrayList<>();
                final  ArrayList<String> Status = new ArrayList<>();
                final  ArrayList<String> Bike_stands = new ArrayList<>();
                final  ArrayList<String> Bike_ava = new ArrayList<>();
                final  ArrayList<String> Bike_stands_ava = new ArrayList<>();
                final  ArrayList<String> Lat = new ArrayList<>();
                final  ArrayList<String> Lng = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    JSONObject jsonPart2 = arr.getJSONObject(i);

                    // Find the name in json data, For example name
                    String stationN = jsonPart.getString("name");

                    //saves it to my array List.
                    Station.add(stationN);

                    String address = jsonPart.getString("address");

                    Address.add(address);

                    String status = jsonPart2.getString("status");

                    Status.add(status);

                    String bike_stands  = jsonPart.getString("bike_stands");

                    Bike_stands.add(bike_stands);

                    String Bikeava  = jsonPart.getString("available_bike_stands");

                    Bike_ava.add(Bikeava);

                    String bike_stands_ava  = jsonPart.getString("available_bikes");

                    Bike_stands_ava.add(bike_stands_ava);

                    JSONObject pos = jsonPart.getJSONObject("position");

                    String lat = pos.getString("lat");

                    String lng = pos.getString("lng");

                    Lat.add(lat);
                    Lng.add(lng);

                }
                  // Dnn't delete this test
                  Log.i("43242j243o",String.valueOf(Station));
                 // Dnn't delete this test, not perfect programmer doing this to make sure it work
                  Log.i("43242j243o",String.valueOf(Station.get(3)));
//                final String finalStationN = stationN;
//                final String finalStationN1 = stationN;
                  final Intent intent = new Intent(getBaseContext(), InfoDisplay.class);
//                intent.putExtra("PersonID", finalStationN1);




                friendsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        Log.i("I clicked item number ", String.valueOf(position));
                        Pos = String.valueOf(position);
                        //test to make sure this works
                        Log.i("43242j243o", String.valueOf(Station.get(position)));
                        Log.i("Test Status", String.valueOf(Status.get(position)));
                        //Send the data across :)
                        intent.putExtra("Stat", String.valueOf(Station.get(position)));
                        Log.i("Test Status", String.valueOf(Status.get(position)));
                        intent.putExtra("Add", String.valueOf(Address.get(position)));
                        Log.i("Test Status", String.valueOf(Address.get(position)));
                        intent.putExtra("stat", String.valueOf(Status.get(position)));
                        Log.i("Test Status", String.valueOf(Status.get(position)));
                        intent.putExtra("Bike", String.valueOf(Bike_ava.get(position)));
                        Log.i("Test Status", String.valueOf(Bike_ava.get(position)));
                        intent.putExtra("Stands", String.valueOf(Bike_stands.get(position)));
                        Log.i("Test Status", String.valueOf(Bike_stands.get(position)));
                        intent.putExtra("Lng", String.valueOf(Lng.get(position)));
                        Log.i("Lat", String.valueOf(Lat.get(position)));
                        intent.putExtra("Lat", String.valueOf(Lat.get(position)));
                        Log.i("Lng", String.valueOf(Lng.get(position)));



                        //Storing lat in a string
                        Bundle b=new Bundle();
                        String[] myStrings = new String[100];
                        String[] myStrings2 = new String[100];
                        String[] myStrings3 = new String[100];
                        for (int i = 0; i < arr.length(); i++) {
                            myStrings[i] = String.valueOf(Lat.get(i));
                            myStrings2[i] = String.valueOf(Lng.get(i));
                            myStrings3[i] = String.valueOf(String.valueOf(Station.get(i)));
                            //testing to make sure it worked
                            Log.i("Test lat", myStrings[i]);
                        }
                        b.putStringArray("Lat i" , myStrings);
                        intent.putExtras(b);
                        b.putStringArray("Lng i" , myStrings2);
                        intent.putExtras(b);
                        b.putStringArray("Name" , myStrings3);
                        intent.putExtras(b);





                        startActivity(intent);
                    }



                });







            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}





