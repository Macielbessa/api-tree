package spring.jpa.postgresql.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.jpa.postgresql.Entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
