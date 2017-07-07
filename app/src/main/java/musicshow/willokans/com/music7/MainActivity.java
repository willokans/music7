package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //declare variables
    private Button mSignIn;
    private Button mRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect variable reference
        mSignIn = (Button) findViewById(R.id.mainSignInBtn);
        mRegister = (Button) findViewById(R.id.mainRegister);


        //Create onclick listener for mSigin buttons
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(s);
            }
        });

        //Create onclick listener for mSRegister buttons
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(s);
            }
        });
    }
}
