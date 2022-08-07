import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) throws IOException {

        //home page
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //create hero page
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroform.hbs");
        }, new HandlebarsTemplateEngine());

        //submit a new hero - redirect to success page
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("heroName");
            int age = Integer.parseInt(request.queryParams("heroAge"));
            String power = request.queryParams("heroSpecialPower");
            String weakness = request.queryParams("heroWeakness");
            Hero newHero = new Hero(name, age, power, weakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //create squad page
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "squadform.hbs");
        }, new HandlebarsTemplateEngine());

        //new squad page
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> squadlessHeroes = new ArrayList<>();
            for (Hero hero : Hero.getHeroList()) {
                if (hero.getSquadGroup().equals("")) {
                    squadlessHeroes.add(hero);
                }
            }
            model.put("squadlessHeroes", squadlessHeroes);
            return new ModelAndView(model, "squadform.hbs");
        }, new HandlebarsTemplateEngine());

        //create a new squad page - redirect to success page
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Hero> squadlessHeroes = new ArrayList<>();
            for (Hero hero : Hero.getHeroList()) {
                if (hero.getSquadGroup().equals("")) {
                    squadlessHeroes.add(hero);
                }
            }

            String name = request.queryParams("squadName");
            String cause = request.queryParams("squadCause");
            String heroName = request.queryParams("founder");
            Hero squadFounder = null;
            for (Hero hero : squadlessHeroes) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    squadFounder = hero;
                    break;
                }
            }
            assert squadFounder != null;
            Squad newSquad = new Squad(name, cause, squadFounder);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String uniqueId = request.queryParams("uniqueId");
            request.session().attribute("uniqueId", uniqueId);
            model.put("uniqueId", uniqueId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //display heroes list
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("hero", Hero.getHeroList());
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "herolist.hbs");
        }, new HandlebarsTemplateEngine());

        //display squads list
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("squad", Squad.getSquadsList());
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "squadlist.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
