package com.uac.taller2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    Button agregar;
    ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    EditText equipo, tecnico, capital, campeonatosGan;
    String textSpin, rdb;
    TextView nombreEqui, nombreTec, nombreCap, numCampGan;
    String numberPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String nombre = i.getStringExtra("equipo");

        agregar = findViewById(R.id.btnAgregar);
        equipo = findViewById(R.id.edtNombreEquipo);
        tecnico = findViewById(R.id.edtDirectorTecnico);
        //textViews
        nombreEqui = findViewById(R.id.txtEquipo);
        nombreCap = findViewById(R.id.txtCapital);
        numCampGan = findViewById(R.id.txtCampGan);
        nombreTec=findViewById(R.id.txtNombreTecnico);

        agregar.setOnClickListener(this);

        //spinner
        Spinner capital = findViewById(R.id.spinnerCiudad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.capitales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        capital.setAdapter(adapter);
        capital.setOnItemSelectedListener(this);




        //number picker

        NumberPicker np = findViewById(R.id.numberPicker);

        np.setMinValue(0);
        np.setMaxValue(20);
        np.setOnValueChangedListener(onValueChangeListener);
    }
    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker,int i, int i1) {
                    numberPick= String.valueOf(numberPicker.getValue());
                    Toast.makeText(MainActivity.this, "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);

    }
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {

            case R.id.btnAgregar:
                agregarEquipo();
                break;

        }
    }

    private void agregarEquipo() {
        equipos.add(new Equipo(equipo.getText().toString(), tecnico.getText().toString(), textSpin,numberPick));
    }


    public void verListado(){
        Intent i = new Intent(this,ListaEquipo.class);
        i.putParcelableArrayListExtra("equi", equipos);
        startActivity(i);
    }
    //spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textSpin = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), textSpin, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.mnuListado:
               verListado();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}