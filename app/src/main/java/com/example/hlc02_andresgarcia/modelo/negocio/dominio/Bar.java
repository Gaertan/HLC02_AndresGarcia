package com.example.hlc02_andresgarcia.modelo.negocio.dominio;

import java.io.Serializable;
import java.util.ArrayList;

    public class Bar implements Serializable {
    //Gracias a serializable se puede pasar entre activities,fragmentos etcetera

        private int codigo;
        private String nombre;
        private String direccion;
        private String img;
        private String descripcion;
        private String telefono;
        private String video;
        private ArrayList<Servicios> servicios;

        public Bar(int codigo, String nombre, String direccion, String img, String descripcion, String telefono, ArrayList<Servicios> servicios) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.direccion = direccion;
            this.img = img;
            this.descripcion = descripcion;
            this.telefono = telefono;
            this.servicios = servicios;
        }
        public Bar(int codigo, String nombre, String direccion) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.direccion = direccion;
        }
        public Bar(int codigo, String nombre, String direccion, String img, String descripcion, String telefono, Servicios servicio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.direccion = direccion;
            this.img = img;
            this.descripcion = descripcion;
            this.telefono = telefono;
            if (this.servicios!=null){servicios.add(servicio);}
            else{ this.servicios= new ArrayList<Servicios>();     this.servicios = servicios;servicios.add(servicio);}

        }
        // Getters y Setters
        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {

            //TODO añadir checks de null
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.direccion = direccion;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public ArrayList<Servicios> getServicios() {
            return servicios;
        }

        public void setServicios(ArrayList<Servicios> servicios) {
            this.servicios = servicios;
        }
        public void addServicio(Servicios servicio){

            if (this.servicios!=null){servicios.add(servicio);}
           else{ this.servicios= new ArrayList<Servicios>();
            this.servicios = servicios;}}

        public void removeServicio(Servicios servicio) {
            if (servicios != null) {
                servicios.remove(servicio);
            }
        }

        public void removeServicio(String nombreServicio) {
            if (servicios != null) {
                for (int i = 0; i < servicios.size(); i++) {
                    if (servicios.get(i).getDescripcion().equals(nombreServicio)) {
                        servicios.remove(i);
                        break;
                    }
                }
            }
        }
        // Método equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bar bar = (Bar) o;

            return codigo == bar.codigo;
        }

        @Override
        public int hashCode() {
            return codigo;
        }
    }