USE `dead_by_daylight`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_name` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Default passwords here are: parola123
--

INSERT INTO `users`
VALUES
('john','{bcrypt}$2a$12$phtb1B6gZUFKNh2CgoKv9OAgIkX35.dES/TgO1FhWa61nd/FMOdXS',1),
('mary','{bcrypt}$2a$12$phtb1B6gZUFKNh2CgoKv9OAgIkX35.dES/TgO1FhWa61nd/FMOdXS',1),
('admin','{bcrypt}$2a$12$phtb1B6gZUFKNh2CgoKv9OAgIkX35.dES/TgO1FhWa61nd/FMOdXS',1);


CREATE TABLE `roles` (
  `user_name` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_name`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles`
VALUES
('john','ROLE_PLAYER'),
('mary','ROLE_PLAYER'),
('mary','ROLE_MANAGER'),
('admin','ROLE_PLAYER'),
('admin','ROLE_MANAGER'),
('admin','ROLE_ADMIN');