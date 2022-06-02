package progmodbeadando.business;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import jdk.jfr.Name;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import progmodbeadando.business.os.Person;
import progmodbeadando.fio.Fio;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@FileName(fileName = "patients.xml")
public class Patient extends Person {
    @GetterFunctionName(name = "getBirthYear")
    private Integer birthYear;
    @GetterFunctionName(name = "getBloodTypeAsString")
    private BloodTypeEnum bloodType;
    @GetterFunctionName(name = "getCheckInDate")
    private Date checkInDate;
    @GetterFunctionName(name = "getDiseasesAsString")
    private ArrayList<String> diseases;

    public Patient() {
    }

    public Patient(String name, Integer birthYear, BloodTypeEnum bloodType, Date checkInDate, ArrayList<String> diseases) {
        this.ID = newPatientId(new ArrayList<Patient>());
        this.name = name;
        this.birthYear = birthYear;
        this.bloodType = bloodType;
        this.checkInDate = checkInDate;
        this.diseases = diseases;
    }

    public Patient(String ID, String name,String birthYear, String bloodType, String checkInDate, String diseases) {
        this.ID = ID;
        this.name = name;
        this.birthYear = Integer.parseInt(birthYear);
        this.bloodType = BloodTypeEnum.findByValue(bloodType);
        this.checkInDate = Date.valueOf(checkInDate);
        this.diseases = getListFromString(diseases);
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public BloodTypeEnum getBloodType() {
        return bloodType;
    }

    public String getBloodTypeAsString(){
        return bloodType.getShortName();
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public ArrayList<String> getDiseases() {
        return diseases;
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

    public String getDiseasesAsString() {
        String result = "";
        for (int i = 0; i < diseases.size(); i++) {
            if (i == diseases.size() - 1) {
                result = result + diseases.get(i);
            } else {
                result = result + diseases.get(i) + ';';
            }
        }
        return result;
    }

    public static ArrayList<Patient> read(){
        ArrayList<Patient> result = new ArrayList<>();
        Fio<Patient> patientFio = new Fio<>(new Patient());
        ArrayList<ArrayList> list = patientFio.read();
        for(int i=0;i<list.size();i++){
            result.add(new Patient(list.get(i).get(0).toString(),list.get(i).get(1).toString(),list.get(i).get(2).toString(),list.get(i).get(3).toString(),list.get(i).get(4).toString(),list.get(i).get(5).toString()));
        }
        return result;
    }

    private static String newPatientId(ArrayList<Patient> list) {
        String ID = "";
        Boolean B = true;
        while (B) {
            for (int i = 0; i < 5; i++) {
                Random rnd = new Random();
                Integer IDElement;
                if (i == 0) {
                    IDElement = rnd.nextInt(8) + 1;
                } else {
                    IDElement = rnd.nextInt(9);
                }
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
