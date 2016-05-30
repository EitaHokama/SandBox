package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import models.Category;
import models.Employees;
import models.Gratitude_Card;

/**
 * params 検索に使用するパラメーターを格納(検索前に必ずnewで初期化すること)
 * name 感謝カード各項目の論理名称を格納
 */
public class SelectGC {

	private Map<String, String[]> params;
	public static final Map<String, String> name = new HashMap<String, String>(){
		{
			put("sender_department", "送信部署");
			put("sender", "送信者");
			put("receiver_department", "受信部署");
			put("receiver","受信者");
			put("date", "日付");
			put("title", "タイトル");
			put("category", "カテゴリ");
			put("date_range","日付範囲");
		}
	};

	SelectGC(Map<String, String[]> params){
		this.params = params;
	}

	public List<Gratitude_Card> find(){
		List<Gratitude_Card> gc;
		ExpressionList<Gratitude_Card> gcWhere;


		/*Query<> cat = Category.find.where().eq("category_name",params.get("category")[0]).findList();
    	if(!"カテゴリ".equals(params.get("category")[0])){
    		gc = Gratitude_Card.find.where().eq("category_id",
    				cat.get(0)
    			).findList();
    	}else{
    		gc = Gratitude_Card.find.all();
    	}*/
		// com...EbeanList を戻り値に持つメソッドを連結する
		//gc= Gratitude_Card.find.all();

		//gc = sortDate(Gratitude_Card.find.all());
		gcWhere=makeWhere();
		gcWhere=findCategory(gcWhere);
		gcWhere=findDepartment(gcWhere,"send");//send or rec
		gcWhere=findDepartment(gcWhere,"rec");
		gcWhere=findEmployees(gcWhere,"send");
		gcWhere=findEmployees(gcWhere,"rec");

		gc=sortDate(gcWhere);

		return gc;
	}
	public List<Gratitude_Card> findRec(Employees emp){
		List<Gratitude_Card> gc;
		ExpressionList<Gratitude_Card> gcWhere;

		gcWhere=makeWhere();
		gcWhere=findCategory(gcWhere);
		gcWhere=findDepartment(gcWhere,"send");//send or rec
		gcWhere=findEmployees(gcWhere,"send");
		gcWhere=gcWhere.eq("receiver_id.name" , emp.name);
		gc=sortDate(gcWhere);

		return gc;
	}
	public List<Gratitude_Card> findSend(Employees emp){
		List<Gratitude_Card> gc;
		ExpressionList<Gratitude_Card> gcWhere;

		gcWhere=makeWhere();
		gcWhere=findCategory(gcWhere);
		gcWhere=findDepartment(gcWhere,"rec");//send or rec
		gcWhere=findEmployees(gcWhere,"rec");
		gcWhere=gcWhere.eq("sender_id.name" , emp.name);
		gc=sortDate(gcWhere);

		return gc;
	}

	private ExpressionList<Gratitude_Card> makeWhere(){
		ExpressionList<Gratitude_Card> gcWhere = Gratitude_Card.find.where();
		gcWhere=
				Ebean
				.find(Gratitude_Card.class)
					.fetch("sender_id")
						.fetch("sender_id.department_id")
					.fetch("receiver_id")
						.fetch("receiver_id.department_id")
					.fetch("category_id")
					.fetch("cs")
				.where();
		return gcWhere;
	}

	public List<Gratitude_Card> sortDate(ExpressionList<Gratitude_Card> gc){

		return gc.orderBy("date DESC").findList();
	}
	public List<Gratitude_Card> sortDate(List<Gratitude_Card> gc){

		return Gratitude_Card.find.orderBy("date DESC").findList();
	}

	private ExpressionList<Gratitude_Card> findCategory(ExpressionList<Gratitude_Card> gc){
		String buf;
		if(params.get("category") == null){
			buf = name.get("category");
		}else{
			buf=params.get("category")[0];
		}
		if(name.get("category").equals(buf)){
			return gc;
		}else{
			List<Category> cat
			= Category.find.where().
			eq("category_name", buf).findList();
			return gc.eq("category_id", cat.get(0));
		}



	}

	private ExpressionList<Gratitude_Card> findDepartment(ExpressionList<Gratitude_Card> gc,String mode){
		String modeSQL;
		if("send".equals(mode)){
			mode="sender_department";
			modeSQL= "sender_id";
			//return gc.eq("card_title","カード1");
		}else if("rec".equals(mode)){
			mode="receiver_department";
			modeSQL= "receiver_id";
		}else{//モードが検出できなければそのままスルー
			return gc.eq("card_title","カード1");
		}
		String buf;
		if(params.get(mode) == null){
			buf = name.get(mode);
		}else{
			buf=params.get(mode)[0];
		}
		if(name.get(mode).equals(buf)){
			return gc;
		}else{
			gc.eq(modeSQL+".department_id.department_name" ,buf);


			return gc;
		}



	}
	private ExpressionList<Gratitude_Card> findEmployees(ExpressionList<Gratitude_Card> gc,String mode){
		String modeSQL;
		if("send".equals(mode)){
			mode="sender";
			modeSQL= "sender_id";
			//return gc.eq("card_title","カード1");
		}else if("rec".equals(mode)){
			mode="receiver";
			modeSQL= "receiver_id";
		}else{//モードが検出できなければそのままスルー
			return gc;
		}
		String buf;
		if(params.get(mode) == null){
			buf = name.get(mode);
		}else{
			buf=params.get(mode)[0];
		}
		if(name.get(mode).equals(buf)){
			return gc;
		}else{
			gc.eq(modeSQL+".name" ,buf);


			return gc;
		}



	}


}