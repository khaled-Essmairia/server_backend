package io.getarrays.server.model;

import io.getarrays.server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
  //  @NotEmpty(message = "IP Address cannot be empty or null")
	private String ipAddress;
	private String name;
	private String memory;
	private String Type;
	private String imageUrl;
	private Status status;
	
}
