package progmodbeadando.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import jdk.jfr.Name;
import progmodbeadando.business.os.Person;

@ClassNameAnn(className = "Patient")
@FileName(fileName = "patients.xml")
public class Patient extends Person {
    @GetterFunctionName(name = "getBirthYear")
    private Integer birthYear;
    @GetterFunctionName(name = "getBloodType")
    private BloodTypeEnum bloodType;
    @GetterFunctionName(name = "getCheckInDate")
    private Integer checkInDate;
    @GetterFunctionName(name = "getDiseasesAsString")
    private ArrayList<String> diseases;

    public Patient() {
    }

    public Patient(String name, Integer birthYear, BloodTypeEnum bloodType, Integer checkInDate, ArrayList<String> diseases) {
        this.ID = newPatientId();
        this.name = name;
        this.birthYear = birthYear;
        this.bloodType = bloodType;
        this.checkInDate = checkInDate;
        this.diseases = diseases;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public BloodTypeEnum getBloodType() {
        return bloodType;
    }

    public Integer getCheckInDate() {
        return checkInDate;
    }

    public ArrayList<String> getDiseases() {
        return diseases;
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

    private static String newPatientId() {
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
            B=false;
            /*
            int i;
            for(i=0;i<list.size();i++){
                if(ID.equals(list.get(i).getID())){
                    break;
                }
            }
            if(i==list.size()){
                B=false;
            }*/
        }
        return ID;
    }
}
