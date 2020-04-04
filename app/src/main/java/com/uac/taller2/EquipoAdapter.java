package com.uac.taller2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class EquipoAdapter extends ArrayAdapter<Equipo> {

    private LayoutInflater inflater = null;
    private Context mycontext;

    public EquipoAdapter(@NonNull Context context, @NonNull ArrayList<Equipo> equi) {
        super(context, 0, equi);
        inflater = LayoutInflater.from(context);
        this.mycontext=context;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.listadoadapter_equipo, null);
            // Do some initialization

            //Retrieve the view on the item layout and set the value.
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //Retrieve your object
        Equipo data = (Equipo) getItem(position);


        viewHolder.nomEqui.setText("EQUIPO: "+data.getNombre());
        viewHolder.nomTec.setText("DIR TECNICO: "+data.getDirectorTecnico());
        viewHolder.cap.setText("CAPITAL RESIDENCIA: "+data.getCapital());
        viewHolder.numCamp.setText("CAMP GANADOS: "+data.getNroCampeonatos());


        return view;

    }

    private class ViewHolder
    {
        private final TextView nomEqui;
        private final TextView nomTec;
        private final TextView cap;
        private final TextView numCamp;

        private ViewHolder(View view)
        {
            nomEqui   = (TextView) view.findViewById(R.id.txtnombreEquipo);
            nomTec = (TextView) view.findViewById(R.id.txtNombreTecnico);
            cap = (TextView) view.findViewById(R.id.txtSpinner);
            numCamp = (TextView) view.findViewById(R.id.txtPicker);
        }


    }


}
