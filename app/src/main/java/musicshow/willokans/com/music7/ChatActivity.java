package musicshow.willokans.com.music7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
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
            case R.id.action_exit:
                //Write own logic
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ChatActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
