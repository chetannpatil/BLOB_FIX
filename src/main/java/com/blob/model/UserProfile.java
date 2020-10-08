package com.blob.model;

import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

@Entity
public class UserProfile
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	//clob or blob
//	@Lob
//	private Blob profilePic;

	
	//@Column(name ="profilePic")
	//	@JoinColumn(name="mapping_type_id")
	@Lob
	@Column(name="profilePic", length = 99999)
//	@Type(type = "org.hibernate.type.MaterializedBlobType")
	private byte[] profilePic;
	
//	@Lob
//	@Column(name ="DP")
//	private Blob dp ;

	private String base64Image;
	
	
	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UserProfile other = (UserProfile) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", name=" + name + ", profilePic=" + Arrays.toString(profilePic)
				+ ", base64Image=" + base64Image + "]";
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	
	
	
}
