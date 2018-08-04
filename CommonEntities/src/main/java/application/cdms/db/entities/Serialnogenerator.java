package application.cdms.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SERIAL_NO_GENERATOR",schema="CDMS")
public class Serialnogenerator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SEQ_NAME")
	private String seqName;
	
	@Column(name="SEQ_VALUE")
	private Long seqValue;
	
	@Column(name="SEQ_PREFIX")
	private String seqPrefix;
	
	@Column(name="seq_size")
	private int seqSize;

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public Long getSeqValue() {
		return seqValue;
	}

	public void setSeqValue(Long seqValue) {
		this.seqValue = seqValue;
	}

	public String getSeqPrefix() {
		return seqPrefix;
	}

	public void setSeqPrefix(String seqPrefix) {
		this.seqPrefix = seqPrefix;
	}

	public int getSeqSize() {
		return seqSize;
	}

	public void setSeqSize(int seqSize) {
		this.seqSize = seqSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
