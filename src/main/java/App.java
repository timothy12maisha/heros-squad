import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import spark.ModelAndView;


public class App {
    public static void main (String[] args){
        staticFileLocation("/public");
        post("/posts/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            hero newHero = new hero(content);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<hero> heros = hero.getAll();
            model.put("heros", heros);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heros/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heros/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero foundPost = hero.findById(idOfPostToFind); //use it to find hero
            model.put("post", foundPost); //add it to model for template to display
            return new ModelAndView(model, "post-detail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/posts/:id/update", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfPostToEdit);
            model.put("editPost", editHero);
            return new ModelAndView(model, "post-form.hbs");
        } , new HandlebarsTemplateEngine());
        post("/posts/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("content");
            int idOfPostToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfPostToEdit);
            editHero.update(newContent); //don't forget me
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero deleteHero = hero.findById(idOfPostToDelete); //use it to find post
            deleteHero.deleteHeros();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            hero.clearAllHeros();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
