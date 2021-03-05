package spring.oauth.jwt.dto;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.oauth.jwt.model.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String username;
	private String password;

	public User convertDTOToEntity() {
		return new ModelMapper().map(this, User.class);
	}
}
