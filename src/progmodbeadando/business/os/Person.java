package progmodbeadando.business.os;

import progmodbeadando.business.GetterFunctionName;

public abstract class Person {
    @GetterFunctionName(name="getID")
    protected String ID;
    @GetterFunctionName(name="getName")
    protected String name;


    public String getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
