package br.fatec.classmate.models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model
{
    ObjectContainer classTypes = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/classTypes.db4o");
    ObjectContainer classrooms = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/classrooms.db4o");
    ObjectContainer courses = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/courses.db4o");
    ObjectContainer teachers = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/teacher.db4o");
    ObjectContainer classSchedules = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/classSchedule.db4o");

    public List<Course> getCoursesByClassroom(int pFloor, int pRoom)
    {
        Query query = courses.query();
        query.constrain(Course.class);
        ObjectSet<Course> allCourses = query.execute();
        List<Course> matchedCourses = new ArrayList<Course>();

        if (allCourses.size() > 0)
        {
            for(Course c:allCourses){
                if (c.getClassroom().getFloor() == pFloor && c.getClassroom().getRoom() == pRoom)
                    matchedCourses.add(c);
            }
        }

        return matchedCourses;
    }

    public List<Course> getCoursesByTeacher(String pTeacher)
    {
        Query query = courses.query();
        query.constrain(Course.class);
        ObjectSet<Course> allCourses = query.execute();
        List<Course> matchedCourses = new ArrayList<Course>();

        if (allCourses.size() > 0)
        {
            for(Course c:allCourses){
                if (c.getTeacher().getName().equals(pTeacher))
                    matchedCourses.add(c);
            }
        }

        return matchedCourses;
    }

    /**
     * Add a new course to the database
     *
     * @param pCourse {@link Course} to be added
     * @return true if added to the database successfully
     */
    public boolean addCourse(Course pCourse){
        if(isCourseAvailable(pCourse.getName())){
            courses.store(pCourse);
            courses.commit();

            return true;
        }

        return false;
    }

    /**
     * Checks if there is already a course with the same name
     *
     * @param pCourseName the name to be checked if already exists
     * @return true if the name is available, therefore, if is not on the database
     */
    public boolean isCourseAvailable(String pCourseName)
    {
        Query query = courses.query();
        query.constrain(Course.class);
        ObjectSet<Course> allCourses = query.execute();

        for(Course c:allCourses){
            if(c.getName().equals(pCourseName)) return false;
        }

        return true;
    }

    /**
     * Add the given item to the database
     *
     * @param pClassType {@link ClassType} to be inserted on the database
     * @return true if inserted successfully to the database
     */
    public boolean addClassType(ClassType pClassType)
    {
        if(isClassTypeAvailable(pClassType.getName()))
        {
            classTypes.store(pClassType);
            classTypes.commit();

            return true;
        }

        return false;
    }

    /**
     * Checks if the given {@link ClassType} already exists on the database
     * @param pClassType {@link ClassType} to be checked
     * @return true if it is available
     */
    public boolean isClassTypeAvailable(String pClassType)
    {
        Query query = classTypes.query();
        query.constrain(ClassType.class);
        ObjectSet<ClassType> allTypes = query.execute();

        for(ClassType c:allTypes){
            if(c.getName().equals(pClassType)) return false;
        }

        return true;
    }

    /**
     * Return a List with all stored class types
     *
     * @return List of {@link ClassTypes}
     */
    public List<ClassType> getClassTypes(String pName)
    {
        Query query = classTypes.query();
        query.constrain(ClassType.class);
        ObjectSet<ClassType> allTypes = query.execute();
        List<ClassType> matchedTypes = new ArrayList<ClassType>();

        for(ClassType c:allTypes)
        {
            if (c.getName().equals(pName) || pName == null)
                matchedTypes.add(c);
        }

        return matchedTypes;
    }

    /**
     * Store a new {@link Classroom} on db
     *
     * @param pFloor floor of the classroom
     * @param pRoom room of the classroom
     * @return
     */
    public boolean addClassroom(Classroom pClassroom)
    {
        if (isClassroomAvailable(pClassroom))
        {
            classrooms.store(pClassroom);
            classrooms.commit();
            return true;
        }
        return false;
    }

    public boolean isClassroomAvailable(Classroom pClassroom)
    {
        Query query = classrooms.query();
        query.constrain(Classroom.class);
        ObjectSet<Classroom> allRooms = query.execute();

        for(Classroom c:allRooms){
            if(c.getFloor() == pClassroom.getFloor() && c.getRoom() == pClassroom.getRoom())
                return false;
        }
        return true;
    }

    public List<Classroom> getClassrooms(int pFilterFloor, int pFilterRoom)
    {
        Query query = classrooms.query();
        query.constrain(Classroom.class);
        ObjectSet<Classroom> allrooms = query.execute();
        List<Classroom> matchedRooms = new ArrayList<Classroom>();

        if (allrooms.size() > 0)
        {
            for(Classroom c:allrooms){
                if ((c.getFloor() == pFilterFloor || pFilterFloor == 0) && (c.getRoom() == pFilterRoom || pFilterRoom == 0))
                    matchedRooms.add(c);
            }
        }

        return matchedRooms;
    }




    public boolean addTeacher(Teacher pTeacher){
        if(isTeacherAvailable(pTeacher.getName())){
            teachers.store(pTeacher);
            teachers.commit();

            return true;
        }

        return false;
    }

    /**
     * Checks if there is already a course with the same name
     *
     * @param pTeacher the name to be checked if already exists
     * @return true if the name is available, therefore, if is not on the database
     */
    public boolean isTeacherAvailable(String pTeacher)
    {
        Query query = teachers.query();
        query.constrain(Teacher.class);
        ObjectSet<Teacher> allTeacher = query.execute();

        for(Teacher c:allTeacher){
            if(c.getName().equals(pTeacher)) return false;
        }

        return true;
    }

    public List<Teacher> getTeachers(String pName)
    {
        Query query = teachers.query();
        query.constrain(Teacher.class);
        ObjectSet<Teacher> allteachers = query.execute();
        List<Teacher> matchedTeachers = new ArrayList<Teacher>();

        if (allteachers.size() > 0)
        {
            for(Teacher c:allteachers){
                if ((c.getName().equals(pName) || pName == null))
                    matchedTeachers.add(c);
            }
        }

        return matchedTeachers;
    }


    /*************************************************************************
     * CLASS SCHEDULE
     *************************************************************************/

    public boolean addClassSchedule(ClassSchedule pClassSchedule){
        if(isClassScheduleAvailable(pClassSchedule)){
            classSchedules.store(pClassSchedule);
            classSchedules.commit();
            return true;
        }
        return false;
    }

    public boolean isClassScheduleAvailable(ClassSchedule pClassSchedule)
    {
        Query query = classSchedules.query();
        query.constrain(ClassSchedule.class);
        ObjectSet<ClassSchedule> allClassSchedules = query.execute();

        for(ClassSchedule c:allClassSchedules)
        {
            if(c.equals(pClassSchedule))
                return false;
        }

        return true;
    }

    public ClassSchedule getClassScheduleWithDayAndTime(DayOfWeek pDay, int pHour)
    {
        Query query = classSchedules.query();
        query.constrain(ClassSchedule.class);
        ObjectSet<ClassSchedule> allSchedules = query.execute();

        if (allSchedules.size() > 0)
        {
            for(ClassSchedule c:allSchedules){
                System.out.println("Actual Day   : "+c.toString());
                System.out.println("Expected Day : "+pDay+" - "+pHour);
                if (c.getWeekday().equals(pDay) && c.getHour() == pHour)
                    return c;
            }
        }
        return null;
    }

    public List<Course> getAllCourses()
    {
        Query query = courses.query();
        query.constrain(Course.class);
        ObjectSet<Course> allcourses = query.execute();

        return allcourses;
    }

    /**
     * Find an instance of {@link Course} based on the name of the course
     * @param pName name of the desired course
     * @return an instance of {@link Course}
     */
    public Course getCourseByName(String pName)
    {
        Query query = courses.query();
        query.constrain(Course.class);
        ObjectSet<Course> allcourses = query.execute();
        for (Course course : allcourses)
        {
            if (course.getName().equalsIgnoreCase(pName))
                return course;
        }
        return null;
    }



}
