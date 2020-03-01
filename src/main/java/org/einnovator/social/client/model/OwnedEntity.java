/**
 * 
 */
package org.einnovator.social.client.model;

import org.einnovator.util.model.EntityBase;
import org.einnovator.util.model.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnedEntity extends EntityBase {

	protected String owner;

	protected Object ownerUser;
	
	protected String groupId;

	protected Object group;


	/**
	 * Create instance of {@code OwnedEntity}.
	 *
	 */
	public OwnedEntity() {
	}
	
	/**
	 * Create instance of {@code OwnedEntity}.
	 *
	 * @param obj a prototype
	 */
	public OwnedEntity(Object obj) {
		super(obj);
	}
	
	//
	// Getters/Setters
	//


	/**
	 * Get the value of property {@code owner}.
	 *
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the value of property owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Get the value of property {@code ownerUser}.
	 *
	 * @return the ownerUser
	 */
	public Object getOwnerUser() {
		return ownerUser;
	}

	/**
	 * Set the value of property {@code ownerUser}.
	 *
	 * @param ownerUser the value of property ownerUser
	 */
	public void setOwnerUser(Object ownerUser) {
		this.ownerUser = ownerUser;
	}

	/**
	 * Get the value of property {@code groupId}.
	 *
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Set the value of property {@code groupId}.
	 *
	 * @param groupId the value of property groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Get the value of property {@code group}.
	 *
	 * @return the group
	 */
	public Object getGroup() {
		return group;
	}

	/**
	 * Set the value of property {@code group}.
	 *
	 * @param group the value of property group
	 */
	public void setGroup(Object group) {
		this.group = group;
	}

	//
	// With
	//

	/**
	 * Set the value of property {@code owner}.
	 *
	 * @param owner the value of property owner
	 * @return this {@code OwnedEntity}
	 */
	public OwnedEntity withOwner(String owner) {
		this.owner = owner;
		return this;
	}
	

	/**
	 * Set the value of property {@code ownerUser}.
	 *
	 * @param ownerUser the value of property ownerUser
	 * @return this {@code OwnedEntity}
	 */
	public OwnedEntity withOwnerUser(Object ownerUser) {
		this.ownerUser = ownerUser;
		return this;
	}

	/**
	 * Set the value of property {@code groupId}.
	 *
	 * @param groupId the value of property groupId
	 * @return this {@code OwnedEntity}
	 */
	public OwnedEntity withGroupId(String groupId) {
		this.groupId = groupId;
		return this;
	}

	/**
	 * Set the value of property {@code group}.
	 *
	 * @param group the value of property group
	 * @return this {@code OwnedEntity}
	 */
	public OwnedEntity withGroup(Object group) {
		this.group = group;
		return this;
	}


	//
	// Util
	//
	
	@JsonIgnore
	public String getRequiredOwner() {
		return owner!=null ? owner : createdBy;
	}


	@Override
	public ToStringCreator toString2(ToStringCreator creator) {
		return super.toString2(creator)
				.append("owner", owner)
				.append("groupId", groupId)
				;
	}
	


}
