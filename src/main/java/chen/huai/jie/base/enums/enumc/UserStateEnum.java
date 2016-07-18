package chen.huai.jie.base.enums.enumc;

import chen.huai.jie.base.enums.enumi.IEnum;

/**
 * 系统用户状态枚举类
 * 
 * @author chenhuaijie
 * 
 */
public enum UserStateEnum implements IEnum<Integer> {
	NORMAL("正常", 1), LOCKED("已锁定", 2), CANCLE("已注销", 3), STOPED("已停用", 4), ;

	/** 名 */
	private String enName;
	/** 值 */
	private Integer enValue;

	private UserStateEnum(String enName, Integer enValue) {
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
