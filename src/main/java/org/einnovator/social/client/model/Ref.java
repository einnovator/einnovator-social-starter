/**
 * 
 */
package org.einnovator.social.client.model;

import org.einnovator.util.model.ObjectBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ref extends ObjectBase {

	private String refId;

	private String refType;

	private String refName;

	private String refImg;

	private String refThumbnail;

	private String refRedirectUri;

	private String refPingUri;

	/**
	 * Create instance of {@code Ref}.
	 *
	 */
	public Ref() {
	}
	
	
	/**
	 * Get the value of property {@code refId}.
	 *
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * Set the value of property {@code refId}.
	 *
	 * @param refId the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * Get the value of property {@code refType}.
	 *
	 * @return the refType
	 */
	public String getRefType() {
		return refType;
	}



	/**
	 * Set the value of property {@code refType}.
	 *
	 * @param refType the refType to set
	 */
	public void setRefType(String refType) {
		this.refType = refType;
	}

	/**
	 * Get the value of property {@code refName}.
	 *
	 * @return the refName
	 */
	public String getRefName() {
		return refName;
	}

	/**
	 * Set the value of property {@code refName}.
	 *
	 * @param refName the refName to set
	 */
	public void setRefName(String refName) {
		this.refName = refName;
	}

	/**
	 * Get the value of property {@code refImg}.
	 *
	 * @return the refImg
	 */
	public String getRefImg() {
		return refImg;
	}

	/**
	 * Set the value of property {@code refImg}.
	 *
	 * @param refImg the refImg to set
	 */
	public void setRefImg(String refImg) {
		this.refImg = refImg;
	}

	/**
	 * Get the value of property {@code refThumbnail}.
	 *
	 * @return the refThumbnail
	 */
	public String getRefThumbnail() {
		return refThumbnail;
	}



	/**
	 * Set the value of property {@code refThumbnail}.
	 *
	 * @param refThumbnail the refThumbnail to set
	 */
	public void setRefThumbnail(String refThumbnail) {
		this.refThumbnail = refThumbnail;
	}

	/**
	 * Get the value of property {@code refRedirectUri}.
	 *
	 * @return the refRedirectUri
	 */
	public String getRefRedirectUri() {
		return refRedirectUri;
	}

	/**
	 * Set the value of property {@code refRedirectUri}.
	 *
	 * @param refRedirectUri the refRedirectUri to set
	 */
	public void setRefRedirectUri(String refRedirectUri) {
		this.refRedirectUri = refRedirectUri;
	}



	/**
	 * Get the value of property {@code refPingUri}.
	 *
	 * @return the refPingUri
	 */
	public String getRefPingUri() {
		return refPingUri;
	}

	/**
	 * Set the value of property {@code refPingUri}.
	 *
	 * @param refPingUri the refPingUri to set
	 */
	public void setRefPingUri(String refPingUri) {
		this.refPingUri = refPingUri;
	}


	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("refId", refId)
				.append("refType", refType)
				.append("refName", refName)
				.append("refRedirectUri", refRedirectUri)
				.append("refPingUri", refPingUri)
				;
	}


}
