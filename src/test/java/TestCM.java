import java.util.Map;

import cn.cuilan.config.Bean;
import cn.cuilan.config.parse.ConfigManager;

import org.junit.Test;

/**
 * 测试ConfigManager
 *
 * @author cuilan
 */
public class TestCM {

	/**
	 * 测试读取配置文件的ConfigManager.java是否正确
	 */
	@Test
	public void fun1() {
		Map<String, Bean> config = ConfigManager.getConfig("/applicationContext.xml");
		System.out.println(config);
	}
}
