package controllers;

import models.CaseStudy;
import models.Category;
import models.Department;
import models.Employees;
import models.Gratitude_Card;
import play.mvc.*;
import java.util.List;
import java.util.Map;
import java.util.Date;
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

  /*  public Result bbs() {
    	//Employees emp = Employees.find.byId(1);
    	Gratitude_Card gc = Gratitude_Card.find.byId(1);
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(bbs.render(gc,cs));
    }*/
    public Result bbs(){
    	List<Gratitude_Card> gc = Gratitude_Card.find.all();
    	Map map = new HashMap<String, String[]>();
    	gc= Gratitude_Card.find.all();

    	return ok(bbs.render(gc, "", map));
    }
    public Result bbsPost(){
    	List<Gratitude_Card> gc ;

    	Map<String, String[]> params =request().body().asFormUrlEncoded();
    	SelectGC sel = new SelectGC(params);
    	gc = sel.findBBS();

    	return ok(bbs.render(gc, "",params));
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
    /*public Result typical(){
    	//Employees emp = Employees.find.byId(1);
    	Gratitude_Card gc = Gratitude_Card.find.byId(1);
    	CaseStudy cs = CaseStudy.find.byId(1);
    	return ok(typical.render(gc,cs));
    }*/
    public Result typical(){
    	List<Gratitude_Card> gc = Gratitude_Card.find.all();
    	Map map = new HashMap<String, String[]>();
    	gc= Gratitude_Card.find.all();
    	SelectGC sel = new SelectGC(map);
    	gc = sel.find();

    	return ok(typical.render(gc, "", map));
    }
    public Result typicalPost(){
    	List<Gratitude_Card> gc ;

    	Map<String, String[]> params =request().body().asFormUrlEncoded();
    	SelectGC sel = new SelectGC(params);
    	sel.controlCS();
    	gc = sel.find();

    	return ok(typical.render(gc, params.get("start_date")[0],params));
    }

    public Result creation() {
    	///DEPARTMENT DT = DEPARTMENT.find.byId(1);
    	//CATEGORY CG = CATEGORY.find.byId(1);
    	//return ok(test.render(gc,cs));
    	return ok(creation.render("",""));
    }
    public Result creationPost(){
    	Map<String,String[]> params = request().body().asFormUrlEncoded();
    	Gratitude_Card gc =new Gratitude_Card();

    	gc.sender_id= Employees.find.byId(1);//セッションからidを求めること
    	gc.date = new Date();
    	boolean category_error=true;
    	Category cs = new Category();

		try {
			cs =Category.find.where().eq("category_name", params.get("カテゴリ")[0]).findUnique();
			if(cs !=null){
				category_error=false;
				gc.category_id=cs;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
    	if(category_error){
    		return badRequest(creation.render("カテゴリ", "カテゴリを入力してください。"));
    	}
    	Department dep = new Department();
    	boolean dep_error=true;
    	try{
    		dep =Department.find.where().eq("department_name", params.get("sel_bunruiA")[0]).findUnique();
    		if(dep !=null){
    			dep_error=false;
    		}

    	} catch (Exception e){

    	}
    	if(dep_error){
    		return  badRequest(creation.render("sel_bunruiA", "部署を入力してください。"));
    	}
    	Employees emp = new Employees();
    	boolean emp_error=true;
    	try{
    		emp =Employees.find.where().eq("del_flag",0).eq("department_id", dep).eq("name", params.get("sel_bunruiB")[0]).findUnique();
    		if(emp.employees_id.intValue() == gc.sender_id.employees_id.intValue()){
    			return  badRequest(creation.render("sel_bunruiA", "自分に感謝カードを贈ることはできません。"));
    		}
    		if(emp !=null){
    			emp_error=false;
    			gc.receiver_id=emp;
    		}


    	} catch (Exception e){

    	}
    	if(emp_error){
    		return  badRequest(creation.render("sel_bunruiA", "名前を入力してください。"));
    	}
    	boolean title_error=true;
    	try{
    		String title = params.get("title")[0];
    		if(title.length()<2){
    			return badRequest(creation.render("title","タイトルが短すぎます(2文字以上)"));

    		}else if(title.length()>=30){
    			return badRequest(creation.render("title","タイトルが長すぎます(30文字以下)"));

    		}else{
    			title_error=false;
    			gc.card_title=title;
    		}
    	} catch (Exception e){

    	}
    	if(title_error){
    		return badRequest(creation.render("title","タイトルを入力してください。"));
    	}
    	boolean content_error=true;
    	try{
    		String content = params.get("kanso")[0];
    		if(content.length()<2){
    			return badRequest(creation.render("title","内容が短すぎます(2文字以上)"));

    		}else if(content.length()>=300){
    			return badRequest(creation.render("title","内容が長すぎます(300文字以下)"));

    		}else{
    			content_error=false;
    			gc.card_content=content;
    		}
    	} catch(Exception e){

    	}
    	if(content_error){
    		return badRequest(creation.render("title","内容を入力してください。"));
    	}

    	gc.save();

    	return ok(creation.render("カテゴリ", ""+cs.category_id));

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

