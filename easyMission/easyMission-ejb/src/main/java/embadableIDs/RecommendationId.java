package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RecommendationId
 *
 */
@Embeddable
public class RecommendationId implements Serializable {

	private int idRecommendedPK ;
	private int idRecommenderPK;
	
	private static final long serialVersionUID = 1L;

	public RecommendationId() {
		super();
	}
	

	public RecommendationId(int idRecommendedPK, int idRecommenderPK) {
		super();
		this.idRecommendedPK = idRecommendedPK;
		this.idRecommenderPK = idRecommenderPK;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRecommendedPK;
		result = prime * result + idRecommenderPK;
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
		RecommendationId other = (RecommendationId) obj;
		if (idRecommendedPK != other.idRecommendedPK)
			return false;
		if (idRecommenderPK != other.idRecommenderPK)
			return false;
		return true;
	}

	public int getIdRecommendedPK() {
		return idRecommendedPK;
	}

	public void setIdRecommendedPK(int idRecommendedPK) {
		this.idRecommendedPK = idRecommendedPK;
	}

	public int getIdRecommenderPK() {
		return idRecommenderPK;
	}

	public void setIdRecommenderPK(int idRecommenderPK) {
		this.idRecommenderPK = idRecommenderPK;
	}
   
}
