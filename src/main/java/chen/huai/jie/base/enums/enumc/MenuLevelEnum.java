package chen.huai.jie.base.enums.enumc;

import chen.huai.jie.base.enums.enumi.IEnum;

/**
 * 菜单等级
 * 
 * @author chenhuaijie
 * 
 */
public enum MenuLevelEnum implements IEnum<Integer> {
	LEVEL_ONE("一级菜单", 1), LEVEL_TWO("二级菜单", 2), LEVEL_THREE("三级菜单", 3), ;

	/** 名 */
	private String enName;
	/** 值 */
	private Integer enValue;

	private MenuLevelEnum(String enName, Integer enValue) {
		this.enName = enName;
		this.enValue = enValue;
	}

	@Override
	public String getEnName() {
		return enName;
	}

	@Override
	public Integer getEnValue() {
		return enValue;
	}

}
