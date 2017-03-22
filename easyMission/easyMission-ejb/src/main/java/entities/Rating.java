package entities;

import java.io.Serializable;
import javax.persistence.*;

import embadableIDs.RatingId;

/**
 * Entity implementation class for Entity: Rating
 *
 */
@Entity

public class Rating implements Serializable {

	@EmbeddedId
	private RatingId idRating;
	
	private float mark ;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinColumn(name="idRaterPK",insertable=false,updatable=false)
	private User rater;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="idRatedPK",insertable=false,updatable=false)
	private User rated;
	
	private static final long serialVersionUID = 1L;

	public Rating() {
		super();
	}

	public RatingId getIdRating() {
		return idRating;
	}

	public void setIdRating(RatingId idRating) {
		this.idRating = idRating;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	public User getRated() {
		return rated;
	}

	public void setRated(User rated) {
		this.rated = rated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRating == null) ? 0 : idRating.hashCode());
		result = prime * result + Float.floatToIntBits(mark);
		result = prime * result + ((rated == null) ? 0 : rated.hashCode());
		result = prime * result + ((rater == null) ? 0 : rater.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (idRating == null) {
			if (other.idRating != null)
				return false;
		} else if (!idRating.equals(other.idRating))
			return false;
		if (Float.floatToIntBits(mark) != Float.floatToIntBits(other.mark))
			return false;
		if (rated == null) {
			if (other.rated != null)
				return false;
		} else if (!rated.equals(other.rated))
			return false;
		if (rater == null) {
			if (other.rater != null)
				return false;
		} else if (!rater.equals(other.rater))
			return false;
		return true;
	}
	
   
}
