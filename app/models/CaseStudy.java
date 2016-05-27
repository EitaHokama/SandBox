package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Find;

import play.data.validation.Constraints.Required;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CaseStudy extends Model {
	@Id
	public Integer case_id;
	@Required
	public Integer year;
	@Required
	public Integer month;

	@ManyToMany(mappedBy="cs")
    public List<Gratitude_Card> gc;

    public static Find<Integer,CaseStudy> find = new Find<Integer,CaseStudy>(){};
}
