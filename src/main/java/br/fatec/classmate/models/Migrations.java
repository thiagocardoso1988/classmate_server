package br.fatec.classmate.models;

import java.time.DayOfWeek;

public class Migrations
{
    private static Model model;

    private Migrations() {}

    public static void migrate(Model pModel)
    {
        model = pModel;
        migrateSchedules();
        migrateTeacher();
        migrateClassTypes();
        migrateClassrooms();
        migrateCourses();
    }

    private static void migrateSchedules()
    {
        DayOfWeek[] workDays = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY};
        for (DayOfWeek d: workDays)
        {
            model.addClassSchedule(new ClassSchedule(d, 18, 45));
            model.addClassSchedule(new ClassSchedule(d, 19, 35));
            model.addClassSchedule(new ClassSchedule(d, 20, 25));
            model.addClassSchedule(new ClassSchedule(d, 21, 25));
            model.addClassSchedule(new ClassSchedule(d, 22, 15));
        }
    }

    private static void migrateTeacher()
    {
        model.addTeacher(new Teacher("GIULIANO BERTOTI"));
        model.addTeacher(new Teacher("LUCAS NADALETE"));
        model.addTeacher(new Teacher("TERESINHA NOGUEIRA"));
        model.addTeacher(new Teacher("REINALDO ARAKAKI"));
        model.addTeacher(new Teacher("CARLOS GARCIA"));
    }

    private static void migrateClassTypes()
    {
        model.addClassType(new ClassType("Tecnologia"));
        model.addClassType(new ClassType("Exatas"));
        model.addClassType(new ClassType("Humanas"));
        model.addClassType(new ClassType("Logica"));
    }

    private static void migrateClassrooms()
    {
        model.addClassroom(new Classroom(4, 1));
        model.addClassroom(new Classroom(2, 9));
        model.addClassroom(new Classroom(4, 12));
    }

    private static void migrateCourses()
    {
        final ClassType ctHumanas = model.getClassTypes("Humanas").get(0);
        final ClassType ctTecnologia = model.getClassTypes("Tecnologia").get(0);

        final Classroom cr0209 = model.getClassrooms(2, 9).get(0);
        final Classroom cr0412 = model.getClassrooms(4, 12).get(0);
        final Classroom cr0401 = model.getClassrooms(4, 1).get(0);

        final Teacher teGiulianoBertoti = model.getTeachers("GIULIANO BERTOTI").get(0);
        final Teacher teLucasNadalete = model.getTeachers("LUCAS NADALETE").get(0);
        final Teacher teTeresinhaNogueira = model.getTeachers("TERESINHA NOGUEIRA").get(0);
        final Teacher teReinaldoArakaki = model.getTeachers("REINALDO ARAKAKI").get(0);
        final Teacher teCarlosGarcia = model.getTeachers("CARLOS GARCIA").get(0);

        model.addCourse(new Course("IPP002", "PADROES DE PROJETOS", cr0401, ctTecnologia, teGiulianoBertoti,
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 20),
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 21),
                model.getClassScheduleWithDayAndTime(DayOfWeek.WEDNESDAY, 21),
                model.getClassScheduleWithDayAndTime(DayOfWeek.WEDNESDAY, 22)));

        model.addCourse(new Course("TTG001", "METODOLOGIA DE PESQUISA CIENTIFICO-TECNOLOGICA", cr0401, ctTecnologia, teGiulianoBertoti,
                model.getClassScheduleWithDayAndTime(DayOfWeek.WEDNESDAY, 18),
                model.getClassScheduleWithDayAndTime(DayOfWeek.WEDNESDAY, 19)));

        model.addCourse(new Course("TTG021", "PROJETO TG1", cr0401, ctTecnologia, teGiulianoBertoti,
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 19),
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 20)));

        model.addCourse(new Course("ILP010", "LINGUAGEM DE PROGRAMACAO", cr0401, ctTecnologia, teReinaldoArakaki,
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 7),
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 8),
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 7),
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 8)));

        model.addCourse(new Course("IBD184", "LABORATORIO DESENVOLVIMENTO BD 4", cr0412, ctTecnologia, teLucasNadalete,
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 18),
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 19),
                model.getClassScheduleWithDayAndTime(DayOfWeek.FRIDAY, 18),
                model.getClassScheduleWithDayAndTime(DayOfWeek.FRIDAY, 19)));
//
        model.addCourse(new Course("LIN400", "INGLES 4", cr0209, ctHumanas, teTeresinhaNogueira,
                model.getClassScheduleWithDayAndTime(DayOfWeek.MONDAY, 22),
                model.getClassScheduleWithDayAndTime(DayOfWeek.WEDNESDAY, 20)));

        model.addCourse(new Course("LIN500", "INGLES 5", cr0209, ctHumanas, teTeresinhaNogueira,
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 21),
                model.getClassScheduleWithDayAndTime(DayOfWeek.TUESDAY, 22)));
    }
}
