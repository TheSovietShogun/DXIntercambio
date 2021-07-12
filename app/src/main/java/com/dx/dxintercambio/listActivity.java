package com.dx.dxintercambio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class listActivity extends AppCompatActivity {

    private ListView listView ;
private listAdapter listAdapter ;
    private int widthScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listEnvio);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(listActivity.this);

        List<CPopulateList> populateLists = dataBaseHelper.selectListIntercambioTerminado();

        widthScreen = getApplicationContext().getResources().getDisplayMetrics().widthPixels;


        if (widthScreen > 480) {
            listAdapter = new listAdapter(getApplicationContext(),R.layout.mlist_item,populateLists);
        }else {
            listAdapter = new listAdapter(getApplicationContext(),R.layout.mlist_item_cel,populateLists);
        }

        listView.setAdapter(listAdapter);

    }
}