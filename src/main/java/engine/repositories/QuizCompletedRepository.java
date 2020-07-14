package engine.repositories;

import engine.models.QuizCompleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletedRepository extends JpaRepository<QuizCompleted, Integer> {

    @Query("SELECT q FROM QuizCompleted q WHERE q.user.id = ?1 ORDER BY q.completedAt DESC")
    Page<QuizCompleted> findCompletedQuizzesPaginated(long id, Pageable pageable);
}