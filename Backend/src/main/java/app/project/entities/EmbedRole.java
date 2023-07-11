package app.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbedRole implements Serializable{
	
	@Column(name="user_id")
	Long UserId;
	
	@Column(name="role_id")
	Long RoleId;
}
