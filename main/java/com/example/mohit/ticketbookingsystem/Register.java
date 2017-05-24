package com.example.mohit.ticketbookingsystem;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//public class Register extends ActionBarActivity {
    public class Register extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    TextView heading;
    TextView username;
    TextView password;
    TextView confirmpassword;
    EditText usernamebox;
    EditText passwordbox;
    EditText confirmpasswordbox;
    Button submit;
    Button backtoLogin;
    MyDBHandler myDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeComponents();
    }

    public void initializeComponents() {
        Log.d(TAG,"Initialize Components Block In Register Class");
        heading=(TextView) findViewById(R.id.textView5);
        username=(TextView) findViewById(R.id.textView6);
        password=(TextView) findViewById(R.id.textView7);
        confirmpassword=(TextView) findViewById(R.id.textView8);
        usernamebox=(EditText) findViewById(R.id.editText3);
        passwordbox=(EditText) findViewById(R.id.editText4);
        confirmpasswordbox=(EditText) findViewById(R.id.editText5);
        submit=(Button) findViewById(R.id.button2);
        backtoLogin=(Button) findViewById(R.id.button3);
        myDBHandler= new MyDBHandler(this,null,null,1);
    }


    public void backtoLoginClick(View view)
    {
        try {
            Log.d(TAG, "IN BacktoLogin Area");
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void RegisterSubmit(View view)
    {
        try {
            String username = usernamebox.getText().toString();
            String password = passwordbox.getText().toString();
            String confirmpassword = confirmpasswordbox.getText().toString();
            Log.d(TAG, "IN Registration Area 1");
            if (password.compareToIgnoreCase(confirmpassword) == 0) {
                Log.d(TAG, "IN Registration Area 2");
                boolean inserted=myDBHandler.Register(this, username, password);
                Log.d(TAG, "IN Registration Area 3");
                if(inserted=true) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully",
                            Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getApplicationContext(), "Registation didn't happen",
                            Toast.LENGTH_LONG).show();
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
