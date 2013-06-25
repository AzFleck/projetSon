INSERT INTO Type VALUES (1, 'Actor');
INSERT INTO Type VALUES (2, 'Director');
INSERT INTO Type VALUES (3, 'Artist');

INSERT INTO Album VALUES (1, 'Album1', '2013-01-01');
INSERT INTO Album VALUES (2, 'Album2', '2011-01-01');

INSERT INTO Person VALUES (1, 'Willis', 'Bruce', 'American');
INSERT INTO Person VALUES (2, 'Dujardin', 'Jean', 'French');
INSERT INTO Person VALUES (3, 'Fox', 'Megan', 'American');
INSERT INTO Person VALUES (4, 'Guetta', 'David', 'French');

INSERT INTO File VALUES (1, 'Transformers', '2013-01-01', '90', "//Ici", 0);
INSERT INTO File VALUES (2, 'Armageddon', '2008-01-01', '98', "//Ici", 0);
INSERT INTO File VALUES (3, 'Very Bad Trip', '2012-02-11', '110', "//Ici", 0);
INSERT INTO File VALUES (4, 'Memories', '2007-01-01', '90', "//Ici", 0);
INSERT INTO File VALUES (5, 'Play Hard', '2009-01-01', '90', "//Ici", 0);
INSERT INTO File VALUES (6, 'Sexy Bitch', '2010-01-01', '90', "//Ici", 0);

INSERT INTO Music VALUES (4, 1);
INSERT INTO Music VALUES (5, 1);
INSERT INTO Music VALUES (6, 2);

INSERT INTO Sort VALUES (1, 'Action', 1);
INSERT INTO Sort VALUES (2, 'Adventure', 1);
INSERT INTO Sort VALUES (3, 'Electro', 2);
INSERT INTO Sort VALUES (4, 'Classic', 2);

INSERT INTO Movie VALUES (1, 'Film bien');
INSERT INTO Movie VALUES (2, 'Film aussi bien');
INSERT INTO Movie VALUES (3, 'Film pareil');

INSERT INTO PersonType VALUES (1, 1);
INSERT INTO PersonType VALUES (1, 2);
INSERT INTO PersonType VALUES (2, 1);
INSERT INTO PersonType VALUES (3, 1);

INSERT INTO FileSort VALUES (1, 1);
INSERT INTO FileSort VALUES (1, 2);
INSERT INTO FileSort VALUES (2, 2);
INSERT INTO FileSort VALUES (3, 2);
INSERT INTO FileSort VALUES (4, 3);
INSERT INTO FileSort VALUES (5, 4);
INSERT INTO FileSort VALUES (6, 4);

INSERT INTO PersonFile VALUES (1, 1, 1);
INSERT INTO PersonFile VALUES (1, 2, 1);
INSERT INTO PersonFile VALUES (2, 3, 1);
INSERT INTO PersonFile VALUES (3, 1, 1);
INSERT INTO PersonFile VALUES (4, 4, 3);
INSERT INTO PersonFile VALUES (4, 5, 3);
INSERT INTO PersonFile VALUES (4, 6, 3);

INSERT INTO PlayList VALUES (1, "Playlist1");
INSERT INTO PlayList VALUES (2, "Playlist2");

INSERT INTO FilePlayList VALUES (4, 1);
INSERT INTO FilePlayList VALUES (5, 1);
INSERT INTO FilePlayList VALUES (6, 1);
INSERT INTO FilePlayList VALUES (1, 2);
INSERT INTO FilePlayList VALUES (2, 2);
INSERT INTO FilePlayList VALUES (3, 2);







