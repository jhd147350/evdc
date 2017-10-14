package evdc.vianet.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.ClientConfig;
import evdc.vianet.auth.mapper.ClientConfigMapper;

/**
 * @author jaden
 *
 * @date	2017年10月14日上午10:30:32
 */
@Service("clientConfigService")
public class ClientConfigServiceImp implements ClientConfigService {
	@Autowired
	private ClientConfigMapper clientConfigMapper;
	
	@Override
	public ClientConfig getMeansByTeamRoleId(long teamRoleId) {
		// TODO Auto-generated method stub
		return clientConfigMapper.findMeansByTeamRoleId(teamRoleId);
	}

}
