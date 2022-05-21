package progmodbeadando.fio;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Fio <T>{

    T object;

    public Fio(T object){
        this.object = object;
    }

    public void save(ArrayList<T> list){
        try{
            File f = new File(object.getClass().getAnnotation(FileName.class).fileName());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xml = db.newDocument();
            Element root = xml.createElement(object.getClass().getSimpleName());
            xml.appendChild(root);
            Field[] superFields = object.getClass().getSuperclass().getDeclaredFields();
            Field[] Fields = object.getClass().getDeclaredFields();
            for(T t : list) {
                for (Field superField : superFields) {
                    if (superField.getAnnotation(GetterFunctionName.class).name() != null) {
                        String gfn = superField.getAnnotation(GetterFunctionName.class).name();
                        Method gm = object.getClass().getSuperclass().getMethod(gfn);
                        String value = gm.invoke(t).toString();
                        String name = superField.getName();
                        Element element = xml.createElement(name);
                        element.setTextContent(value);
                        root.appendChild(element);
                    }
                }
                for (Field Field : Fields) {
                    if (Field.getAnnotation(GetterFunctionName.class).name() != null) {
                        String gfn = Field.getAnnotation(GetterFunctionName.class).name();
                        Method gm = object.getClass().getMethod(gfn);
                        String value = gm.invoke(t).toString();
                        String name = Field.getName();
                        Element element = xml.createElement(name);
                        element.setTextContent(value);
                        root.appendChild(element);
                    }
                }
            }
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
