package progmodbeadando.service;

import progmodbeadando.business.Doctor;
import progmodbeadando.business.ReadMethodName;
import progmodbeadando.business.SaveMethodName;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Service <T>{
    //Bejövő adatok ellenőrzése, metódusok hivása ha az adatok helyesek ?Generikus?

    T object;
    public Service(T object){ this.object = object; }

    public void list(ArrayList<T> list){
        for(T element : list){
            System.out.println(element.toString());
        }
    }

    public T newObject(ArrayList<String> list, ArrayList<T> objects){
        Method[] ms = object.getClass().getDeclaredMethods();
        try {
            for (int i = 0; i < ms.length; i++) {
                if (ms[i].getName().equals("newObject")) {
                    objects.add((T) ms[i].invoke(null,list,objects));
                    return (T) ms[i].invoke(null,list,objects);
                }
            }

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return objects.get(0);
    }

    public ArrayList<T> read(){
        try {
            if(object.getClass().getAnnotation(ReadMethodName.class).name() != null){
                String mn =object.getClass().getAnnotation(ReadMethodName.class).name();
                Method m = object.getClass().getMethod(mn);
                ArrayList <T> list = (ArrayList<T>) m.invoke(null , null);
                return list;
            }
        }
        catch(NoSuchMethodException ex){
            System.out.println(ex);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(ArrayList<T> list){
        try{
            if(object.getClass().getAnnotation(SaveMethodName.class).name() != null){
                String mn =object.getClass().getAnnotation(SaveMethodName.class).name();
                Method[] ms = object.getClass().getDeclaredMethods();
                for(int i = 0;i<ms.length;i++){
                    if(ms[i].getName().equals(mn)) {
                        ms[i].invoke(null, list);
                    }

                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            System.err.println("HIBA");
            System.out.println(ex);
        }
    }

}
