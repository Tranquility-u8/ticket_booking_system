use cinema_demo_data;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` int(11) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,# 用户设置的自己的真实名字
                        `nickname` varchar(255) NOT NULL,# 昵称
                        `email` varchar(30) DEFAULT NULL,
                        `avatar_url` varchar(255) DEFAULT NULL,# 头像链接
                        `tel` varchar(255) DEFAULT NULL,
                        `address` varchar(255) DEFAULT NULL,
                        `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,# 创建时设置
                        `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,# 创建或更新记录时设置
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`user_id`, `name`, `nickname`, `email`, `avatar_url`, `tel`, `address`)VALUES ('1', '奔雷少年', '奔少', null, null, null, null);

INSERT INTO `user` (`name`, `nickname`, `email`, `avatar_url`, `tel`, `address`)
VALUES ('Alice', 'alice', 'alice@example.com', 'https://example.com/alice.jpg', '1234567890', '1 Example Street'),
       ('Bob', 'bob', 'bob@example.com', 'https://example.com/bob.jpg', '0987654321', '2 Example Street');

#------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
                             `record_id` int (11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) NOT NULL,
                             `username` varchar(255) NOT NULL,# 这是真正的用户名，其实这个可以设为主键，要求不同
                             `password` varchar(255) NOT NULL,
                             `type` int(11) DEFAULT NULL,
                             PRIMARY KEY (`record_id`),
                             FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_auth` VALUES ('1', '1', 'thunderboy', 'reins1409', '0');
INSERT INTO `user_auth` (`user_id`, `username`, `password`, `type`)
VALUES (2, 'alice', 'password1', 1),
       (3, 'bob', 'password2', 1);

# ---------------------------
# 电影表
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
                         `id` int (11) NOT NULL AUTO_INCREMENT,
                         `title` VARCHAR(255) NOT NULL,
                         `release_date` VARCHAR(255) DEFAULT NULL,# 上映时间
                         `genre` VARCHAR(255) DEFAULT NULL,# 电影类型
                         `director` VARCHAR(255) DEFAULT NULL,# 导演
                         `cast` TEXT DEFAULT NULL,# 演员表
                         `description` TEXT DEFAULT NULL,# 电影描述
                         `poster_url` VARCHAR(255) DEFAULT NULL,# 头图链接
                         `movie_url` VARCHAR(255) DEFAULT NULL,# 电影链接
                         `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `movie` (`title`, `release_date`, `genre`, `director`, `cast`, `description`, `poster_url`, `movie_url`)
VALUES ('Movie 1', '2022-01-01', 'Action', 'Director 1', 'Cast 1', 'Description 1', 'https://example.com/movie1.jpg', 'https://example.com/movie1.mp4'),
       ('Movie 2', '2022-02-02', 'Comedy', 'Director 2', 'Cast 2', 'Description 2', 'https://example.com/movie2.jpg', 'https://example.com/movie2.mp4');

# ---------------------------
# 影院表，以及放映记录表（某电影，在某时刻、某家影院放映的信息）
DROP TABLE IF EXISTS `cinema`;
CREATE TABLE `cinema` (
                          `id` INT AUTO_INCREMENT PRIMARY KEY,
                          `name` VARCHAR(255) NOT NULL,
                          `address` VARCHAR(255) DEFAULT NULL,
                          `phone` VARCHAR(255) DEFAULT NULL,
                          `poster_url` VARCHAR(255) DEFAULT NULL# 头图链接
);
INSERT INTO `cinema` (`name`, `address`)
VALUES ('Cinema 1', '1 Cinema Street'),
       ('Cinema 2', '2 Cinema Street');

#----------------------------------

# 放映信息
DROP TABLE IF EXISTS `screening`;
CREATE TABLE `screening` (
                             `id` INT AUTO_INCREMENT PRIMARY KEY,
                             `cinema_id` INT NOT NULL,
                             `movie_id` INT NOT NULL,
                             `start_time` DATETIME DEFAULT NULL,
                             FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`),
                             FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
);
INSERT INTO `screening` (`cinema_id`, `movie_id`, `start_time`)
VALUES (1, 1, '2022-03-03 12:00:00'),
       (1, 2, '2022-03-03 14:00:00'),
       (2, 1, '2022-03-03 16:00:00'),
       (2, 2, '2022-03-03 18:00:00');

# -------------------------
# 评论，待实现树形评论功能
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                           `comment_id` int(11) NOT NULL AUTO_INCREMENT,
                           `movie_id` int(11) NOT NULL,
                           `user_id` int(11) NOT NULL,
                           `content` text NOT NULL,
                           `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`comment_id`),
                           FOREIGN KEY (`movie_id`) REFERENCES `movie`(`id`),
                           FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `comment` (`movie_id`, `user_id`, `content`)
VALUES (1, 2, 'Great movie!'),
       (1, 3, 'I like it.'),
       (2, 2, 'Very funny.'),
       (2, 3, 'Made me laugh.');

#----------
# 放映场次对应的ticket信息，以及用户实际买票之后的票务信息
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
                          `id` INT AUTO_INCREMENT PRIMARY KEY,
                          `screening_id` INT NOT NULL,
                          `price` DECIMAL(10,2) NOT NULL,
                          FOREIGN KEY (`screening_id`) REFERENCES `screening`(`id`)
);

# 订单信息
DROP TABLE IF EXISTS `order`;# 持久化维护，不要随便乱动
CREATE TABLE `order`(# 为了实现方便，所有用户都共用order_id的自增功能
                        `order_id` INT AUTO_INCREMENT PRIMARY KEY,
                        `user_id` int(11) NOT NULL,
                        `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                        `total` decimal(10,2) DEFAULT NULL,
                        FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 订单处理
DROP TABLE IF EXISTS `orderItem`;
CREATE TABLE `orderItem` (
                            `id` INT AUTO_INCREMENT PRIMARY KEY,
                            `order_id` INT NOT NULL,
                            `ticket_id` INT NOT NULL,
                            `ticket_count` INT NOT NULL,
                            `real_price` DECIMAL(10,2) NOT NULL,# 经过可能打折的计算后，得到的值
                            FOREIGN KEY (`ticket_id`) REFERENCES `ticket`(`id`),
                            FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`)
);