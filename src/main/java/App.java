import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //create hero page
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroform.hbs");
        }, new HandlebarsTemplateEngine());

        //submit a new hero - redirect to success page
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
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

            String name = request.queryParams("name");
            String cause = request.queryParams("cause");
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

/*        //get: retrieve all heroes and squads
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("allHeroes", Hero.getHeroList());
            model.put("allSquads", Squad.getSquadsList());
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroes-squads.hbs");
        }, new HandlebarsTemplateEngine());*/

        //display heroes list
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero foundHero = Hero.findHero(itemId);
            model.put("hero", foundHero);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "herolist.hbs");
        }, new HandlebarsTemplateEngine());

        //display squads list
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            model.put("squad", foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "squadlist.hbs");
        }, new HandlebarsTemplateEngine());

        /*//Post: update hero details
        post("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero updateHero = Hero.findHero(itemId);
            updateHero.updateName(request.queryParams("name"));
            updateHero.updateAge(Integer.parseInt(request.queryParams("age")));
            updateHero.updatePower(request.queryParams("power"));
            updateHero.updateWeakness(request.queryParams("weakness"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: update hero details
        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero updateHero = Hero.findHero(itemId);
            model.put("updateHero", updateHero);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: remove single hero
        get("/heroes/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Hero.deleteHero(itemId);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "heroes-squads.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: Update squad members by recruiting
        post("/squads/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            String heroName = request.queryParams("addHero");
            Hero heroToAdd = null;
            for (Hero hero : Hero.getHeroList()) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    heroToAdd = hero;
                    break;
                }
            }
            foundSquad.changeHeroSquad(heroToAdd, foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: Update squad
        get("/squads/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            List<Hero> nonMembers = new ArrayList<>();
            for (Hero hero : Hero.getHeroList()) {
                if (!hero.getSquadGroup().equalsIgnoreCase(foundSquad.getName())) {
                    nonMembers.add(hero);
                }
            }
            model.put("nonMembers", nonMembers);
            model.put("squad", foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "update-form.hbs");
        }, new HandlebarsTemplateEngine());

        //Post: Update squad by removal of members when full
        post("/squads/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            String heroName = request.queryParams("removeHero");
            Hero heroToRemove = null;
            for (Hero hero : Hero.getHeroList()) {
                if (hero.getName().equalsIgnoreCase(heroName)) {
                    heroToRemove = hero;
                    break;
                }
            }
            foundSquad.removeMember(heroToRemove);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: get list of current members in squad
        get("/squads/:id/remove", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int itemId = Integer.parseInt(request.params(":id"));
            Squad foundSquad = Squad.findSquad(itemId);
            model.put("members", foundSquad.getMembers());
            model.put("squad", foundSquad);
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "update-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: Route to the frequently asked questions template
        get("/faqs", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("uniqueId", request.session().attribute("uniqueId"));
            return new ModelAndView(model, "faqs.hbs");
        }, new HandlebarsTemplateEngine());*/

    }
}