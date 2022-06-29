package com.idat.ec2.DTO;

public class UsuarioDTOResponse {
	
	private String token;
	
	public UsuarioDTOResponse(String token) {
		this.token = token;
	}
	
	

	public UsuarioDTOResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
