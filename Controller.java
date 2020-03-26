import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

public class Controller {
    private ArrayList<String> options = new ArrayList<>(){{
        add("Ver que paciente le toca ser atendido");
        add("Pasar el siguiente paciente");
        add("Salir");
    }};
    private ArrayList<Patient> patients = new ArrayList<>();
    private PriorityQueue<Patient> heap;

    public void init(){
        fillPool();
        heap  = new PriorityQueue<Patient>(){{
            addAll(patients);
        }};
        boolean t = true;
        while (t){
            switch (View.getView().selectOptions(options, "Escoja el numero de una de las opciones", "Escoja una opcion valida")){
                case 0:
                    View.getView().print("El paciente que le toca pasar es:");
                    View.getView().print(heap.peek().toString());
                    break;
                case 1:
                    View.getView().print("El paciente que va a pasar ahorita es:");
                    View.getView().print(heap.poll().toString());
                    if (heap.isEmpty()){
                        View.getView().print("Ya pasaron todos los pacientes");
                        t = false;
                    }else{
                        View.getView().print("El paciente que le toca pasar luego es:");
                        View.getView().print(heap.peek().toString());
                    }

                    break;
                default:
                    View.getView().print("Gracias por usar el programa pase feliz dia");
                    t = false;
                    break;
            }
        }

    }
    private void fillPool(){
        try {

            var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (getOsName().startsWith("Windows")){
                if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
            }
            List<String> strings = Files.readAllLines(Path.of(mainPath + "pacientes.txt"));
            for (String line:
                    strings) {

                var holder = new ArrayList<String>(){{ addAll(List.of(line.split(", ")));}};
                var name = holder.get(0);
                var symptom = holder.get(1);
                var letter = holder.get(2);
                patients.add(new Patient(name, symptom, letter));
            }

        } catch(URISyntaxException | IOException e){
            System.out.print(e);
            System.out.println("Revise bien que su archivo txt  exista");
        }
    }
    private  String getOsName()
    {
        return System.getProperty("os.name");
    }
}
