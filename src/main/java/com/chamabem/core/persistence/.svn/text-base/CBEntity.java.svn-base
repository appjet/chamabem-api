package com.chamabem.core.persistence;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;

public abstract class CBEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Serializable getId();

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !this.getClass().isAssignableFrom(obj.getClass())){
			return false;
		}
		
		if(this.getId() == null || ((CBEntity) obj).getId() == null){
			return super.equals(obj);
		}
		
		return this.getId().equals(((CBEntity) obj).getId());
	}

	@Override
	public int hashCode() {
		return this.getId() != null ? HashCodeBuilder.reflectionHashCode(this.getId()) : super.hashCode();
	}
}