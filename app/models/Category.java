package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;

import play.data.validation.Constraints.Required;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category extends Model {
	@Id

	public Integer category_id;
	@Required
	public String category_name;

	@OneToMany(mappedBy="category_id")
	public List<Gratitude_Card> gc = new ArrayList<>();

	public static Find<Integer,Category> find = new Find<Integer,Category>(){};
}
