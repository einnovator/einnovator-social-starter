package org.einnovator.social.client.model;

public class RefBuilder {

	private String id;

	private String type;

	private String name;

	private String img;

	private String thumbnail;

	private String redirectUri;

	private String pingUri;


	public RefBuilder id(String id) {
		this.id = id;
		return this;
	}

	public RefBuilder type(String type) {
		this.type = type;
		return this;
	}

	public RefBuilder name(String name) {
		this.name = name;
		return this;
	}

	public RefBuilder img(String img) {
		this.img = img;
		return this;
	}

	public RefBuilder thumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}

	public RefBuilder redirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
		return this;
	}

	public RefBuilder pingUri(String pingUri) {
		this.pingUri = pingUri;
		return this;
	}

	
	public Ref build() {
		Ref ref = new Ref();
		ref.setId(id);
		ref.setType(type);
		ref.setName(name);
		ref.setImg(img);
		ref.setThumbnail(thumbnail);
		ref.setRedirectUri(redirectUri);
		ref.setPingUri(pingUri);
		return ref;
	}
	
	
	
}
