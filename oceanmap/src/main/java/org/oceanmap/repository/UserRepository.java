package org.oceanmap.repository;

import org.oceanmap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface UserRepository extends JpaRepository<User, Long> {
}
