package musicshow.willokans.com.music7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText rFullName;
    private EditText rEmail;
    private EditText rPassword;
    private Button rSignUp;
    private Button rBack;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //connect variable reference
        rBack = (Button) findViewById(R.id.reg_backBtn);
        rSignUp = (Button) findViewById(R.id.reg_signUp_btn);
        rPassword = (EditText) findViewById(R.id.reg_password_field);
        rEmail = (EditText) findViewById(R.id.reg_email_Field);
        rFullName = (EditText) findViewById(R.id.reg_fullName_field);

        //Create onclick listener for lBackbtn buttons
        rBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(s);
            }
        });

        //Create onclick listener for lLogin buttons
        rSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser();
            }
        });

        //set reference for lAuth
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.getCurrentUser();
    }

    private void createUser() {
        String email = rEmail.getText().toString();
        String password = rPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(RegisterActivity.this, LogInActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            rEmail.setText("");
                            rPassword.setText("");

                        }

                        // ...
                    }
                });

    }
}
