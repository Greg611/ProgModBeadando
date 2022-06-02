package progmodbeadando.business;

import java.util.ArrayList;
import java.util.Random;

import progmodbeadando.business.os.Person;
import progmodbeadando.fio.Fio;

@ReadMethodName(name="read")
@SaveMethodName(name="saveDoctors")
@FileName(fileName = "doctors.xml")
public class Doctor extends Person {
    @GetterFunctionName(name="getPassword")
    private String password;
    @GetterFunctionName(name="getPatientsAsString")
    private ArrayList<String> patients;
    @GetterFunctionName(name="getAccessAsString")
    private Boolean access;

    public Doctor() {
        this.ID = "AAAAA";
        this.name = "admin";
        this.password = "12345";
        this.patients = new ArrayList<>();
        this.patients.add("None");
        this.access = true;
    }

    public Doctor(String name, String password, ArrayList<String> patients,String access){
        this.ID = newDoctorId(new ArrayList<>());
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

    public static void saveDoctors(ArrayList<Doctor> list){
        Fio<Doctor> doctorFio = new Fio<>(new Doctor());
        doctorFio.save(list);
    }

    public static ArrayList<Doctor> read(){
        ArrayList<Doctor> result = new ArrayList<>();
        Fio<Doctor> doctorFio = new Fio<>(new Doctor());
        ArrayList<ArrayList> list = doctorFio.read();
        System.out.println("asd");
        System.out.println(list.get(0).get(4).toString());
        System.out.println(Boolean.valueOf(list.get(0).get(4).toString()));
        for(int i=0;i<list.size();i++){
            result.add(new Doctor(list.get(i).get(0).toString(),list.get(i).get(1).toString(),list.get(i).get(2).toString(),list.get(i).get(3).toString(),list.get(i).get(4).toString()));
        }
        System.out.println("asd");
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

    @Override
    public String toString(){
        return this.ID + "   " + this.name;
    }
}
