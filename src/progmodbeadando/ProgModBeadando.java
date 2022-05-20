package progmodbeadando;

import progmodbeadando.business.BloodTypeEnum;
import progmodbeadando.business.Doctor;
import progmodbeadando.business.Patient;
import progmodbeadando.fio.Fio;

import java.util.ArrayList;

public class ProgModBeadando {
    public static void main(String[] args) {
        ArrayList<String> diseases = new ArrayList<>();
        diseases.add("asd");
        diseases.add("rew");
        Patient p = new Patient("asd",1, BloodTypeEnum.A_Negative,5,diseases);
        ArrayList<String> patients = new ArrayList<>();
        patients.add(p.getID());
        patients.add("11111");
        Doctor d = new Doctor("reww", "1254",patients);
        System.out.println("asd");
        Fio<Patient> pf = new Fio<>();
        Fio<Doctor> df = new Fio<>();
        pf.save(p);
        df.save(d);
    }
}
