package app.project.parametre;

import javax.persistence.Column;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataEditService {
	
	private String serviceName;
	
	@ColumnDefault(value="null")
	private String thumbnailUrl;
	
	private int yearsInBusiness;
	
	
	private float maxPrice;

	private float minPrice;
	
	private String Description;
	private int phone;
	private String adresse;

}
