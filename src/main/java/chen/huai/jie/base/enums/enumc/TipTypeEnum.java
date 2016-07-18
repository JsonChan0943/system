package chen.huai.jie.base.enums.enumc;

import chen.huai.jie.base.enums.enumi.IEnum;

/**
 * 提示信息类别枚举类
 * @author Administrator
 *
 */
public enum TipTypeEnum implements IEnum<String>{
	SUCCESS("成功提示", "success"), 
	WARN("警告提示", "warn"), 
	ERROR("错误提示", "error"),
	;
	private String enName;
	private String enValue;
	
	private TipTypeEnum(String enName,String enValue){
		this.enName = enName;
		this.enValue = enValue;
	}
	@Override
	public String getEnName() {
		return enName;
	}

	@Override
	public String getEnValue() {
		return enValue;
	}
}

	
