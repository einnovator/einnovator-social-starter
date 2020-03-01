package org.einnovator.social.client.model;

public enum ChannelStatus {
	ACTIVE("Active"),
	LOCKED("Locked"),
	ARCHIVED("Archived"),
	;
	
	private final String displayValue;

	ChannelStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static ChannelStatus parse(String s) {
		for (ChannelStatus e: ChannelStatus.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static ChannelStatus parse(String s, ChannelStatus defaultValue) {
		ChannelStatus value = parse(s);
		return value!=null ? value: defaultValue;
	}
	
	
}
