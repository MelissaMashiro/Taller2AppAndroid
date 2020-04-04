package com.uac.taller2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

public class DetalleEquipo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String textSpin;
    String numberPick;
    ArrayList<Equipo> equipos = new ArrayList<Equipo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);

        final TextView equipoDetalle = findViewById(R.id.txtContenidoEquipo);
        Button botonEditar = findViewById(R.id.btnEditar);
        final Equipo equi = (Equipo) getIntent().getSerializableExtra("equipoData");
        final EditText modEquipo = findViewById(R.id.modEquipo);
        final EditText modTecnico = findViewById(R.id.modTecnico);

        //INFORMACION PARA EL TEXTVIEW
        assert equi != null;
        String contenido = "Informacion actual:\n"
                +"\nEQUIPO: "+ equi.getNombre()
                + "\nDIR TECNICO: "+ equi.getDirectorTecnico()
                + "\nCAPITAL: "+ equi.getCapital()
                + "\nCAMP GANADOS: "+ equi.getNroCampeonatos()
                +"\n---------------------------\n";

        equipoDetalle.setText(contenido);

        //PICKER
        NumberPicker np = findViewById(R.id.numberPicker2);

        np.setMinValue(0);
        np.setMaxValue(20);
        np.setOnValueChangedListener(onValueChangeListener);


        //SPINNER
        Spinner capital = findViewById(R.id.spinnerCiudad2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.capitales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        capital.setAdapter(adapter);
        capital.setOnItemSelectedListener(this);

        botonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent_editar = new Intent(DetalleEquipo.this,equipoEditar.class);
                intent_editar.putExtra("equipo", (Serializable) equi);
                startActivity(intent_editar);*/
                String nombreNuevo= modEquipo.getText().toString();
                equi.setNombre(nombreNuevo);
                String tecnicoNuevo= modTecnico.getText().toString();
                equi.setDirectorTecnico(tecnicoNuevo);
                equi.setCapital(textSpin);
                equi.setNroCampeonatos(numberPick);

                //NO MOSTRAR
                equipos.add(new Equipo(nombreNuevo,tecnicoNuevo, textSpin,numberPick));

                String contenido = "Informacion actual (ACTUALIZADA):\n"
                        +"\nEQUIPO: "+ equi.getNombre()
                        + "\nDIR TECNICO: "+ equi.getDirectorTecnico()
                        + "\nCAPITAL: "+ equi.getCapital()
                        + "\nCAMP GANADOS: "+ equi.getNroCampeonatos()
                        +"\n---------------------------\n";

                equipoDetalle.setText(contenido);

            }
        });

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker numberPicker,int i, int i1) {
                    numberPick= String.valueOf(numberPicker.getValue());
                    Toast.makeText(DetalleEquipo.this, "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
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
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
