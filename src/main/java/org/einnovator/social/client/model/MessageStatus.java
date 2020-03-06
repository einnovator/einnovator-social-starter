package org.einnovator.social.client.model;

public enum MessageStatus {
	VISIBLE("Visible"),
	DRAFT("Draft"),
	REPORTED("Reported"),
	CLOSED("Closed"),
	;
	
	private final String displayValue;

	MessageStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static MessageStatus parse(String s) {
		for (MessageStatus e: MessageStatus.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static MessageStatus parse(String s, MessageStatus defaultValue) {
		MessageStatus value = parse(s);
		return value!=null ? value: defaultValue;
	}
	
	
}
