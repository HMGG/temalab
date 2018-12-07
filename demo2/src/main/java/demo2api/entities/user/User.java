package demo2api.entities.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import demo2api.Location;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@NotNull
	@Size(min=3, max = 50)
	public String name;
	
	@Column(unique=true)
	@NotNull
	@Size(min=6, max = 100)
	public String email;
	
	public String bio;

	public Long birthday;
	
	@NotNull
	@Size(min=8, max = 50)
	public String password;
	
	@Column(unique=true)
	public Long sessionId;
	
	public Float latitude;
	
	public Float longitude;

	@NotNull
	public Boolean constantloc;
	
	public User() {
	}

	public User(long id, @NotNull @Size(min = 3, max = 50) String name, @NotNull @Size(min = 6, max = 100) String email,
			String bio, Long birthday, @NotNull @Size(min = 8, max = 50) String password, Long sessionId,
			Location l, Boolean constantloc) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.bio = bio;
		this.birthday = birthday;
		this.password = password;
		this.sessionId = sessionId;
		this.latitude = l.latitude;
		this.longitude = l.longitude;
		this.constantloc = constantloc;
	}

	public User hideinfo() {
		email = null;
		password = null;
		sessionId = null;
		return this;
	}	

}