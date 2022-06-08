package progmodbeadando;

import progmodbeadando.business.BloodTypeEnum;
import progmodbeadando.business.Doctor;
import progmodbeadando.business.Patient;
import progmodbeadando.fio.Fio;
import progmodbeadando.service.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgModBeadando {
    public static void main(String[] args) {
        Service<Doctor> ds = new Service<>(new Doctor());
        ArrayList<Doctor> doctors = ds.read();
        Service<Patient> ps = new Service<>(new Patient());
        Boolean b;
        Scanner scn = new Scanner(System.in);
        System.out.println("Azonosító:");String azon = scn.nextLine();
        System.out.println("Jelszó:");String pass = scn.nextLine();
        Doctor logged = null;
        for(int i = 0;i<doctors.size();i++){
            if((doctors.get(i).getID().equals(azon)) && (doctors.get(i).getPassword().equals(pass))){
                logged = doctors.get(i);
            }
        }
        if(logged ==null){
            b = false;
            System.out.println("Helytelen belépési adatok");
        }
        else{
            b=true;
            System.out.println(logged);
        }
        ArrayList<Patient> allp = ps.read();
        ArrayList<Patient> patients = new ArrayList<>();
        if(b) {
            for (int i = 0; i < allp.size(); i++) {
                for (int j = 0; j < logged.getPatients().size(); j++) {
                    if (logged.getPatients().get(j).equals(allp.get(i).getID())) {
                        patients.add(allp.get(i));
                    }
                }
            }
        }
        Integer choice;
        while(b){
            ArrayList<Patient> patients2 = new ArrayList<>();
            for (int i = 0; i < allp.size(); i++) {
                for (int j = 0; j < logged.getPatients().size(); j++) {
                    if (logged.getPatients().get(j).equals(allp.get(i).getID())) {
                        patients2.add(allp.get(i));
                    }
                }
            }
            patients = patients2;
            choice = readIntegerFromConsole("Válassza ki mit szeretne tenni:\n" +
                    "0-Kilépés\n" +
                    "1-Páciensek listázása\n" +
                    "2-Új páciens felvétele\n" +
                    "3-Páciens eltávolítása a listából\n" +
                    "4-Páciens adatainak módosítása\n");
            switch(choice){
                case 0 : b=false;break;
                case 1 : ps.list(patients);break;
                case 2 :
                    ArrayList<String> newPatient = new ArrayList<>();
                    System.out.println("Adja meg a páciens nevét:");newPatient.add(scn.nextLine());
                    System.out.println("Adja meg a páciens születési évét:");newPatient.add(scn.nextLine());
                    System.out.println("Adja meg a páciens vércsoportját(0-,A+,stb.):");newPatient.add(scn.nextLine());
                    Date now = Date.valueOf(LocalDate.now());
                    newPatient.add(now.toString());
                    System.out.println("Adja meg a páciens panaszait(pontosvesszővel elválasztva):");newPatient.add(scn.nextLine());
                    ps.newObject(newPatient, allp);
                    for(int i=0;i<allp.size();i++){
                        if(allp.get(i).getName().equals(newPatient.get(0))){
                            logged.addNewPatient(allp.get(i).getID());

                        }
                    }
                    break;
                case 3:
                    int number = 0;
                    System.out.println("Adja meg a törölni kívánt páciens azonosítóját");String ID = scn.nextLine();
                    for(int i=0;i< allp.size();i++){
                        if(allp.get(i).getID().equals(ID)){
                            allp.remove(i);
                            logged.removePatient(ID);
                            System.out.println("Páciens sikeresen törölve.");
                            number++;
                        }
                    }
                    if(number==0){
                        System.out.println("Nincs ilyen azonosítojú páciens");
                    }
                    break;
                case 4:
                    System.out.println("Adja meg a módosítani kívánt páciens azonosítóját:");ID = scn.nextLine();
                    Integer choicem = readIntegerFromConsole("Adja meg melyik értéket kívánja módosítani:" +
                            "1-Név\n" +
                            "2-Születési év\n" +
                            "3-Vércsoport\n" +
                            "4-Panaszok\n");
                    Patient modify = null;
                    for(int i=0;i<patients.size();i++){
                        if(patients.get(i).getID().equals(ID)){
                            modify=patients.get(i);
                        }
                    }
                    if(modify == null){
                        break;
                    }
                    String modifyValue;
                    switch(choicem){
                        case 1 :
                            System.out.println("Adja meg az új nevet:");modifyValue=scn.nextLine();
                            modify.setName(modifyValue);
                        case 2 :
                            System.out.println("Adja meg az új születési évet:");modifyValue=scn.nextLine();
                            modify.setBirthYear(modifyValue);
                        case 3 :
                            System.out.println("Adja meg az új vércsoportot(0+,A-,stb.):");modifyValue=scn.nextLine();
                            modify.setBloodType(modifyValue);
                        case 4 :
                            System.out.println("Adja meg az összes panaszt(pontosvesszővel elválasztva):");modifyValue=scn.nextLine();
                            modify.setDiseases(modifyValue);
                    }
            }
        }
        ps.save(allp);
        ds.save(doctors);
    }

    private static Integer readIntegerFromConsole(String outputString){
        Integer result = 0;
        Scanner scn = new Scanner(System.in);
        Boolean b = true;

        while(b){
            try{
                System.out.print(outputString);
                result=scn.nextInt();
                scn.nextLine();
                b=false;
            }
            catch(InputMismatchException ex){
                scn.nextLine();
                System.out.println("Csak szám adható meg.");
            }
        }
        return result;
    }
}
