package evdc.vianet.auth.controller;

import java.util.ArrayList;
import java.util.List;

import evdc.vianet.auth.entity.ConsoleList;

public class MeanModule extends ConsoleList{
	private List<MeanModule> submodules;

	public MeanModule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeanModule(ConsoleList cl) {
		super(cl.getId(), cl.getListname(), cl.getValue(), cl.getFather(), cl.getPath());
	}

	public List<MeanModule> getSubmodules() {
		return submodules;
	}
	
	public void setSubmodules(List<MeanModule> submodules) {
		this.submodules = submodules;
	}

	public void setSubmodulesByConsoleList(long userAuth, List<ConsoleList> subCls) {
		List<MeanModule> subModules = new ArrayList<>(); 
		for (ConsoleList cl : subCls) {
			if((userAuth&cl.getValue()) > 0){
				subModules.add(new MeanModule(cl));
			}
		}
		this.submodules = subModules;
	}
	
}
