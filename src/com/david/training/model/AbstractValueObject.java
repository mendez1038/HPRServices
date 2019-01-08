package com.david.training.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractValueObject  implements ValueObject {
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

	public int compareTo(Contenido c) {
		// TODO Auto-generated method stub
		return 0;
	}
}
