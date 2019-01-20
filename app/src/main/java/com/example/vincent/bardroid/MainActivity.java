package com.example.vincent.bardroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView txtResult;
    public EditText txtInput;
    public TcpClient mTcpClient;
    public Table[] tableData;
    public Bartender[] BartenderData;
    public Consumable[] consumableData;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtInfo);
        txtInput = findViewById(R.id.editText);
        createSomeData();
        new ConnectTask().execute("");
    }
    public void btnSend(View view)
    {
        List<Table> lijst = new ArrayList<>();
        lijst.add(createSomeData());
        sendSpecificData(lijst);

    }
    public void sendSpecificData(List<Table> tableDataToSend)
    {
        if (mTcpClient != null) {
            mTcpClient.sendMessage(new Gson().toJson(new Data(3,tableDataToSend,3)));
        }
    }
    public void sendBackupData()
    {
        if (mTcpClient != null) {
            mTcpClient.sendMessage(new Gson().toJson(""));
        }
    }
    public Table createSomeData()
    {
        Table tafel1 = new Table();
        Order order1 = new Order(new Bartender(1,"joske","Bangelijk"));
        order1.addDrink(new Consumable("cola", 1.2));
        order1.addDrink(new Consumable("cola", 1.2));
        order1.addDrink(new Consumable("cola", 1.2));
        order1.addDrink(new Consumable("fanta", 1.3));
        tafel1.addOrder(order1);

        return tafel1;
    }

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //response received from server
            Gson gson = new Gson();
            tafel1 = gson.fromJson(values[0], Table.class);
            Log.d("test", "response " + values[0]);
            Bill bill = new Bill(tafel1);
            Log.d("tag","Bill"+"\n"+bill.getBillContent());
            txtResult.setText(bill.getBillContent());


            //process server response here....

        }
    }

}
