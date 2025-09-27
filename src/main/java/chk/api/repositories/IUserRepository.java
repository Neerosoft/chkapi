package chk.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import chk.api.models.UserModel;

/*los repositorios permiten hacer operaciones en la base de datos*/
/*JpaRepository<UserModel,Long> agrega cierta funcionalidad de salida */
@Repository
public interface IUserRepository extends JpaRepository<UserModel,Long>{
	

}
