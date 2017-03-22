package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RatingId
 *
 */
@Embeddable

public class RatingId implements Serializable {

	private int idRaterPK;
	private int idRatedPK;

	private static final long serialVersionUID = 1L;

	public RatingId() {
		super();
	}

	public int getIdRaterPK() {
		return idRaterPK;
	}

	public void setIdRaterPK(int idRaterPK) {
		this.idRaterPK = idRaterPK;
	}

	public int getIdRatedPK() {
		return idRatedPK;
	}

	public void setIdRatedPK(int idRatedPK) {
		this.idRatedPK = idRatedPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idRatedPK;
		result = prime * result + idRaterPK;
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
		RatingId other = (RatingId) obj;
		if (idRatedPK != other.idRatedPK)
			return false;
		if (idRaterPK != other.idRaterPK)
			return false;
		return true;
	}

}
