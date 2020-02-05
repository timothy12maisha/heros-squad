import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.eclipse.jetty.websocket.common.message.MessageAppender;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import static spark.Spark.get;
import spark.ModelAndView;

public class App {
    public static void main (String[] args){
        staticFileLocation("/public");
        post("/heros/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<hero> heros = hero.getAll();
            String heroesnames = request.queryParams("hname");
            String superPowerName = request.queryParams("sname");
            String superCodeName = request.queryParams("cname");
            String superSquadName = request.queryParams("sqname");
            hero newHero = new hero(heroesnames, superPowerName, superCodeName, superSquadName);
            return new ModelAndView(model, "Success-hero-registered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heros/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "register-hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/herosquads",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
           ArrayList<hero> heros = hero.getAll();
            model.put("heros",heros);
            return new ModelAndView(model, "heros-and-squads-list.hbs");
        },new HandlebarsTemplateEngine());
        get("/heros/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<hero> heros = hero.getAll();
            model.put("heros", heros);
            return new ModelAndView(model, "register-hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<hero> heros = hero.getAll();
            model.put("heros", heros);
            return new ModelAndView(model, "register-hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heros/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHerosToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero foundHero = hero.findById(idOfHerosToFind); //use it to find hero
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "heros-and-squads-list.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());

        get("/heros/:id/update", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfHerosToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfHerosToEdit);
            model.put("editPost", editHero);
            return new ModelAndView(model, "register-hero-form.hbs");
        } , new HandlebarsTemplateEngine());

        post("/heros/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = req.queryParams("content");
            int idOfHerosToEdit = Integer.parseInt(req.params("id"));
            hero editHero = hero.findById(idOfHerosToEdit);
            editHero.update(newContent); //don't forget me
            return new ModelAndView(model, "success-hero-registered.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heros/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHerosToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            hero deleteHero = hero.findById(idOfHerosToDelete); //use it to find post
            deleteHero.deletePost();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heros/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            hero.clearAllHeros();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
