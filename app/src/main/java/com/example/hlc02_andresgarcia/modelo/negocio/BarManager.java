package com.example.hlc02_andresgarcia.modelo.negocio;

import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Servicios;

import java.util.ArrayList;

public class BarManager {
    private static final BarManager INSTANCE = new BarManager();
    private ArrayList<Bar> listaBares = new ArrayList<>();

    private BarManager() {

        //inicializar singleton con datos dummy
        Bar bar1 = new Bar(1, "Bar Barro", "Av. de Madrid, 1, 04007 Almería", "imagen1", "Crónica de la actualidad gastronómica almeriense. Bar clásico de Almería que se ha modernizado en las nuevas corrientes culinarias de la ciudad: ofrecer unas pocas tapas de cortesía, completando el resto de la carta con pinchos (muy típico de la zona) y raciones. La carta ofrece variedad aunque también los precios son variados tirando por encima de la media. Nosotros principalmente tapeamos, siendo estas elaboraciones correctas. No te llevarás un recuerdo de ellas, ni para bueno ni para malo. Ademas probamos las croquetas de jamón ibérico, sinceramente están en consonancia a las congeladas de cualquier súper. El servicio fue majo pero tardaban bastante en atendernos. Conclusión, sitio correcto en el que poder disfrutar de unas cervezas o de una cena, ya lo de repetir quedará en ti.", "123456789", Servicios.WIFI);
        Bar bar2 = new Bar(2, "Bar  LaLoLa", "C. Paterna del Río, 50, 04007 Almería", "imagen2", "Sitio muy tranquilo,cerveza super fresquita y berenjenas deliciosas", "987654321", Servicios.TELEVISION);
        bar1.setVideo("https://www.youtube.com/watch?v=oxwmlyWHq8E");
        bar2.setVideo("https://www.youtube.com/watch?v=oxwmlyWHq8E");

        listaBares.add(bar1);
        listaBares.add(bar2);
    }

    public static BarManager getInstance() {
        return INSTANCE;
    }

    public ArrayList<Bar> getListaBares() {
        return listaBares;
    }

    //Metodo para agregar un nuevo bar a la lista just in case
    public void agregarBar(Bar bar) {
        listaBares.add(bar);
    }
}