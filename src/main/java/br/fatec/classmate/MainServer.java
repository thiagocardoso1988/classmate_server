package br.fatec.classmate;

import static spark.Spark.*;

import br.fatec.classmate.controller.RestController;
import br.fatec.classmate.models.Migrations;
import br.fatec.classmate.models.Model;

public class MainServer
{
    private static Model model = new Model();

    public static void main(String[] args)
    {
        // Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 8080;
        }
//        ipAddress("172.20.10.4");
//        ipAddress("192.168.56.1");
        port(port);

        initializeModel();
        staticFileLocation("/static");
        RestController controller = new RestController(model);

        controller.getClassTypes();
        controller.getCoursesByClassroom();
        controller.getCoursesByTeacher();
        controller.getAllCourses();
        controller.getAllCoursesNames();
        controller.getCourseInfoByName();
    }

    private static void initializeModel()
    {
        Migrations.migrate(model);
    }

}
