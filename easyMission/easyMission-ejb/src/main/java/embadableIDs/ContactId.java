package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ContactId
 *
 */

@Embeddable
public class ContactId implements Serializable {

	private int idUserPK ;
	private int idContactPK;
	
	private static final long serialVersionUID = 1L;

	public ContactId() {
		super();
	}

	public int getIdUserPK() {
		return idUserPK;
	}

	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
	}

	public int getIdContactPK() {
		return idContactPK;
	}

	public void setIdContactPK(int idContactPK) {
		this.idContactPK = idContactPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idContactPK;
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
		ContactId other = (ContactId) obj;
		if (idContactPK != other.idContactPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}
   
}
