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
import javax.persistence.OneToOne;

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
	
	
	private  int hiredCount;
	private boolean isOnline;
	private int yearsInBusiness;
	
	@ColumnDefault(value="null")
	private float maxPrice;
	
	@ColumnDefault(value="null")
	private float minPrice;
	
	@Column(length=999999999)
	@ColumnDefault(value="null")
	private String photo;
	@ColumnDefault(value="0")
	private int raiting;
	
	private String Description;
	@ColumnDefault(value="false")
	private boolean isBackgroundVerified;
	private boolean isHomeService;
	private String adresse;
	
	@CreationTimestamp
	private Timestamp created_at;
	@UpdateTimestamp
	private Timestamp updated_at;
	
	
	
	
	@OneToOne(mappedBy = "service")
	private Schedule schedule;
	
	
	@OneToOne(mappedBy = "service")
	private User user;
	
}
