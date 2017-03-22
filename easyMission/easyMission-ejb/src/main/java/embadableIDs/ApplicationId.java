package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ApplicationId
 *
 */
@Embeddable

public class ApplicationId implements Serializable {

	private int idApplicantPK;
	private int idMissionPK;
	private static final long serialVersionUID = 1L;

	public ApplicationId() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idApplicantPK;
		result = prime * result + idMissionPK;
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
		ApplicationId other = (ApplicationId) obj;
		if (idApplicantPK != other.idApplicantPK)
			return false;
		if (idMissionPK != other.idMissionPK)
			return false;
		return true;
	}

	public int getIdApplicantPK() {
		return idApplicantPK;
	}

	public void setIdApplicantPK(int idApplicantPK) {
		this.idApplicantPK = idApplicantPK;
	}

	public int getIdMissionPK() {
		return idMissionPK;
	}

	public void setIdMissionPK(int idMissionPK) {
		this.idMissionPK = idMissionPK;
	}
 
	
}
