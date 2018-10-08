package br.fatec.classmate.controller;

import static spark.Spark.get;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import br.fatec.classmate.models.ClassType;
import br.fatec.classmate.models.Course;
import br.fatec.classmate.models.Model;
import spark.Request;
import spark.Response;
import spark.Route;

public class RestController
{
    private Model model;

    public RestController(Model pModel)
    {
        model = pModel;
    }

    public void getClassTypes()
    {
        get("/classtypes", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");

                List<ClassType> allTypes = model.getClassTypes(null);
                return new Gson().toJsonTree(allTypes);
//                JSONArray jsonResult = new JSONArray();
//
//                for (ClassType classType : allTypes)
//                {
//                    JSONObject jsonObj = new JSONObject();
//                    jsonObj.put("name", classType.getName());
//                    jsonResult.put(jsonObj);
//                }
//                return jsonResult;
             }
          });
    }

    public void getCoursesByClassroom()
    {
        get("/courses/byroom/:floor/:room", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");
                Integer floor = Integer.parseInt(request.params(":floor"));
                Integer room = Integer.parseInt(request.params(":room"));

                List<Course> allCourses = model.getCoursesByClassroom(floor, room);

                return new Gson().toJsonTree(allCourses);
             }
          });
    }

    public void getCoursesByTeacher()
    {
        get("/courses/byteacher/:teacher", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");
                String teacher = request.params(":teacher");

                List<Course> allCourses = model.getCoursesByTeacher(teacher);

                return new Gson().toJsonTree(allCourses);
             }
          });
    }

    public void getAllCourses()
    {
        get("/courses/all", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");

                List<Course> allCourses = model.getAllCourses();
                return new Gson().toJsonTree(allCourses);
             }
          });
    }

    public void getAllCoursesNames()
    {
        get("/courses/all/names", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");

                List<Course> allCourses = model.getAllCourses();
                List<String> allCourseNames = allCourses.stream().map(x -> x.getName()).collect(Collectors.toList());
                return new Gson().toJsonTree(allCourseNames);
             }
          });
    }

    public void getCourseInfoByName()
    {
        get("/course/:name", new Route()
        {
            @Override
            public Object handle(final Request request, final Response response){
                response.header("Access-Control-Allow-Origin", "*");
                String name = request.params(":name");

                Course course = model.getCourseByName(name);

                return new Gson().toJsonTree(course);
             }
          });
    }

}
