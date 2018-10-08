package br.fatec.classmate.models;

import java.util.LinkedList;
import java.util.List;

public class Course
{
    private String name;
    private String id;
    private Classroom classroom;
    private ClassType classType;
    private Teacher teacher;
    private List<ClassSchedule> schedules = new LinkedList<ClassSchedule>();

    public Course (String pId, String pName, Classroom pClassroom, ClassType pClassType, Teacher pTeacher, ClassSchedule... pSchedules)
    {
        setId(pId);
        setName(pName);
        setClassroom(pClassroom);
        setClassType(pClassType);
        setTeacher(pTeacher);
        setSchedules(pSchedules);
    }

    public Course (String pId, String pName, int pFloor, int pRoom, String pClassType, String pTeacher, ClassSchedule... pSchedules)
    {
        this(pId, pName, new Classroom(pFloor, pRoom), new ClassType(pClassType), new Teacher(pTeacher), pSchedules);
    }

    @Override
    public String toString()
    {
        return String.format("%s [Name: %s; Classroom: %s-%s, ClassType: %s, Teacher: %s]", getName(),
                getClassroom().getFloor(), getClassroom().getRoom(), getClassType().toString(), getTeacher().toString());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Classroom getClassroom()
    {
        return classroom;
    }

    public void setClassroom(Classroom classroom)
    {
        this.classroom = classroom;
    }

    public ClassType getClassType()
    {
        return classType;
    }

    public void setClassType(ClassType classType)
    {
        this.classType = classType;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher pTeacher)
    {
        this.teacher = pTeacher;
    }

    public List<ClassSchedule> getSchedules()
    {
        return schedules;
    }

    public void setSchedules(ClassSchedule[] pSchedules)
    {
        for (ClassSchedule classSchedule : pSchedules)
        {
            schedules.add(classSchedule);
        }
    }

    public void setSchedules(List<ClassSchedule> schedules)
    {
        this.schedules = schedules;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
