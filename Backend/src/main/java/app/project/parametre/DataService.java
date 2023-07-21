package app.project.parametre;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataService {

	private int[] count_page;
	private Page<app.project.entities.Service> service;
	int page;
}
