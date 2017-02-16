package com.balaji.pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "AadharUser")
public class AadharUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private String udi_no;
	private String mobile_no;

	public String getUdi_no() {
		return udi_no;
	}

	@XmlElement
	public void setUdi_no(String udi_no) {
		this.udi_no = udi_no;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	@XmlElement
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public AadharUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AadharUser(String udi_no, String mobile_no) {
		super();
		this.udi_no = udi_no;
		this.mobile_no = mobile_no;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof AadharUser)) {
			return false;
		} else {
			AadharUser user = (AadharUser) object;
			if (udi_no.equals(user.getUdi_no()) && mobile_no.equals(user.getMobile_no())) {
				return true;
			}
		}
		return false;
	}

}
