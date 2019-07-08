package cl.meeting.MeetingServices.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import cl.meeting.MeetingServices.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

	Usuario findByUsername(String username);

	Usuario findByUsernameAndPassword(String username, String password);

}