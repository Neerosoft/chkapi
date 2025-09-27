package chk.api.copias;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chk.api.models.UserModel;
import chk.api.services.UserService;

//Aqui es donde se definen las peticiones http
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ArrayList<UserModel>gtUser(){
		return this.userService.getUser();
	}
	
	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return  this.userService.saveUser(user);
		
	}
	
	@GetMapping(path="/{id}")
	public Optional<UserModel>getUserByID(@PathVariable Long id){
		return this.userService.getById(id);
	}
	
	@PutMapping(path="/{id}")
	public UserModel updateUserById(@RequestBody UserModel request,@PathVariable Long id) {
		return this.userService.updateById(request, id);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		boolean ok =this.userService.deleteUser(id);
		if(ok) {
			return "user with id="+id+" deleted ";
		}
		else {
			return "error al borrar el id ";
		}
	}
	

}
