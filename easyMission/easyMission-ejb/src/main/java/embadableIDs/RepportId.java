package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReportId
 *
 */
@Embeddable

public class RepportId implements Serializable {

	private int idUserPK;
	private int idMisssionPK;
	
	private static final long serialVersionUID = 1L;

	public RepportId() {
		super();
	}
	

	public RepportId(int idUserPK, int idMisssionPK) {
		super();
		this.idUserPK = idUserPK;
		this.idMisssionPK = idMisssionPK;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMisssionPK;
		result = prime * result + idUserPK;
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
		RepportId other = (RepportId) obj;
		if (idMisssionPK != other.idMisssionPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}

	public int getIdUserPK() {
		return idUserPK;
	}

	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
	}

	public int getIdMisssionPK() {
		return idMisssionPK;
	}

	public void setIdMisssionPK(int idMisssionPK) {
		this.idMisssionPK = idMisssionPK;
	}
   
}
