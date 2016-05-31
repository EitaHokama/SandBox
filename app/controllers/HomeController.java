package controllers;

import models.CaseStudy;

import models.Employees;
import models.Gratitude_Card;
import play.mvc.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.inject.Inject;
import views.html.*;
import java.text.SimpleDateFormat;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints.Required;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Security.Authenticated(Secured.class)
public class HomeController extends Controller {
@Inject
private FormFactory formFactory;
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render());
    }

    public Result bbs() {
    	//Employees emp = Employees.find.byId(1);
    	Gratitude_Card gc = Gratitude_Card.find.byId(1);
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(bbs.render(gc,cs));
    }
    public Result valuation() {
    	//Employees emp = Employees.find.byId(1);
    	Gratitude_Card gc = Gratitude_Card.find.byId(1);
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(valuation.render(gc,cs));
    }

    public Result test(){
    	//Employees emp = Employees.find.byId(1);
    	List<Gratitude_Card> gc =
    			Gratitude_Card.find.where().orderBy("date DESC").findList();
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(test.render(gc,cs));
    }

    public Result login(){
    	return ok(login.render());
    }
    public Result typical(){
    	//Employees emp = Employees.find.byId(1);
    	Gratitude_Card gc = Gratitude_Card.find.byId(1);
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(typical.render(gc,cs));
    }

    public Result creation() {
    	///DEPARTMENT DT = DEPARTMENT.find.byId(1);
    	//CATEGORY CG = CATEGORY.find.byId(1);
    	//return ok(test.render(gc,cs));
    	return ok(creation.render());
    }

    public Result kanri() {
        return ok(kanri.render());

    }
    public Result valuation_detailed() {
        return ok(valuation_detailed.render());
    }

    public Result createTest(){
    	Employees emp = formFactory.form(Employees.class).bindFromRequest().get();
    	emp.save();
    	return redirect(routes.HomeController.test());
    }

}

