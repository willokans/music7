package musicshow.willokans.com.music7;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Util.Preference;
import data.customListViewAdaptor;
import model.Event;

public class HomeActivity extends AppCompatActivity {

    private Button hSignOut;

    private customListViewAdaptor adapter;
    private ArrayList<Event> events = new ArrayList<>();
    private String urlLeft = "http://api.eventful.com/json/events/search?...&q=band&where=";
    private String apiKey = "&app_key=Jztd9tB4rN22MnHB";
    private ListView listView;
    private TextView selectedCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        listView = (ListView) findViewById(R.id.list);
        selectedCity = (TextView) findViewById(R.id.selectedLocationText);

//        instantiate our custom adaptor
        adapter = new customListViewAdaptor(HomeActivity.this, R.layout.list_row, events);
        listView.setAdapter(adapter);

        //instantiate Preference class
        Preference pref = new Preference(HomeActivity.this);
        String city = pref.getCity().toUpperCase();

        //set default city name
        selectedCity.setText(city);


        showEvents(city);


    }


    /**
     * mehtod to get event based on the city
     *
     * @Param: city
     */
    private void getEvent(String city) {

        //clear data first
        events.clear();

        //append both url left and right with the city parameter
        String finalUrl = urlLeft + city + apiKey;


        JsonObjectRequest eventRequest = new JsonObjectRequest(Request.Method.GET,
                finalUrl, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject eventsObject = response.getJSONObject("events");
                    Log.v("Data: ", eventsObject.toString());

                    //get event array
                    JSONArray eventsArray = eventsObject.getJSONArray("event");

                    //loop through array to get information
                    for (int i = 0; i < eventsArray.length(); i++) {

                        //create a json object to get info in each event array
                        JSONObject jsonObject = eventsArray.getJSONObject(i);

                        //get Artist
                        String HeadlinerText = jsonObject.getString("title");
                        Log.v("HeadlinerText: ", HeadlinerText);

                        //get Venue name
                        String venueName = jsonObject.getString("venue_name");
                        if (venueName == "null") {
                            Log.v("Venue Name: ", "TBC");

                        } else {
                            Log.v("Venue Name: ", venueName);
                        }


                        //get Venue location
                        String venueStreet = jsonObject.getString("venue_address");
                        Log.v("Street: ", venueStreet);
                        String venueCity = jsonObject.getString("city_name");
                        Log.v("City: ", venueCity);
                        String venueCountry = jsonObject.getString("country_abbr");
                        Log.v("Country: ", venueCountry);

                        //get start Data and time
                        String startDataAndTime = jsonObject.getString("start_time");
                        Log.v("Start Date and Time: ", startDataAndTime);

                        //get Website url
                        String website = jsonObject.getString("url");
                        if (website == "null") {
                            Log.v("website: ", "TBC");

                        } else {
                            Log.v("website: ", website);
                        }


                        //get url image
                        String imageUrl = " ";

                        String venueURL = jsonObject.getString("image");
                        if (venueURL == "null") {
                            System.out.println("No Image");

                        } else {
                            JSONObject imageObject = jsonObject.getJSONObject("image");
                            JSONObject imageSize = imageObject.getJSONObject("medium");

                            //get image
                            imageUrl = imageSize.getString("url");
                            Log.v("Image: ", imageUrl);
                        }



                        //instantiate our events object
                        Event event = new Event();

                        event.setHeadLiner(HeadlinerText);
                        event.setVenueName(venueName);
                        event.setStreet(venueStreet);
                        event.setCity(venueCity);
                        event.setCountry(venueCountry);
                        event.setUrl(imageUrl);
                        event.setWebsite(website);
                        event.setStartData(startDataAndTime);

                        //call our events array list to hold all out event objects
                        events.add(event);


                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(eventRequest);

    }


    //for Action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //add items into the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_changeLocationID:
                //call on the input dialog method
                showInputDialog();
                return true;
            case R.id.action_exit:
                //Write own logic
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                return true;
            case R.id.action_chat:
                //Write own logic
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    //Create an Alert dialog with will contain an editText where user can type in their city of choice
    private void showInputDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.MyDialogTheme);
        builder.setTitle("Change City");

        final EditText cityInput = new EditText(HomeActivity.this);
        //set input type to text
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        //set hint
        cityInput.setHint("dublin ...");
        //insert the input into the edit text
        builder.setView(cityInput);

        //set onclick listener for submit button
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //call our preference class
                Preference cityPreference = new Preference(HomeActivity.this);

                cityPreference.setCity(cityInput.getText().toString());
                String updatedCity = cityPreference.getCity();

                //showing updated city by user on the Selected city text view on the homeactivity
                String newCity = updatedCity.toUpperCase();
                selectedCity.setText(newCity);

                //re-render everything again
                showEvents(newCity);


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        //create alert dialog
        AlertDialog alertDialog = builder.create();

        //show the dialog alert
        alertDialog.show();



    }

    //call the get event method in this class and pass in the city
    private void showEvents(String city) {
        getEvent(city);

    }




}
