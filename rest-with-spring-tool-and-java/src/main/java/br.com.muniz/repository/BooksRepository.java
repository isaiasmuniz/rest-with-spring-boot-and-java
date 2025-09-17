package br.com.muniz.repository;

import br.com.muniz.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends JpaRepository<Books, Long> {

//    @Query("SELECT p FROM books p WHERE p.authorName LIKE LOWER(CONCAT('%', :authorName, '%'))")
//    Page<Books> findBookByName(@Param("authorName") String authorName, Pageable pageable);
}
