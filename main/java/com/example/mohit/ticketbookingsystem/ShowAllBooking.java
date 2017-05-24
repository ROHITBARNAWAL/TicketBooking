package com.example.mohit.ticketbookingsystem;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


//public class ShowAllBooking extends ActionBarActivity {
public class ShowAllBooking extends AppCompatActivity {
    TextView showAllHeader;
    Button showAllData;
    Button backToMain;
    MyDBHandler myDBHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_booking);
        initializeComponents();
        myDBHandler= new MyDBHandler(this,null,null,1);
    }

    public void initializeComponents()
    {
        showAllData=(Button) findViewById(R.id.button7);
        showAllHeader=(TextView) findViewById(R.id.textView14);
        backToMain= (Button) findViewById(R.id.button8);
        myDBHandler= new MyDBHandler(this,null,null,1);

    }

    public void BackToMain(View view)
    {
        Intent intent= new Intent(this,Login.class);
        startActivity(intent);
    }

    public void ShowAll(View view)
    {
        Cursor res=myDBHandler.getAllData();
        if(res.getCount()==0)
        {
            ShowMessage("Error","No Data Found");
            return;
        }
        StringBuffer sb=new StringBuffer();
        while (res.moveToNext())
        {
            sb.append("UserName :"+res.getString(0)+"\n");
            sb.append("FromLocation :"+res.getString(1)+"\n");
            sb.append("ToLocation :"+res.getString(2)+"\n");
            sb.append("Traveldate :"+res.getString(3)+"\n");
        }
        ShowMessage("Data",sb.toString());
    }

    public void ShowMessage(String title,String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_booking, menu);
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
