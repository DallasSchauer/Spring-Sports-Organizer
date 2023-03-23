insert into player (id, username, password, name, dob) values 
 (0, 'SUPER_USER', 'Admin123!', 'Super User', '2000-01-01');
insert into player (username, password, name, dob, favorite_sport) values 
 ('PhoenixFireE3', 'User1!', 'Alyssa', '1999-12-26', 1);
insert into player (username, password, name, dob, favorite_sport) values 
 ('Ori', 'User1!', 'Brody', '1999-09-26', 0);
insert into player (username, password, name, dob, favorite_sport) values 
 ('CSpokesperson', 'User1!', 'Casey', '2000-05-02', 2);
insert into player (username, password, name, dob, favorite_sport) values 
 ('djschauer', 'User1!', 'Dallas', '1999-10-29', 4);
insert into player (username, password, name, dob, favorite_sport) values
 ('xDemonChip', 'User1!', 'Dylan', '2000-02-01', 6);
insert into player (username, password, name, dob) values 
 ('HunterL', 'User1!', 'Hunter L', '2000-01-01');
insert into player (username, password, name, dob, favorite_sport) values
 ('Scarbruh', 'User1!', 'Hunter S', '2000-01-17', 1);
insert into player (username, password, name, dob, favorite_sport) values 
 ('LC', 'User1!', 'Justin', '2000-05-19', 5);
insert into player (username, password, name, dob, favorite_sport) values 
 ('Shiver', 'User1!', 'Ryan', '2000-01-01', 6); 
insert into player (username, password, name, dob, favorite_sport) values 
 ('SpookyJohnson', 'User1!', 'Savion', '2000-01-16', 0);
insert into player (username, password, name, dob, favorite_sport) values 
 ('ShawnMan', 'User1!', 'Shawn', '2000-01-29', 2);
insert into player (username, password, name, dob, favorite_sport) values 
 ('Randall', 'User1!', 'Thomas', '2000-02-04', 3);
insert into player (username, password, name, dob) values 
 ('Rexriptor', 'User1!', 'Tyler', '2000-01-07');
insert into player (username, password, name, dob, favorite_sport) values 
 ('Yurster', 'User1!', 'Yuriy', '2000-06-03', 5);
insert into player (username, password, name, dob) values 
 ('Froggery', 'User1!', 'Zack', '2000-05-19');

insert into team (id, manager_id, name) values (0, 0, 'BYE');
insert into team (manager_id, name, sport) values (2, 'Freddies Freakers', 0);
insert into team (manager_id, name, sport) values (12, 'Hip Cats', 0);
insert into team (manager_id, name, sport) values (3, 'El Philippinos', 0);
insert into team (manager_id, name, sport) values (5, 'The Crows', 0);
insert into team (manager_id, name, sport) values (4, 'F AND F', 0);
insert into team (manager_id, name, sport) values (7, 'Tubby Hunters', 0);

insert into player_participates (team_id, player_id) values (1, 2);
insert into player_participates (team_id, player_id) values (1, 8);
insert into player_participates (team_id, player_id) values (2, 12);
insert into player_participates (team_id, player_id) values (2, 15);
insert into player_participates (team_id, player_id) values (3, 3);
insert into player_participates (team_id, player_id) values (3, 14);
insert into player_participates (team_id, player_id) values (4, 5);
insert into player_participates (team_id, player_id) values (4, 10);
insert into player_participates (team_id, player_id) values (5, 1);
insert into player_participates (team_id, player_id) values (5, 4);
insert into player_participates (team_id, player_id) values (6, 6);
insert into player_participates (team_id, player_id) values (6, 7);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values 
('2019 Tennis Cup', 4, 6, 0, 1, .25);
insert into tournament_details (event_id, start_date, end_date, tournament_type)
values (1, '2020-06-13', '2020-06-23', 1);

insert into team_participates (event_id, team_id) values (1, 1);
insert into team_participates (event_id, team_id) values (1, 2);
insert into team_participates (event_id, team_id) values (1, 3);
insert into team_participates (event_id, team_id) values (1, 4);
insert into team_participates (event_id, team_id) values (1, 5);
insert into team_participates (event_id, team_id) values (1, 6);


insert into team (manager_id, name, sport) values (2, 'GFuel Boys', 0);
insert into team (manager_id, name, sport) values (8, 'HBEMTLP', 0);
insert into team (manager_id, name, sport) values (9, 'League of Legends', 0);

