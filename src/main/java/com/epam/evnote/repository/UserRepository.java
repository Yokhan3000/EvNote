package com.epam.evnote.repository;

import com.epam.evnote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Chtetsov on 09/12/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User getByLogin(String login);
}
