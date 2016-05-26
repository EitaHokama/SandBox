package controllers;

import models.CaseStudy;
import models.Employees;
import models.Gratitude_Card;
import models.Category;
import play.mvc.*;
import java.util.List;

import views.html.*;
import play.data.Form;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.Map;
import java.util.HashMap;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class TestController extends Controller {

	@Inject
	private FormFactory formFactory;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */


    public Result test2(){
    	//Employees emp = Employees.find.byId(1);
    	List<Gratitude_Card> gc = Gratitude_Card.find.all();

    	return ok(test2.render(gc, ""));
    }
    public Result test2Post(){
    	//Employees emp = Employees.find.byId(1);
    	List<Gratitude_Card> gc ;
    	//Form<FindForm> f = new Form(FindForm.class).bindFromRequest();
    	Map<String, String[]> params =request().body().asFormUrlEncoded();
    	SelectGC sel = new SelectGC(params);



    	List<Category> cat = Category.find.where().eq("category_name",params.get("category")[0]).findList();
    	if(!"カテゴリ".equals(params.get("category")[0])){
    		gc = Gratitude_Card.find.where().eq("category_id",
    				cat.get(0)
    			).findList();
    	}else{
    		gc = Gratitude_Card.find.all();
    	}
    	gc = new SelectGC(params).find();

    			/*(Category.find.where().
    					eq("category_name", params.get("category")[0])
    					.findList().category_id));*/

    	return ok(test2.render(gc, params.get("sender_department")[0]));
    }

    public Result makeJS(){
    	return ok(views.js.template.ichiran.render());
    }
    public Result syousai(){
    	Map<String, String[]> params =request().body().asFormUrlEncoded();
    	int iD = Integer.valueOf(params.get("CardNumber")[0]);
    	Gratitude_Card gc = Gratitude_Card.find.byId(iD);
    	return ok(syousai.render(gc,"" +iD));
    }
}
