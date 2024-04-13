CREATE DATABASE IF NOT EXISTS desafio_integrador_01;
USE desafio_integrador_01;

DROP TABLE IF EXISTS peliculas;
CREATE TABLE IF NOT EXISTS peliculas (
`codigo` INT NOT NULL AUTO_INCREMENT,
`titulo` VARCHAR(40) NOT NULL,
`director` VARCHAR(25) NOT NULL,
`url` VARCHAR(80) NOT NULL,
`imagen` LONGBLOB,
PRIMARY KEY (`codigo`)
);

DROP TABLE IF EXISTS generos;
CREATE TABLE IF NOT EXISTS generos (
`id` INT NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(25) NOT NULL,
PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS peliculas_generos;
CREATE TABLE IF NOT EXISTS peliculas_generos (
`codigoPelicula` INT,
`idGenero` INT,
PRIMARY KEY (`codigoPelicula`, `idGenero`),
FOREIGN KEY (`codigoPelicula`) REFERENCES peliculas (`codigo`),
FOREIGN KEY (`idGenero`) REFERENCES generos (`id`)
);

INSERT INTO peliculas (`codigo`, `titulo`, `director`, `url`)
VALUES
(1, 'Oppenheimer', 'Christopher Nolan', 'https://www.oppenheimermovie.com'),
(2, 'Inception', 'Christopher Nolan', 'https://www.warnerbros.com/movies/inception'),
(3, 'Interstellar', 'Christopher Nolan', 'https://www.warnerbros.co.uk/movies/interstellar'),
(4, 'Dunkirk', 'Christopher Nolan', 'http://unlock.dunkirkmovie.com/intl/nl/'),
(5, 'Tenet', 'Christopher Nolan', 'https://www.tenetfilm.com'),
(6, 'Memento', 'Christopher Nolan', 'https://www.imdb.com/title/tt0209144/'),
(7, 'The Prestige', 'Christopher Nolan', 'https://www.warnerbros.co.uk/movies/prestige'),
(8, 'Batman: Begins', 'Christopher Nolan', 'https://www.warnerbros.com/movies/batman-begins'),
(9, 'Batman: The Dark Knight', 'Christopher Nolan', 'https://www.warnerbros.com/movies/dark-knight'),
(10, 'Batman: The Dark Knight Rises', 'Christopher Nolan', 'https://www.warnerbros.com/movies/dark-knight-rises'),
(11, 'Prisoners', 'Denis Villeneuve', 'https://www.warnerbros.com/movies/prisoners'),
(12, 'Arrival', 'Denis Villeneuve', 'https://www.imdb.com/title/tt2543164/'),
(13, 'Blade Runner 2049', 'Denis Villeneuve', 'https://www.bladerunner2049movie.com/'),
(14, 'Enemy', 'Denis Villeneuve', 'https://www.imdb.com/title/tt2316411/'),
(15, 'Transcendence', 'Wally Pfister', 'https://www.warnerbros.com/movies/transcendence'),
(16, 'The Little Things', 'John Lee Hancock', 'https://www.warnerbros.com/movies/little-things'),
(17, 'A Monster Calls', 'Juan Antonio Bayona', 'https://www.uphe.com/movies/a-monster-calls'),
(18, 'The Whale', 'Darren Aronofsky', 'https://a24films.com/films/the-whale'),
(19, 'The Shape Of Water', 'Guillermo del Toro', 'https://www.20thcenturystudios.com/movies/the-shape-of-water'),
(20, 'Poor Things', 'Yorgos Lanthimos', 'https://www.poorthingsfilm.co.uk/home/'),
(21, 'The Mummy', 'Stephen Sommers', 'https://www.uphe.com/movies/the-mummy-1999'),
(22, 'The Mummy Returns', 'Stephen Sommers', 'https://www.uphe.com/movies/the-mummy-returns'),
(23, 'The Mummy: Tomb of the Dragon Emperor', 'Rob Cohen', 'https://www.uphe.com/movies/the-mummy-tomb-of-the-dragon-emperor'),
(24, 'Barbie', 'Greta Gerwig', 'https://www.warnerbros.com/movies/barbie'),
(25, 'The Avengers', 'Joss Whedon', 'https://www.marvel.com/movies/the-avengers'),
(26, 'Avengers: Age of Ultron', 'Joss Whedon', 'https://www.marvel.com/movies/avengers-age-of-ultron'),
(27, 'Avengers: Infinity War', 'Russo brothers', 'https://www.marvel.com/movies/avengers-infinity-war'),
(28, 'Avengers: Endgame', 'Russo brothers', 'https://www.marvel.com/movies/avengers-endgame'),
(29, 'La La Land', 'Damien Chazelle', 'https://www.lionsgate.com/movies/la-la-land'),
(30, 'The Notebook', 'Nick Cassavetes', 'https://www.warnerbros.com/movies/notebook');

-- prueba
select * from peliculas;

INSERT INTO generos (`id`, `nombre`)
VALUES
(1, 'Suspenso'),
(2, 'Ciencia ficción'),
(3, 'Acción'),
(4, 'Aventura'),
(5, 'Misterio'),
(6, 'Crimen'),
(7, 'Terror'),
(8, 'Fantasía'),
(9, 'Infantil'),
(10, 'Drama'),
(11, 'Romance'),
(12, 'Comedia'),
(13, 'Musical'),
(14, 'Bélico'),
(15, 'Época/Historia');

-- prueba
select * from generos;

INSERT INTO peliculas_generos (`codigoPelicula`, `idGenero`)
VALUES
(1, 1),(1, 14),(1, 15),
(2, 2),(2, 3),(2, 1),
(3, 2),(3, 4),(3, 1),
(4, 3),(4, 14),(4, 15),
(5, 3),(5, 2),
(6, 1),(6, 5),(6, 6),
(7, 1),(7, 10),
(8, 1),(8, 3),(8, 4),
(9, 1),(9, 3),(9, 4),(9, 10),
(10, 1),(10, 3),(10, 4),
(11, 1),(11, 5),(11, 6),(11, 10),
(12, 10),(12, 2),(12, 5),
(13, 2),(13, 8),(13, 1),
(14, 10),(14, 5),
(15, 8),(15, 10),(15, 2),(15, 1),
(16, 1),(16, 5),(16, 6),(16, 10),
(17, 8),(17, 9),
(18, 10),
(19, 10),(19, 8),(19, 11),
(20, 12),(20, 11),(20, 10),
(21, 3),(21, 4),(21, 8),
(22, 3),(22, 4),(22, 8),
(23, 3),(23, 4),(23, 8),
(24, 12),(24, 8),
(25, 3),(25, 2),(25, 8),
(26, 3),(26, 2),(26, 8),
(27, 3),(27, 2),(27, 8),
(28, 3),(28, 2),(28, 8),
(29, 10),(29, 11),(29, 12),(29, 13),
(30, 11),(30, 10);

----- Consultas PRUEBA -----
select * from peliculas;
select * from generos;
select * from peliculas_generos order by 1;

-- Metodo alternativo para insertar --
INSERT INTO peliculas_generos (codigoPelicula, idGenero)
SELECT codigo, id
FROM peliculas
CROSS JOIN generos
WHERE titulo = 'Oppenheimer' AND nombre IN (1, 15);
select * from peliculas_generos order by 1;

----- Consultas para JAVA (query) -----

-- SELECT_BY_TITLE
SELECT codigo, titulo FROM peliculas WHERE titulo LIKE ?;

-- GET_BY_TITLE
SELECT * FROM peliculas WHERE titulo LIKE ?;

-- SELECT_ALL_MOVIES
SELECT codigo, titulo FROM peliculas;

-- GET_ALL_MOVIES
SELECT * FROM peliculas;

-- SHOW_DETAILS
SELECT * FROM peliculas WHERE codigo = ?;

-- SELECT_ALL_GENRES
SELECT * FROM generos;

-- SELECT_BY_GENRE
SELECT p.* FROM peliculas p
INNER JOIN peliculas_generos pg ON p.codigo = pg.codigoPelicula
INNER JOIN generos g ON pg.idGenero = g.id
WHERE g.nombre LIKE ?
order by 1;

-- SELECT_GENRES_BY_MOVIEID
SELECT g.* FROM generos g
INNER JOIN peliculas_generos pg ON g.id = pg.idGenero
WHERE pg.codigoPelicula = ?;


----- Consultas PRUEBA -----
select p.titulo, g.nombre
from peliculas p, generos g, peliculas_generos pg
where p.codigo = pg.codigoPelicula
and g.id = pg.idGenero;
-- and p.codigo = 1;   

SELECT codigo, titulo
FROM peliculas
WHERE titulo LIKE '%opp%';

SELECT p.codigo, p.titulo FROM peliculas p 
JOIN peliculas_generos pg ON p.codigo = pg.codigoPelicula 
JOIN generos g ON pg.idGenero = g.id 
WHERE g.nombre LIKE '%sus%';