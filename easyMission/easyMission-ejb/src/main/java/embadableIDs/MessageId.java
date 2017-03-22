package embadableIDs;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MessageId
 *
 */
@Embeddable

public class MessageId implements Serializable {

	private int idSenderPK;
	private int idReceiverPK;
	private static final long serialVersionUID = 1L;

	public MessageId() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idReceiverPK;
		result = prime * result + idSenderPK;
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
		MessageId other = (MessageId) obj;
		if (idReceiverPK != other.idReceiverPK)
			return false;
		if (idSenderPK != other.idSenderPK)
			return false;
		return true;
	}

	public int getIdSenderPK() {
		return idSenderPK;
	}

	public void setIdSenderPK(int idSenderPK) {
		this.idSenderPK = idSenderPK;
	}

	public int getIdReceiverPK() {
		return idReceiverPK;
	}

	public void setIdReceiverPK(int idReceiverPK) {
		this.idReceiverPK = idReceiverPK;
	}
   
}
