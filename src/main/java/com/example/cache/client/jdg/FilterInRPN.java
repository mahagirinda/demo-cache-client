package com.example.cache.client.jdg;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilterInRPN {

	private String field;
	private String parameter;
	private String operator;

	public FilterInRPN(String field, String parameter, String operator) {
		this.field = field;
		this.parameter = parameter;
		this.operator = operator;
	}

}
