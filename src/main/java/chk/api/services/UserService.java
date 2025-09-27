package chk.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.WebListenerRegistry;
import org.springframework.stereotype.Service;

import chk.api.models.UserModel;
import chk.api.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepository;
	
	public ArrayList<UserModel>getUser(){
		return (ArrayList<UserModel>)this.userRepository.findAll();
	}
	
	public UserModel saveUser(UserModel user) {
		return this.userRepository.save(user);
	}
	
	public Optional<UserModel>getById(Long id){
		return this.userRepository.findById(id);
	}
	
	/*Para actualizar primero se tiene que buscar*/
	public UserModel updateById(UserModel request,Long id){
		//se tiene que buscar primrmnt pde exst a cm no pde extr*/
		UserModel user=this.userRepository.findById(id).get();		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		
		return user;
		
	}
	
	public Boolean deleteUser(Long id) {
		boolean flag=false;
		try {
			this.userRepository.deleteById(id);			
		} 
		catch (Exception e) {
			flag=false;
		}
		return flag;
	}

}
