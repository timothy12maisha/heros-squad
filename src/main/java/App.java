import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import spark.ModelAndView;


public class App {
    public static void main (String[] args){
        staticFileLocation("/public");
        post("/heros/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            hero newHero = new hero(content);
            return new ModelAndView(model, "Success-hero-registered.hbs");
        }, new HandlebarsTemplateEngine());
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            hero newHero = new hero(content);
            return new ModelAndView(model, "Success-squad-registered.hbs");
        }, new HandlebarsTemplateEngine());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<hero> heros = hero.getAll();
            model.put("heros", heros);
            return new ModelAndView(model, "register-hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "register-squad-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/heros/register-squad-form", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "register-squad-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/herosquads",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            String content = request.queryParams("content");
//            squad squad = new squad(content);
//            model.put("squad", squad);
            return new ModelAndView(model, "heros-and-squads-list.hbs");
        },new HandlebarsTemplateEngine());

        get("/heros/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHerosToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero foundHero = hero.findById(idOfHerosToFind); //use it to find hero
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "heros-and-squads-list.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadsToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero foundSquad = hero.findById(idOfSquadsToFind); //use it to find hero
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "heros-and-squads-list.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());
        get("/heros/:id/update", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfHerosToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfHerosToEdit);
            model.put("editPost", editHero);
            return new ModelAndView(model, "register-hero-form.hbs");
        } , new HandlebarsTemplateEngine());
        get("/squads/:id/update", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfSquadsToEdit = Integer.parseInt(req.params("id"));
            squad editSquads = squad.findById(idOfSquadsToEdit);
            model.put("editSquad", editSquads);
            return new ModelAndView(model, "register-squad-form.hbs");
        } , new HandlebarsTemplateEngine());
        post("/heros/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("content");
            int idOfHerosToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfHerosToEdit);
            editHero.update(newContent); //don't forget me
            return new ModelAndView(model, "success-hero-registered.hbs");
        }, new HandlebarsTemplateEngine());
//        get("/heros/:id/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfHerosToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
//            hero deleteHero = hero.findById(idOfHerosToDelete); //use it to find post
//            deleteHero.deleteHeros();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//        get("/heros/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            hero.clearAllHeros();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}
