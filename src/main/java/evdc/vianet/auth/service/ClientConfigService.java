package evdc.vianet.auth.service;

import evdc.vianet.auth.entity.ClientConfig;

/**
 * @author jaden
 *
 * @date	2017年10月14日上午10:29:27
 */
public interface ClientConfigService {
	public ClientConfig getMeansByTeamRoleId(long teamRoleId);
}
