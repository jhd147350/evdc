package evdc.vianet.auth.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.ConsoleList;
import evdc.vianet.auth.mapper.ConsoleListMapper;

/**
 * @author jaden
 *
 * @date	2017年10月14日上午11:02:47
 */
@Service("consoleListService")
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

	@Override
	public ConsoleList getMeanById(long id) {
		// TODO Auto-generated method stub
		return consoleListMapper.findMeanById(id);
	}

}
