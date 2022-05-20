package progmodbeadando.fio;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import progmodbeadando.business.ClassNameAnn;
import progmodbeadando.business.FileName;
import progmodbeadando.business.GetterFunctionName;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Fio <T>{

    public void save(T object){
        try{
            Class clazz = object.getClass();
            System.out.println(clazz.getSimpleName());
            System.out.println(object.getClass().getAnnotation(FileName.class).fileName());
            File f = new File(object.getClass().getAnnotation(FileName.class).fileName());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            System.out.println("1");
            Document xml = db.parse(f);
            System.out.println("2");
            Element root = xml.createElement(object.getClass().getSimpleName());
            Field[] superFields = object.getClass().getSuperclass().getDeclaredFields();
            for(Field superField: superFields){
                System.out.println("asd");
                if(superField.getAnnotation(GetterFunctionName.class).name() != null){
                    System.out.println("asdasd");
                    String gfn = superField.getAnnotation(GetterFunctionName.class).name();
                    Method gm = object.getClass().getSuperclass().getMethod(gfn);
                    String value = gm.invoke(object).toString();
                    String name = superField.getName();
                    System.out.println(name + " " + value);
                    Element element = xml.createElement(name);
                    element.setTextContent(value);
                    root.appendChild(element);
                }
                System.out.println();
            }
            Field[] Fields = object.getClass().getDeclaredFields();
            for(Field Field: Fields){
                System.out.println("asdasdasd");
                if(Field.getAnnotation(GetterFunctionName.class).name() != null){
                    System.out.println("asdasdasdasd");
                    String gfn = Field.getAnnotation(GetterFunctionName.class).name();
                    Method gm = object.getClass().getMethod(gfn);
                    String value = gm.invoke(object).toString();
                    String name = Field.getName();
                    System.out.println(name + " " + value);
                    Element element = xml.createElement(name);
                    element.setTextContent(value);
                    root.appendChild(element);
                }
                System.out.println();
            }
            xml.getFirstChild().appendChild(root);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            DOMSource s = new DOMSource(xml);
            StreamResult r = new StreamResult(f);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(s, r);
        }
        catch(Exception ex){
            System.out.println("Hiba" + ex.getStackTrace());
            System.out.println(ex.toString());
            System.out.println(ex.getCause());
            System.out.println(ex);
        }
    }
}
