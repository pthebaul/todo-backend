package fr.paulthebault.todobackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.paulthebault.todobackend.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}