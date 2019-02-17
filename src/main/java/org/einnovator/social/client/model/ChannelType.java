package org.einnovator.social.client.model;


public enum ChannelType {
	MULTI("Multi"),
	COMMENTS("Comments"),
	QUESTION("Question"),
	DIRECT("Direct"),
	PERSONAL("Personal"),
	MAIL("Mail"),
	EXTERNAL("External"),
	BLOG("Blog"),
	;
	
	private final String displayValue;

	ChannelType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static ChannelType parse(String s) {
		for (ChannelType e: ChannelType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static ChannelType parse(String s, ChannelType defaultValue) {
		ChannelType value = parse(s);
		return value!=null ? value: defaultValue;
	}

}
