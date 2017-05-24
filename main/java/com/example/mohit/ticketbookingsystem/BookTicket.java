package com.example.mohit.ticketbookingsystem;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


//public class BookTicket extends ActionBarActivity {
public class BookTicket extends AppCompatActivity {
    private static final String TAG = "MyActivity----->";
    TextView bookticketHeading;
    TextView userName;
    TextView fromLocation;
    TextView toLocation;
    TextView travellDate;
    EditText usernamebox;
    EditText fromLocationBox;
    EditText toLocationBox;
    DatePicker travellDatebox;
    Button submitButton;
    Button goToShowAllButton;
    MyDBHandler myDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        initializeComponents();
        myDBHandler= new MyDBHandler(this,null,null,1);

    }
    public void initializeComponents() {
        Log.d(TAG, "Initialize Components Block in Login Class");
        bookticketHeading=(TextView)findViewById(R.id.textView9);
        userName=(TextView)findViewById(R.id.textView10);
        fromLocation=(TextView)findViewById(R.id.textView11);
        toLocation=(TextView)findViewById(R.id.textView12);
        travellDate=(TextView)findViewById(R.id.textView13);
        usernamebox=(EditText) findViewById(R.id.editText6);
        fromLocationBox=(EditText) findViewById(R.id.editText7);
        toLocationBox=(EditText) findViewById(R.id.editText8);
        travellDatebox=(DatePicker) findViewById(R.id.datePicker);
        submitButton=(Button) findViewById(R.id.button5);
        goToShowAllButton=(Button) findViewById(R.id.button6);
        myDBHandler= new MyDBHandler(this,null,null,1);
        Bundle bundle=getIntent().getExtras();
        if(bundle==null)
        {
            return;
        }
        String Usernameget=bundle.getString("Message Pass");
        usernamebox.setText(Usernameget);
    }

    public void bookTicketClick(View view)
    {
        int day=0;
        int month=0;
        int year=0;
        try {
            EditText usernamebox = (EditText) findViewById(R.id.editText6);
            EditText fromLocationbox = (EditText) findViewById(R.id.editText7);
            EditText toLocationbox = (EditText) findViewById(R.id.editText8);
            DatePicker travellDatebox = (DatePicker) findViewById(R.id.datePicker);
            String uname = usernamebox.getText().toString();
            String fromLocation = fromLocationbox.getText().toString();
            String toLocation = toLocationBox.getText().toString();
            day=travellDatebox.getDayOfMonth();
            month=travellDatebox.getMonth();
            year=travellDatebox.getYear();
            String travellDate = day+"/"+month+"/"+year;
            boolean inserted = myDBHandler.TicketBooking(uname, fromLocation, toLocation, travellDate);
            if (inserted = true) {
                Toast.makeText(getApplicationContext(), "Ticket Booked Successfully",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "!!!Some Error Happend",
                        Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void goToShowAll(View view)
    {
        Intent intent= new Intent(this,ShowAllBooking.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_ticket, menu);
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
