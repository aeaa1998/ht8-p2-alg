public class Patient implements Comparable<Patient>{
    private String name;
    private String symptom;
    private String priority;

    public Patient(String name, String symptom, String priority) {
        this.name = name;
        this.symptom = symptom;
        this.priority = priority;
    }

    @Override
    public int compareTo(Patient o) {
        return priority.compareTo(o.priority);
    }

    @Override
    public String toString() {
        return
                "Nombre:" + name + "\n" +
                "Sintoma=" + symptom + '\n' +
                "Prioridad=" + priority + '\n';
    }
}
