package es.upm.pproject.gym;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    // Creacion del logger
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // Mensajes de prueba del logger
        logger.info("Aplicación GymManager iniciada");
        logger.debug("Mensaje de debug (solo visible si nivel es DEBUG)");
        logger.warn("Esto es una advertencia");
        logger.error("Esto es un error");

    }

}
