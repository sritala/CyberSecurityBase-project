package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sec.project.domain.Signup;

import javax.persistence.Table;

@Table(name = "Signup")
public interface SignupRepository extends JpaRepository<Signup, Long> {

    @Query(value = "Select * from Signup where id = ?1", nativeQuery = true)
    Signup findWithSubscriberId(String subscriberId);
}
