package com.example.mohit.ticketbookingsystem;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


//public class Login extends ActionBarActivity {
public class Login extends AppCompatActivity {
    private static final String TAG = "MyActivity----->";
    TextView LoginText;
    TextView unameText;
    TextView passwordText;
    TextView registerText;
    EditText unamebox;
    EditText passbox;
    Button loginButton;
    Button regButton;
    MyDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyDBHandler myDBHandler= new MyDBHandler(this,null,null,1);
        initializeComponents();
    }

    public void initializeComponents()
    {
        Log.d(TAG, "Initialize Components Block in Login Class");
        LoginText=(TextView) findViewById(R.id.textView);
        unameText=(TextView) findViewById(R.id.textView2);
        passwordText=(TextView) findViewById(R.id.textView3);
        registerText=(TextView) findViewById(R.id.textView4);
        unamebox=(EditText) findViewById(R.id.editText);
        passbox=(EditText) findViewById(R.id.editText2);
        loginButton=(Button) findViewById(R.id.button);
        regButton=(Button) findViewById(R.id.button4);
        myDBHandler= new MyDBHandler(this,null,null,1);
    }

    public void LoginClick(View view)
    {
        Log.d(TAG,"-------------------In Login Click Area--------------");
        EditText unamebox=(EditText) findViewById(R.id.editText);
        EditText passbox=(EditText) findViewById(R.id.editText2);
        try {
            String username;
            username = unamebox.getText().toString();
            String password;
            password = passbox.getText().toString();
            Log.d(TAG,username+"----UserName----------------");
            String originalPass = myDBHandler.getFromDb(username);
            Log.d(TAG,originalPass+"--------Orginial Pass------------");
            if (password.compareToIgnoreCase(originalPass) == 0) {
                Intent intent = new Intent(this, BookTicket.class);
                intent.putExtra("Message Pass",username);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "!!!Wrong UserName or Password",
                        Toast.LENGTH_LONG).show();

            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void RegisterClick(View view)
    {
        try {
            Log.d(TAG, "IN Registration re-direct");
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
