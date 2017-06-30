package com.demo.function_parser;

public class FunctionTemplate {
	private String db;
	private String template;
	private boolean varargs;

	public boolean isVarargs() {
		return varargs;
	}

	public void setVarargs(boolean varargs) {
		this.varargs = varargs;
	}

	public FunctionTemplate() {
	}

	public FunctionTemplate(String db, String template) {
		this.db = db;
		this.template = template;
		this.varargs = false;
	}
	
	public FunctionTemplate(String db, String template, boolean varargs) {
		this.db = db;
		this.template = template;
		this.varargs = varargs;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
