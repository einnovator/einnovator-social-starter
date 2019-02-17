package org.einnovator.social.client.model;

public class ChannelBuilder {

	private ChannelType type;

	private String owner;
	
	private String name;

	private String purpose;

	private String img;

	private String thumbnail;

	private Ref ref;

	private Message head;

	
	public ChannelBuilder type(ChannelType type) {
		this.type = type;
		return this;
	}

	public ChannelBuilder owner(String owner) {
		this.owner = owner;
		return this;
	}

	public ChannelBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ChannelBuilder purpose(String purpose) {
		this.purpose = purpose;
		return this;
	}

	public ChannelBuilder img(String img) {
		this.img = img;
		return this;
	}

	public ChannelBuilder thumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}

	public ChannelBuilder ref(Ref ref) {
		this.ref = ref;
		return this;
	}

	public ChannelBuilder head(Message head) {
		this.head = head;
		return this;
	}

	
	
	public Channel build() {
		Channel channel = new Channel();
		channel.setType(type);
		channel.setOwner(owner);
		channel.setName(name);
		channel.setPurpose(purpose);
		channel.setImg(img);
		channel.setThumbnail(thumbnail);
		channel.setRef(ref);
		channel.setHead(head);
		return channel;
	}
	
	
	
}
