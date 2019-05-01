package com.imooc.enums;

public enum VideoStatusEnum {
	
	ALLOWED(1,"允许播放"),
	
	FORBIDDEN(2,"禁止播放");
	
	public final int type;
	public final String value;
	
	
	
	
	VideoStatusEnum(int type,String value){
		this.type=type;
		this.value=value;
	}

	public int getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
	
	public static String getValueByKey(int key){
		if(key!=1&&key!=2){
			return null;
		}
		if(key==1){
			return ALLOWED.value;
		}
		if(key==2){
			return FORBIDDEN.value;
		}
		return null;
	}
}
