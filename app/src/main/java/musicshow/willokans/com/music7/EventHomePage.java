package musicshow.willokans.com.music7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.firebase.auth.FirebaseAuth;

import model.Event;

public class EventHomePage extends AppCompatActivity {

    private Event event;

    //Set up textview variables
    private TextView venus;
    private TextView when;
    private TextView where;
    private TextView HeadLiners;

    //for image we cast it as NetworkImageView
    private NetworkImageView bandImage;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_page);

        //back arrow action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get serialized object from customListViewAdaptor class
        event = (Event) getIntent().getSerializableExtra("eventObj");

        //set up the views
        venus = (TextView) findViewById(R.id.detailVenue);
        when = (TextView) findViewById(R.id.detailWhen);
        where = (TextView) findViewById(R.id.detailWhere);
        HeadLiners = (TextView) findViewById(R.id.detailHeadliner);
        bandImage = (NetworkImageView) findViewById(R.id.detailBandImage);

        //populate views
        HeadLiners.setText("HeadLiner: " + event.getHeadLiner());
        venus.setText("Venue: " + event.getVenueName());
        when.setText("When: " + event.getStartData());
        where.setText("Where: " + event.getStreet() + ", " + event.getCity() + ", " + event.getCountry());
        bandImage.setImageUrl(event.getUrl(), imageLoader);




    }

    //over ride back row action bar with method
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate the menu: this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_activity_event_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Handle action bar item click here. The action bar will auto handle click
        //on the Home/up button, so long as you specify a parent activity in AndroidManifest.xml
        switch (item.getItemId()) {
            case R.id.action_websiteID:

                //get url from event class
                String url = event.getWebsite();

                if(!url.equals(" ")) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Website is  not available!", Toast.LENGTH_LONG).show();
                }

                return true;
            case R.id.action_exit:
                //Write own logic
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(EventHomePage.this, MainActivity.class));
                return true;
            case R.id.action_chat:
                //Write own logic
                startActivity(new Intent(EventHomePage.this, ChatActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
