package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button hSignOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        hSignOut = (Button) findViewById(R.id.hSignOut);



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


    /**
     * http://api.eventful.com/rest/events/search?...&where=dublin&app_key=Jztd9tB4rN22MnHB&q=music
     *
     * http://api.eventful.com/rest/events/search?...&where=dublin&app_key=Jztd9tB4rN22MnHB&q=music
     *
     * http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=spain&api_key=6c550ca66ca5567cca149e8494edfbff&format=json
     */



}
