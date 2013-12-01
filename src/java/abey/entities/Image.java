package abey.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Anthony
 */
@Entity
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	private byte[] original;

	@Lob
	private byte[] thumbnail;

	private String mimetypeOriginal;
	
	private String mimetypeThumbnail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getOriginal() {
		return original;
	}

	public void setOriginal(byte[] original) {
		this.original = original;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMimetypeOriginal() {
		return mimetypeOriginal;
	}

	public void setMimetypeOriginal(String mimetypeOriginal) {
		this.mimetypeOriginal = mimetypeOriginal;
	}

	public String getMimetypeThumbnail() {
		return mimetypeThumbnail;
	}

	public void setMimetypeThumbnail(String mimetypeThumbnail) {
		this.mimetypeThumbnail = mimetypeThumbnail;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Image)) {
			return false;
		}
		Image other = (Image) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "abey.entities.Image[ id=" + id + " ]";
	}

}
