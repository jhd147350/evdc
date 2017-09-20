package evdc.vianet.auth.controller;

import java.util.ArrayList;
import java.util.List;

import evdc.vianet.auth.entity.Authority;

public class AuthModule extends Authority{
	private List<AuthModule> submodules;

	public AuthModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthModule(Authority auth) {
		super(auth.getId(), auth.getAuthName(), auth.getPath(), auth.getAuthValue(), auth.getDescribe());
		// TODO Auto-generated constructor stub
	}

	public List<AuthModule> getSubmodules() {
		return submodules;
	}
	
	public void setSubmodules(List<AuthModule> submodules) {
		this.submodules = submodules;
	}

	public void setSubmodulesByAuth(List<Authority> subAuth) {
		List<AuthModule> subModules = new ArrayList<>(); 
		for (Authority authority : subAuth) {
			subModules.add(new AuthModule(authority));
		}
		this.submodules = subModules;
	}
	
}