insert into player_participates (team_id, player_id) values (7, 2);
insert into player_participates (team_id, player_id) values (7, 11);
insert into player_participates (team_id, player_id) values (8, 7);
insert into player_participates (team_id, player_id) values (8, 8);
insert into player_participates (team_id, player_id) values (9, 5);
insert into player_participates (team_id, player_id) values (9, 9);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values 
('2020 Tennis Cup', 0, 6, 0, 1, .25);
insert into tournament_details (event_id, start_date, end_date, tournament_type)
values (2, '2020-06-13', '2020-06-13', 1);

insert into team_participates (event_id, team_id) values (2, 2);
insert into team_participates (event_id, team_id) values (2, 3);
insert into team_participates (event_id, team_id) values (2, 5);
insert into team_participates (event_id, team_id) values (2, 7);
insert into team_participates (event_id, team_id) values (2, 8);
insert into team_participates (event_id, team_id) values (2, 9);


insert into team (manager_id, name, sport) values (8, 'Hy-Vee Heroes', 0);
insert into team (manager_id, name, sport) values (4, 'Family Guys', 0);
insert into team (manager_id, name, sport) values (9, 'Hydration Nation', 0);
insert into team (manager_id, name, sport) values (2, 'Jars', 0);

insert into player_participates (team_id, player_id) values (10, 7);
insert into player_participates (team_id, player_id) values (10, 8);
insert into player_participates (team_id, player_id) values (11, 4);
insert into player_participates (team_id, player_id) values (11, 12);
insert into player_participates (team_id, player_id) values (12, 5);
insert into player_participates (team_id, player_id) values (12, 15);
insert into player_participates (team_id, player_id) values (13, 1);
insert into player_participates (team_id, player_id) values (13, 2);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values 
('2021 Tennis Cup', 0, 6, 0, 1, .25);
insert into tournament_details (event_id, start_date, end_date, tournament_type)
values (3, '2021-08-09', '2021-08-09', 1);

insert into team_participates (event_id, team_id) values (3, 3);
insert into team_participates (event_id, team_id) values (3, 10);
insert into team_participates (event_id, team_id) values (3, 11);
insert into team_participates (event_id, team_id) values (3, 12);
insert into team_participates (event_id, team_id) values (3, 13);
 
insert into team (manager_id, name, sport) values (3, 'Legally Blonde', 0);
insert into team (manager_id, name, sport) values (2, 'The Kongz', 0);
insert into team (manager_id, name, sport) values (1, 'Church of Based', 0);
insert into team (manager_id, name, sport) values (5, 'Eye of the Spider', 0);
insert into team (manager_id, name, sport) values (7, 'Stone Men', 0);
insert into team (manager_id, name, sport) values (4, 'Money Monkey Men', 0);

insert into player_participates (team_id, player_id) values (14, 3);
insert into player_participates (team_id, player_id) values (14, 12);
insert into player_participates (team_id, player_id) values (15, 2);
insert into player_participates (team_id, player_id) values (15, 14);
insert into player_participates (team_id, player_id) values (16, 1);
insert into player_participates (team_id, player_id) values (16, 15);
insert into player_participates (team_id, player_id) values (17, 5);
insert into player_participates (team_id, player_id) values (18, 7);
insert into player_participates (team_id, player_id) values (18, 11);
insert into player_participates (team_id, player_id) values (19, 4);
insert into player_participates (team_id, player_id) values (19, 8);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values 
('2022 Tennis Cup', 0, 6, 0, 1, .25);
insert into tournament_details (event_id, start_date, end_date, tournament_type)
values (4, '2022-09-11', '2022-09-11', 1);

insert into team_participates (event_id, team_id) values (4, 14);
insert into team_participates (event_id, team_id) values (4, 15);
insert into team_participates (event_id, team_id) values (4, 16);
insert into team_participates (event_id, team_id) values (4, 17);
insert into team_participates (event_id, team_id) values (4, 18);
insert into team_participates (event_id, team_id) values (4, 19);

insert into player (username, password, name, dob) values 
 ('IH8BASEBALL', 'User1!', 'Rob Manfred', '1950-02-03');

insert into team (manager_id, name, sport) values (16, 'New York Yankees', 1);
insert into team (manager_id, name, sport) values (16, 'New York Mets', 1);
insert into team (manager_id, name, sport) values (16, 'Boston Red Sox', 1);
insert into team (manager_id, name, sport) values (16, 'Chicago Cubs', 1);
insert into team (manager_id, name, sport) values (16, 'Houston Astros', 1);
insert into team (manager_id, name, sport) values (16, 'St Louis Cardinals', 1);
insert into team (manager_id, name, sport) values (16, 'Los Angeles Dodgers', 1);
insert into team (manager_id, name, sport) values (16, 'San Francisco Giants', 1);
insert into team (manager_id, name, sport) values (16, 'Atlanta Braves', 1);
insert into team (manager_id, name, sport) values (16, 'Minnesota Twins', 1);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values
('2023 MLB', 4, 10, 1, 0, 3.0);
insert into league_details (event_id, start_date, end_date, num_games)
values (5, '2022-04-01', '2022-09-30', 20);

