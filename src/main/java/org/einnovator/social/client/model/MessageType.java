package org.einnovator.social.client.model;


/**
 * An Enum for a {@code Message} type.
 *
 * @see Message
 * @author support@einnovator.org
 */
public enum MessageType {
	MESSAGE("Message"),
	QUESTION("Question"),
	ANSWER("Answer"),
	COMMENT("Comment"),
	HEADER("Header"),
	BLOG("Blog"),
	;
	
	private final String displayValue;

	MessageType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	public static MessageType parse(String s) {
		for (MessageType e: MessageType.class.getEnumConstants()) {
			if (e.toString().equalsIgnoreCase(s)) {
				return e;
			}
		}
		return null;
	}
	
	public static MessageType parse(String s, MessageType defaultValue) {
		MessageType value = parse(s);
		return value!=null ? value: defaultValue;
	}

}
