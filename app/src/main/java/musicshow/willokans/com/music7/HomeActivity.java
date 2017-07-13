package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

import data.customListViewAdaptor;
import model.Event;

public class HomeActivity extends AppCompatActivity {

    private Button hSignOut;

    private customListViewAdaptor adapter;
    private ArrayList<Event> events = new ArrayList<>();
    private String urlLeft = "http://api.eventful.com/json/events/search?...&q=music&q=band&where=";
    private String apiKey = "&app_key=Jztd9tB4rN22MnHB";
    private ListView listView;
    private TextView selectedCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hSignOut = (Button) findViewById(R.id.hSignOut);



        getEvent("chicago");



    }


    /**
     * mehtod to get event based on the city
     * @Param: city
     */
    private void getEvent (String city) {

        //clear data first
        events.clear();

        //append both url left and right with the city parameter
        String finalUrl = urlLeft+city+ apiKey;


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
                    for (int i =0; i < eventsArray.length(); i++) {

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
                        String venueTest = jsonObject.getString("image");
                        if (venueTest == "null") {
                            System.out.println("No Image");

                        } else {
                            JSONObject imageObject = jsonObject.getJSONObject("image");
                            JSONObject imageSize = imageObject.getJSONObject("medium");

                            //get image
                            String imageUrl = imageSize.getString("url");
                            Log.v("Image: ", imageUrl);
                        }



                    }

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
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Search option selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_exit:
                //Write own logic
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                return true;
            case R.id.action_setting:
                Toast.makeText(getApplicationContext(), "setting option selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }



}
