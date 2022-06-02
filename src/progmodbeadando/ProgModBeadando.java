package progmodbeadando;

import progmodbeadando.business.BloodTypeEnum;
import progmodbeadando.business.Doctor;
import progmodbeadando.business.Patient;
import progmodbeadando.fio.Fio;
import progmodbeadando.service.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgModBeadando {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Azonosító:");String azon = scn.nextLine();
        System.out.println("Jelszó:");String pass = scn.nextLine();

        Service<Doctor> ds = new Service<>(new Doctor());
        ArrayList<Doctor> doctors = ds.read();
        ds.save(doctors);
    }
}
