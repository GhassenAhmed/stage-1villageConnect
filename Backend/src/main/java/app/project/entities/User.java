package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	
	@ColumnDefault(value="null")
	@Column(length=999999999)
	private String photo;
	
	@ColumnDefault(value="null")
	private Boolean welcome_field;
	
	@ColumnDefault(value="null")
	private String password_token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value="null")
	private Date password_token_send_ats;
	
	@CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value = "null")
	private Date email_verified_at;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns=@javax.persistence.JoinColumn(name="user_id"),
	inverseJoinColumns = @javax.persistence.JoinColumn(name ="role_id"))
	private List<Role> roles;

}
