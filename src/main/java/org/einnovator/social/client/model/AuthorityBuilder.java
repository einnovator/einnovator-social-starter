package org.einnovator.social.client.model;

import org.einnovator.util.model.EntityBase;

public class AuthorityBuilder extends EntityBase {

	private String username;

	private String groupId;

	private String role;
	
	private Boolean publik;

	private Boolean read;

	private Boolean write;

	private Boolean manage;

	

	public AuthorityBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public AuthorityBuilder groupId(String groupId) {
		this.groupId = groupId;
		return this;
	}

	public AuthorityBuilder role(String role) {
		this.role = role;
		return this;
	}

	public AuthorityBuilder publik(Boolean publik) {
		this.publik = publik;
		return this;
	}

	public AuthorityBuilder read(Boolean read) {
		this.read = read;
		return this;
	}


	public AuthorityBuilder write(Boolean write) {
		this.write = write;
		return this;
	}


	public AuthorityBuilder manage(Boolean manage) {
		this.manage = manage;
		return this;
	}

	public Authority build() {
		Authority authority = new Authority();
		authority.setUsername(username);
		authority.setGroupId(groupId);
		authority.setRole(role);
		authority.setPublik(publik);
		authority.setRead(read);
		authority.setWrite(write);
		authority.setManage(manage);
		return authority;
	}
}
