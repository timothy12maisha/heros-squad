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

    }
}
