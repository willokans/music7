package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String urlLeft = "http://api.eventful.com/rest/events/search?...&q=music&q=musical&q=jazz&q=concert&q=rock%20music&where=";
    private String apiKey = "&app_key=Jztd9tB4rN22MnHB";
    private ListView listView;
    private TextView selectedCity;

    //api url string
    private String url = "http://api.eventful.com/json/events/search?...&q=music&where=Dublin&app_key=Jztd9tB4rN22MnHB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hSignOut = (Button) findViewById(R.id.hSignOut);



        getEvent("Lagos");







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
                    System.out.println("got eventsObject");

                    //get the event array from the events object
                    JSONArray eventsArray = eventsObject.getJSONArray("event");
                    System.out.println("got eventsArray");

                    //loop throug array to get information
                    for (int i =0; i < eventsArray.length(); i++) {

                        //create another Json object inside the the eventArray
                        JSONObject jsonObject = eventsArray.getJSONObject(i);
                        System.out.println("got jsonObject");

                        //Get artist object
                        JSONObject artistObject = jsonObject.getJSONObject("title");
                        System.out.println("got artistobject");
                        String headlinerText = artistObject.getString("title");
                        System.out.println("HeadLiner: " + headlinerText);
                    }

                    System.out.println("EVENTS " + eventsObject);
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
