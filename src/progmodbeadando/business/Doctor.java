package progmodbeadando.business;

import java.util.ArrayList;
import java.util.Random;

import progmodbeadando.business.os.Person;

@FileName(fileName = "doctors.xml")
public class Doctor extends Person {
    @GetterFunctionName(name="getPassword")
    private String password;
    @GetterFunctionName(name="getPatientsAsString")
    private ArrayList<String> patients;

    public Doctor() {
    }

    public Doctor(String name, String password, ArrayList<String> patients){
        this.ID = newDoctorId(new ArrayList<>());
        this.name = name;
        this.password = password;
        this.patients = patients;
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
}
