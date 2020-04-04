package com.uac.taller2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Equipo implements Parcelable, Serializable {

    private String Nombre;
    private String DirectorTecnico;
    private String NroCampeonatos;
    private String capital;

    protected Equipo(Parcel in) {
        Nombre = in.readString();
        DirectorTecnico = in.readString();
        NroCampeonatos = in.readString();
        capital = in.readString();
    }

    public static final Creator<Equipo> CREATOR = new Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel in) {
            return new Equipo(in);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDirectorTecnico() {
        return DirectorTecnico;
    }

    public void setDirectorTecnico(String directorTecnico) {
        DirectorTecnico = directorTecnico;
    }

    public String getNroCampeonatos() {
        return NroCampeonatos;
    }

    public void setNroCampeonatos(String nroCampeonatos) {
        NroCampeonatos = nroCampeonatos;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Equipo(String nombre, String directorTecnico, String capital, String nroCampeonatos) {
        Nombre = nombre;
        DirectorTecnico = directorTecnico;
        NroCampeonatos = nroCampeonatos;
        this.capital = capital;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(DirectorTecnico);
        dest.writeString(NroCampeonatos);
        dest.writeString(capital);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "Nombre='" + Nombre + '\'' +
                ", DirectorTecnico='" + DirectorTecnico + '\'' +
                ", NroCampeonatos='" + NroCampeonatos + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
