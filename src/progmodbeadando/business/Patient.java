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

    public Patient(String ID, String name,Integer birthYear, String bloodType, Date checkInDate, String diseases) {
        this.ID = ID;
        this.name = name;
        this.birthYear = birthYear;
        this.bloodType = BloodTypeEnum.findByValue(bloodType);
        this.checkInDate = checkInDate;
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

    public boolean read(String path, ArrayList<Patient> list){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            Element rootElement = document.getDocumentElement();
            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i=0;i<childNodesList.getLength();i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                   String diseases = "";
                    NodeList childNodesOfPatientTag = node.getChildNodes();
                    String name="",birthYear ="",checkInDate = "",ID = "", bloodType = "";
                    for(int j = 0;j<childNodesOfPatientTag.getLength();j++){
                        if(childNodesOfPatientTag.item(j).getNodeType()==Node.ELEMENT_NODE){
                            switch (childNodesOfPatientTag.item(j).getNodeName()){
                                case "ID": ID=childNodesOfPatientTag.item(j).getTextContent();break;
                                case "name": name=childNodesOfPatientTag.item(j).getTextContent();break;
                                case "birthYear":birthYear=childNodesOfPatientTag.item(j).getTextContent();break;
                                case "bloodType":bloodType=childNodesOfPatientTag.item(j).getTextContent();break;
                                case "checkInDate":checkInDate=childNodesOfPatientTag.item(j).getTextContent();break;
                                case "disease": diseases=(childNodesOfPatientTag.item(j).getTextContent());break;
                            }
                        }
                    }

                    list.add(new Patient(ID,name,Integer.parseInt(birthYear), bloodType, Date.valueOf(checkInDate),diseases));
                }
            }
            return true;
        }
        catch(FileNotFoundException e){
            System.err.println("A fájl nem létezik, ezért a program nem tud elindulni.");
            return false;
        }
        catch(Exception e){
            System.err.println("Olvasási hiba   "+e + "\n" + e.getStackTrace() + "\n" + e.getCause() + "\n" + e.getMessage());
            return false;
        }
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
