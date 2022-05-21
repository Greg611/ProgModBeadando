package progmodbeadando;

import progmodbeadando.business.BloodTypeEnum;
import progmodbeadando.business.Doctor;
import progmodbeadando.business.Patient;
import progmodbeadando.fio.Fio;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProgModBeadando {
    public static void main(String[] args) {
        ArrayList<String> diseases = new ArrayList<>();
        diseases.add("asd");
        diseases.add("rew");
        Date now = Date.valueOf(LocalDate.now());
        Patient p = new Patient("asd",1, BloodTypeEnum.A_Negative,now,diseases);
        ArrayList<Patient> plist = new ArrayList<>();
        plist.add(p);
        ArrayList<String> patients = new ArrayList<>();
        patients.add(p.getID());
        patients.add("11111");
        Doctor d = new Doctor("reww", "1254",patients);
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(d);
        Fio<Patient> pf = new Fio<>(new Patient());
        Fio<Doctor> df = new Fio<>(new Doctor());
        pf.save(plist);
        df.save(doctors);
    }
}