insert into team_participates (event_id, team_id) values (5, 20);
insert into team_participates (event_id, team_id) values (5, 21);
insert into team_participates (event_id, team_id) values (5, 22);
insert into team_participates (event_id, team_id) values (5, 23);
insert into team_participates (event_id, team_id) values (5, 24);
insert into team_participates (event_id, team_id) values (5, 25);
insert into team_participates (event_id, team_id) values (5, 26);
insert into team_participates (event_id, team_id) values (5, 27);
insert into team_participates (event_id, team_id) values (5, 28);
insert into team_participates (event_id, team_id) values (5, 29);

insert into player (username, password, name, dob) values 
 ('NBApres', 'User1!', 'Adam Silver', '1962-07-21');

insert into team (manager_id, name, sport) values (17, 'Shirts', 2);
insert into team (manager_id, name, sport) values (17, 'Skins', 2);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values
('2023 Summer Basketball Games', 0, 2, 2, 0, 1);

insert into team_participates (event_id, team_id) values (6, 30);
insert into team_participates (event_id, team_id) values (6, 31);

insert into player_participates (team_id, player_id) values (30, 1);
insert into player_participates (team_id, player_id) values (31, 2);
insert into player_participates (team_id, player_id) values (30, 3);
insert into player_participates (team_id, player_id) values (31, 4);
insert into player_participates (team_id, player_id) values (30, 5);
insert into player_participates (team_id, player_id) values (31, 7);
insert into player_participates (team_id, player_id) values (30, 8);
insert into player_participates (team_id, player_id) values (31, 9);
insert into player_participates (team_id, player_id) values (30, 10);
insert into player_participates (team_id, player_id) values (31, 11);
insert into player_participates (team_id, player_id) values (30, 12);
insert into player_participates (team_id, player_id) values (31, 13);
insert into player_participates (team_id, player_id) values (30, 14);
insert into player_participates (team_id, player_id) values (31, 15);

insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-08-05 18:00:00', 30, 31); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-08-19 18:00:00', 31, 30);  
insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-06-10 18:00:00', 30, 31); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-06-24 18:00:00', 31, 30); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-07-08 18:00:00', 30, 31); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (6, '2023-07-22 18:00:00', 31, 30);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values
('2022 Summer Basketball Games', 4, 2, 2, 0, 1);

insert into team_participates (event_id, team_id) values (7, 30);
insert into team_participates (event_id, team_id) values (7, 31);

insert into game (event_id, game_time, away_team_id, home_team_id, 
away_score, home_score, finished, winner_id) values 
(7, '2022-06-09', 30, 31, 21, 14, TRUE, 30);
insert into game (event_id, game_time, away_team_id, home_team_id, 
away_score, home_score, finished, winner_id) values 
(7, '2022-06-23', 31, 30, 21, 20, TRUE, 30);
insert into game (event_id, game_time, away_team_id, home_team_id, 
away_score, home_score, finished, winner_id) values 
(7, '2022-07-07', 30, 31, 20, 21, TRUE, 31);
insert into game (event_id, game_time, away_team_id, home_team_id, 
away_score, home_score, finished, winner_id) values 
(7, '2022-07-21', 31, 30, 21, 10, TRUE, 31);

insert into player (username, password, name, dob) values 
 ('Commish', 'User1!', 'Roger Goodell', '1962-07-21');

insert into team (manager_id, name, sport) values (18, 'North', 3);
insert into team (manager_id, name, sport) values (18, 'East', 3);
insert into team (manager_id, name, sport) values (18, 'West', 3);
insert into team (manager_id, name, sport) values (18, 'South', 3);

insert into event (name, commissioner, max_teams, sport, event_type, avg_hours) values 
('2023 Football League', 4, 4, 3, 0, 1);

insert into team_participates (event_id, team_id) values (8, 32);
insert into team_participates (event_id, team_id) values (8, 33);
insert into team_participates (event_id, team_id) values (8, 34);
insert into team_participates (event_id, team_id) values (8, 35);

insert into player_participates (team_id, player_id) values (32, 4);

insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-08-04 18:00:00', 32, 34); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-08-20 18:00:00', 33, 35);
  
insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-06-19 18:00:00', 34, 33); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-06-22 18:00:00', 35, 32);
 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-07-10 18:00:00', 35, 34); 
insert into game (event_id, game_time, away_team_id, home_team_id)
values (8, '2023-07-20 18:00:00', 32, 33);

