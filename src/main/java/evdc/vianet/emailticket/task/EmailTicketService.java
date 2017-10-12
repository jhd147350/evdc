package evdc.vianet.emailticket.task;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import evdc.vianet.shift.entity.jo.TableData;

@Service("emailTicketService")
public class EmailTicketService {

	@Autowired
	EmailMapper mapper;

	public String getEmailTicketJson(long page, long limit) {
		List<Email> emails = mapper.selectAllUnhandledEmail((page - 1) * limit, limit);
		long count = mapper.countAllUnhandledEmail();
		TableData<Email> jsonData = new TableData<>();
		jsonData.setCode(200);
		jsonData.setCount(count);// TODO 这个数量现在随便写的，实际返回的是所有数据
		jsonData.setMsg("this is suc msg");
		jsonData.setData(emails);
		JSONObject jo = new JSONObject(jsonData);
		// System.out.println(jo.toString());
		return jo.toString();

	}

	public void getEmailDetail(long id, Model m) {
		m.addAttribute("m", mapper.selectEmailById(id));
	}

}