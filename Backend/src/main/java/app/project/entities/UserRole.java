package app.project.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user_role")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable{
	
	@EmbeddedId
	EmbedRole id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name="role_id")
	Role role;
	
	@ColumnDefault(value = "0")
    int status;
}
