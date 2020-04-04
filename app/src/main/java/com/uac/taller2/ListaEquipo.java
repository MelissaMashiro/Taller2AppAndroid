package com.uac.taller2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaEquipo extends AppCompatActivity  {

    ListView listado;
    ArrayList<String> equipos;
    ArrayAdapter<Equipo> arrayAdapter;
    ArrayList<Equipo> equip;
    Button btn;
    EquipoAdapter myAdapter;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_equipo);

        equipos = new ArrayList<>();
        Intent in = getIntent();
        equip = in.getParcelableArrayListExtra("equi");
        assert equip != null;
        Toast.makeText(this,"equipos ="+ equip.size(),Toast.LENGTH_LONG).show();


        for (int i = 1; i<=10; i++) {
            equipos.add("Equipo " + i );
        }
        listado = findViewById(R.id.lstlistado);
        arrayAdapter = new EquipoAdapter(this, equip);
        registerForContextMenu(listado);

        listado.setAdapter(arrayAdapter);




       listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String dato = equipos.get(position);
                posicion=position;
                Toast.makeText(getApplicationContext(), "Equipo = " + dato, Toast.LENGTH_LONG).show();

            }

        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.eliminar_item:
                equip.remove(posicion);
                arrayAdapter.notifyDataSetChanged();

                break;
            case R.id.modificar_item:

                return true;

        }
        return super.onContextItemSelected(item);
    }


    public void OnClick(MenuItem item) {
        /*Intent i = new Intent(this,DetalleEquipo.class);
        startActivity(i);*/
        Intent intent = new Intent(ListaEquipo.this,DetalleEquipo.class);
        intent.putExtra("equipoData", (Serializable) equip.get(posicion));
        startActivity(intent);
    }
}
