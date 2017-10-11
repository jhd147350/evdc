package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.ConsoleList;

public interface ConsoleListService {
	public List<ConsoleList> getMainMeans();
	public List<ConsoleList> getMeansByFather(long id);
}
