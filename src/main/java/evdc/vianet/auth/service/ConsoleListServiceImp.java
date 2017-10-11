package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import evdc.vianet.auth.entity.ConsoleList;
import evdc.vianet.auth.mapper.AuthorityMapper;
import evdc.vianet.auth.mapper.ConsoleListMapper;

public class ConsoleListServiceImp implements ConsoleListService {
	@Autowired
	private ConsoleListMapper consoleListMapper;
	
	@Override
	public List<ConsoleList> getMainMeans() {
		// TODO Auto-generated method stub
		return consoleListMapper.findMainMeans();
	}

	@Override
	public List<ConsoleList> getMeansByFather(long id) {
		// TODO Auto-generated method stub
		return consoleListMapper.findMeansByFather(id);
	}

}
