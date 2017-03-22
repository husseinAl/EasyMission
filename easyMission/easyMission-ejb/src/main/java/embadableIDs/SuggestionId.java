package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SuggestionId
 *
 */
@Embeddable

public class SuggestionId implements Serializable {

	
	private int idEmployerPK;
	private int idWorkerPK;
	private static final long serialVersionUID = 1L;

	public SuggestionId() {
		super();
	}   
	public int getIdEmployerPK() {
		return this.idEmployerPK;
	}

	public void setIdEmployerPK(int idEmployerPK) {
		this.idEmployerPK = idEmployerPK;
	}   
	public int getIdWorkerPK() {
		return this.idWorkerPK;
	}

	public void setIdWorkerPK(int idWorkerPK) {
		this.idWorkerPK = idWorkerPK;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmployerPK;
		result = prime * result + idWorkerPK;
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
		SuggestionId other = (SuggestionId) obj;
		if (idEmployerPK != other.idEmployerPK)
			return false;
		if (idWorkerPK != other.idWorkerPK)
			return false;
		return true;
	}
	
	
   
}
