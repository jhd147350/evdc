package evdc.vianet.sysconfig.SLA;

/**
 * evdc的sla配置
 * 
 * @author jhd147350
 *
 */
public class SLA {

	String name;

	String notes;

	// 0 不计算
	int initialResponse = 0;// 初始响应
	int updateInterval = 0;// 更新间隔，每隔多长时间就要有一条更新
	int resolveDuration = 0;// 解决时长
}
