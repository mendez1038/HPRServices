package com.david.training.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ValueObject {

	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
				
	}
}
