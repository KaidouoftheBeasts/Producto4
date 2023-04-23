package beepod.controlador;


import beepod.dao.DAOException;
import beepod.vista.GestionOs;

import java.io.IOException;

public class
OnLineStore {
    public static void main(String[] args) throws IOException, DAOException {
        GestionOs gestionOs = new GestionOs();
        gestionOs.inicio();
    }
}
