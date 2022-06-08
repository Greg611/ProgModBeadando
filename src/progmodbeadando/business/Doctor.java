package progmodbeadando.business;

import java.util.ArrayList;
import java.util.Random;

import progmodbeadando.business.os.Person;
import progmodbeadando.fio.Fio;

@ReadMethodName(name="read")
@SaveMethodName(name="save")
@FileName(fileName = "doctors.xml")
public class Doctor extends Person {
    @GetterFunctionName(name="getPassword")
    private String password;
    @GetterFunctionName(name="getPatientsAsString")
    private ArrayList<String> patients;
    @GetterFunctionName(name="getAccessAsString")
    private Boolean access;

    public Doctor() {}

    public Doctor(ArrayList<String> list, ArrayList<Doctor> doctors){
        this.ID = newDoctorId(doctors);
        this.name = list.get(0);
        this.password = list.get(1);
        this.patients = getListFromString(list.get(2));
        this.access = Boolean.valueOf(list.get(3));
    }

    public Doctor(String name, String password, ArrayList<String> patients,String access, ArrayList<Doctor> doctors){
        this.ID = newDoctorId(doctors);
        this.name = name;
        this.password = password;
        this.patients = patients;
        this.access = Boolean.valueOf(access);
    }

    public Doctor(String ID, String name,String password, String patients, String access){
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.patients = getListFromString(patients);
        this.access = Boolean.valueOf(access);
    }

    public static void save(ArrayList<Doctor> list){
        Fio<Doctor> doctorFio = new Fio<>(new Doctor());
        doctorFio.save(list);
    }

    public static ArrayList<Doctor> read(){
        ArrayList<Doctor> result = new ArrayList<>();
        Fio<Doctor> doctorFio = new Fio<>(new Doctor());
        ArrayList<ArrayList> list = doctorFio.read();
        for(int i=0;i<list.size();i++){
            result.add(new Doctor(list.get(i).get(0).toString(),list.get(i).get(1).toString(),list.get(i).get(2).toString(),list.get(i).get(3).toString(),list.get(i).get(4).toString()));
            //result.add(new Doctor(list.get(i).get(0).toString(),list.get(i).get(1).toString(),list.get(i).get(2).toString(),list.get(i).get(3).toString(),list.get(i).get(4).toString()));
        }
        return result;
    }

    public String getPassword(){
        return this.password;
    }

    public ArrayList<String> getPatients(){
        return this.patients;
    }

    public String getPatientsAsString(){
        String result = "";
        for(int i=0;i<patients.size();i++){
            if(i==patients.size()-1){
                result = result + patients.get(i);
            }
            else{
                result = result + patients.get(i) + ';';
            }
        }
        return result;
    }

    public String getAccessAsString() {
        return access.toString();
    }

    private ArrayList<String> getListFromString(String string){
        ArrayList result = new ArrayList<>();
        ArrayList<Integer> tokens = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)==';'){
                tokens.add(i);
            }
        }
        tokens.add(string.length());
        for(int i=0;i<tokens.size();i++){
            System.out.println(tokens.get(i));
        }
        for(int i = 0;i<tokens.size();i++){
            if(i==0){
                result.add(string.substring(0, tokens.get(i)));
            }
            else{
                result.add(string.substring((tokens.get(i-1)+1),(tokens.get(i))));
            }
        }
        return result;
    }

    private static String newDoctorId(ArrayList<Doctor> list){
        String ID = "";
        Boolean B = true;
        while (B) {
            for (int i = 0; i < 5; i++) {
                Random rnd = new Random();
                Character IDElement;
                    IDElement =(char) (rnd.nextInt(26)+'A');
                ID = ID + IDElement;
            }
            int i;
            for(i=0;i<list.size();i++){
                if(ID.equals(list.get(i).getID())){
                    break;
                }
            }
            if(i==list.size()){
                B=false;
            }
        }
        return ID;
    }

    public void addNewPatient(String ID){
        this.patients.add(ID);
    }

    public void removePatient(String ID){
        this.patients.remove(ID);
    }

    @Override
    public String toString(){
        return this.ID + "   " + this.name;
    }
}
