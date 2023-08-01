package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String serviceName;
	
	@ColumnDefault(value="null")
	private String thumbnailUrl;
	
	private int yearsInBusiness;
	
	
	private float maxPrice;

	private float minPrice;
	
	@Column(length=999999999)
	@ColumnDefault(value="null")
	private String photo;
	@ColumnDefault(value="0")
	private int raiting;
	
	private int phone;
	
	private String Description;
	@ColumnDefault(value="false")
	private boolean isBackgroundVerified;
	private String adresse;
	
	@ColumnDefault(value="0")
	private int status;
	
	@CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
	
	
	
	@ManyToOne
	@JoinColumn(name="village_id")
	private Village village;
	
	@ManyToOne
	@JoinColumn(name="categorie_id")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	
}
