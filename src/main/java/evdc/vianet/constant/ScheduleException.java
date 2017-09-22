package evdc.vianet.constant;
/**
 * 所查询团队没有值班时抛出的异常
 * @author jhd147350
 *
 */
public class ScheduleException extends Exception {

	public ScheduleException(String msg) {
		super(msg);
	}
}
