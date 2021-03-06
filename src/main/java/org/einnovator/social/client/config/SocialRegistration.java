package org.einnovator.social.client.config;

import java.util.List;
import java.util.Map;

import org.einnovator.social.client.model.Channel;
import org.einnovator.util.model.Application;
import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SocialRegistration extends ObjectBase {

	@JsonIgnore
	private boolean auto;
	
	private Application application;
	
	private List<Channel> channels;

	private Map<String, Object> properties;

	public SocialRegistration() {
	}
	
	
	/**
	 * Get the value of property {@code auto}.
	 *
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}


	/**
	 * Set the value of property {@code auto}.
	 *
	 * @param auto the auto
	 */
	public void setAuto(boolean auto) {
		this.auto = auto;
	}


	/**
	 * Get the value of property {@code application}.
	 *
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}


	/**
	 * Set the value of property {@code application}.
	 *
	 * @param application the application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	/**
	 * Get the value of property {@code channels}.
	 *
	 * @return the channels
	 */
	public List<Channel> getChannels() {
		return channels;
	}

	/**
	 * Set the value of property {@code channels}.
	 *
	 * @param channels the channels
	 */
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	/**
	 * Get the value of property {@code properties}.
	 *
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}


	/**
	 * Set the value of property {@code properties}.
	 *
	 * @param properties the properties
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}


	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
			.append("auto", auto)
			.append("application", application)
			.append("channels", channels)
			.append("properties", properties)
			;
	}

}
