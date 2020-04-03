package com.agenda.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<T> manyData;
	private List<String> errors;
	private int status;
	private String message;
	private String token;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getManyData() {
		return manyData;
	}

	public void setManyData(List<T> manyData) {
		this.manyData = manyData;
	}

	public List<String> getErrors() {
		if (errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
