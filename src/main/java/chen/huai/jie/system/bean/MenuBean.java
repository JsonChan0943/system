package chen.huai.jie.system.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单Bean
 * 
 * @author chenhuaijie
 * 
 */
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 8035665246582259270L;
	private String title;// 菜单名称
	private String url;// 菜单url

	private List<MenuBean> subMenus;

	public MenuBean() {
		super();
	}

	public MenuBean(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuBean> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuBean> subMenus) {
		this.subMenus = subMenus;
	}

}
