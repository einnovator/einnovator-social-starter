package org.einnovator.social.client.model;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * A Attachment.
 * 
 * @author support@einnovator.org
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachment extends EntityBase {

	private String filename;

	private String title;

	private String uri;
	
	private Long size;

	private String contentType;
		
	/**
	 * Create instance of {@code Attachment}.
	 *
	 */
	public Attachment() {
	}
	//
	// Getters/Setters
	//	

	/**
	 * Get the value of property {@code filename}.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Set the value of property {@code filename}.
	 *
	 * @param filename the value of property filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * Get the value of property {@code title}.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the value of property {@code title}.
	 *
	 * @param title the value of property title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the value of property {@code uri}.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the value of property uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Get the value of property {@code size}.
	 *
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * Set the value of property {@code size}.
	 *
	 * @param size the value of property size
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * Get the value of property {@code contentType}.
	 *
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the value of property contentType
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	//
	// With
	//
	
	/**
	 * Set the value of property {@code filename}.
	 *
	 * @param filename the value of property filename
	 * @return this {@code Attachment}
	 */
	public Attachment withFilename(String filename) {
		this.filename = filename;
		return this;
	}

	/**
	 * Set the value of property {@code title}.
	 *
	 * @param title the value of property title
	 * @return this {@code Attachment}
	 */
	public Attachment withTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Set the value of property {@code uri}.
	 *
	 * @param uri the value of property uri
	 * @return this {@code Attachment}
	 */
	public Attachment withUri(String uri) {
		this.uri = uri;
		return this;
	}

	/**
	 * Set the value of property {@code size}.
	 *
	 * @param size the value of property size
	 * @return this {@code Attachment}
	 */
	public Attachment withSize(Long size) {
		this.size = size;
		return this;
	}

	/**
	 * Set the value of property {@code contentType}.
	 *
	 * @param contentType the value of property contentType
	 * @return this {@code Attachment}
	 */
	public Attachment withContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}

	@Override
	public ToStringCreator toString1(ToStringCreator creator) {
		return super.toString1(creator)
				.append("filename", filename)
				.append("title", title)
				.append("uri", uri)
				.append("contentType", contentType)
				.append("size", size)
				;
	}
	
}
