package app.project.parametre;

import app.project.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyRequest {
private long categorie_id;
private long village_id;
private Service service;
}
