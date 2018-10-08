package br.fatec.classmate.models;

public class Teacher
{
    private String name;

    public Teacher(String pName)
    {
        setName(pName);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


}
