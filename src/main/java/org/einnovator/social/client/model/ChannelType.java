package org.einnovator.social.client.model;


/**
 * An Enum for a {@code Channel} type.
 *
 * @see Channel
 * @author support@einnovator.org
 */
public enum ChannelType {
	MAIL("Mail"),
	MBOX("MBox"),
	FORUM("Forum"),
	CHAT("Chat"),
	DIRECT("Direct"),
	COMMENTS("Comments"),
	PERSONAL("Personal"),
	BLOG("Blog"),
	FEED("Feed"),
	EXTERNAL("External")
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
