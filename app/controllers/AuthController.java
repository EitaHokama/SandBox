package controllers;

import play.mvc.*;
import javax.inject.Inject;
import models.Login;
import play.data.Form;
import play.data.FormFactory;
import views.html.authentication.*;

public class AuthController extends Controller{
	@Inject
	private FormFactory formFactory;

	public Result index(){
		if(session("login") != null){
			return ok("あなたは既に " + session("login") + " としてログインしています");
		}
		return ok(index.render(formFactory.form(Login.class)));
	}

	public Result authenticate(){

		Form<Login> form = formFactory.form(Login.class).bindFromRequest();

		if(form.hasErrors()){
			return badRequest(index.render(form));
		}else{
			Login login = form.get();
			session("login",login.username);
			return ok("ようこそ " + login.username + " さん");
		}
	}

	public Result logout(){
		session().clear();
		return redirect(routes.AuthController.index());
	}
}
