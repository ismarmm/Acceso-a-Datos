package JSON;
import JSON.Empleado;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Gestiona {
    private static final Logger logger = LogManager.getLogger(Gestiona.class);
    public static void main(String[] args) {

        String fichero = "C:\\Users\\alumno\\IdeaProjects\\AD\\src\\main\\resources";
        Gson gson = new Gson();
        Empleado empleado = gson.fromJson(fichero, Empleado.class);
        logger.info(empleado);
    }

}
