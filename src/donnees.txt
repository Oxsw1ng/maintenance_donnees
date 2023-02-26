SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `infos_pays`;
CREATE TABLE infos_pays (
    id INT(4) NOT NULL AUTO_INCREMENT,
    pays VARCHAR(200) NOT NULL,
    habitants INT(11) NOT NULL,
    PIB DECIMAL(10,2) NOT NULL,
    moyenneAge DECIMAL(5,2) NOT NULL,
    moyenneAgeVie DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id, pays)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO infos_pays (pays, habitants, PIB, moyenneAge, moyenneAgeVie)
VALUES
('France', 67012883, 2900.00, 41.0, 82.3),
('Allemagne', 83019200, 3847.00, 46.5, 81.0),
('Espagne', 47100396, 1843.00, 43.5, 83.2),
('Italie', 60486683, 1978.00, 46.0, 83.6),
('Royaume-Uni', 66435600, 2912.00, 40.0, 80.9),
('États-Unis', 331002651, 21433.00, 38.0, 78.9),
('Chine', 1439323776, 16642.32, 38.4, 76.9),
('Inde', 1380004385, 2726.32, 28.5, 69.7),
('Japon', 126476461, 5082.00, 47.0, 84.6),
('Russie', 145934462, 1659.00, 40.0, 72.0),
('Canada', 37961548, 1806.00, 41.0, 82.5);