package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private Button lLoginbtn;
    private Button lBackbtn;
    private EditText lEmailField;
    private EditText lPaswField;

    //declare Auth variable
    private FirebaseAuth mAuth;

    //set up firebase Auth Listener
    private FirebaseAuth.AuthStateListener lAuthLister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        //connect variable reference
        lLoginbtn = (Button) findViewById(R.id.login_logIn_btn);
        lBackbtn = (Button) findViewById(R.id.reg_backBtn);
        lEmailField = (EditText) findViewById(R.id.login_email_Field);
        lPaswField = (EditText) findViewById(R.id.reg_password_field);

        //Create onclick listener for lBackbtn buttons
        lBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(s);
            }
        });

        //Create onclick listener for lLogin buttons
        lLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();
            }
        });

        //set reference for lAuth
        mAuth = FirebaseAuth.getInstance();

    }



    //Create on start method


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.getCurrentUser();

    }

    //func for signing in
    private void startSignIn() {
        String email = lEmailField.getText().toString();
        String password = lPaswField.getText().toString();

        //if blank throw a error massage
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LogInActivity.this, "Please enter Email and Password!", Toast.LENGTH_LONG).show();
        } else {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(LogInActivity.this, HomeActivity.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LogInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                lEmailField.setText("");
                                lPaswField.setText("");

                            }

                            // ...
                        }
                    });
        }



    }

}
