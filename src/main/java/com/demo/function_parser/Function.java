package com.demo.function_parser;

import java.util.ArrayList;
import java.util.List;

public class Function {
	private Integer id;
	private String name;
	private String code;
	private String description;
	private List<FunctionTemplate> templates;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FunctionTemplate> getTemplates() {
		if(templates == null) {
			templates = new ArrayList<FunctionTemplate>();
		}
		return templates;
	}

	public void setTemplates(List<FunctionTemplate> templates) {
		this.templates = templates;
	}

}
