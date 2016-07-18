package chen.huai.jie.base.enums.enumc;

import chen.huai.jie.base.enums.enumi.IEnum;

/**
 * 机构等级
 * 
 * @author chenhuaijie
 * 
 */
public enum OrganLevelEnum implements IEnum<Integer> {
	LEVEL_ONE("一级机构", 1), LEVEL_TWO("二级机构", 2), LEVEL_THREE("三级机构", 3), ;

	/** 名 */
	private String enName;
	/** 值 */
	private Integer enValue;

	private OrganLevelEnum(String enName, Integer enValue) {
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
