package com.chamabem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "ApiAccess")
public class ApiAccess implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    private String publicKey;
    
    @Lob
    @NotNull
    private String privateKey;
    
    public String getPublicKey() {
		return publicKey;
	}
    
    public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
    
    public String getPrivateKey() {
		return privateKey;
	}
    
    public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}