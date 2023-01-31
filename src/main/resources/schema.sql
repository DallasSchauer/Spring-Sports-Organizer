create table account(
	id int auto_increment primary key,
	username varchar(20) not null,
	password varchar(20) not null,
	account_type int not null,
	constraint account_type_check check (0 <= account_type and account_type <= 2)
);

create table player(
	id int auto_increment primary key,
	name varchar(20) not null,
	dob Date not null,
	preferred_position varchar(20) default 'Any'
);

create table team(
	id int auto_increment primary key,
	manager_id int references player(id) on delete cascade,
	name varchar(30) not null,
	sport int default 0,
	min_age int default 0,
	max_age int default 100,
	constraint team_age_logic check (max_age >= min_age),
	constraint team_min_age_check check (min_age >= 0),
	constraint team_max_age_check check (max_age <= 150),
	constraint team_sport_check check (0 <= sport and sport <= 5)
);



create table event(
	id int auto_increment primary key,
	name varchar(40) not null,
	max_age int default 150,
	max_teams int not null,
	sport int not null,
	event_type int not null,
	avg_hours number(7,2) default 1
	constraint event_max_age_check check (0 <= max_age and max_age <= 150),
	constraint event_max_teams_check check (0 <= max_teams and max_teams <= 64),
	constraint event_type_check check (0 <= event_type and event_type <= 1),
	constraint event_sport_check check (0 <= sport and sport <= 5)
);

create table player_participates(
	id int auto_increment primary key,
	team_id int references team(id) on delete cascade,
	player_id int references player(id) on delete cascade
);
	
create table league_details(
	event_id int references event(id) on delete cascade,
	start_date date not null,
	end_date date not null,
	num_games int not null,
	days bigserial,
	earliest_time timestamp,
	latest_time timestamp,
	tournament_at_end boolean default false,
	constraint league_date_logic_check check (start_date <= end_date),
	constraint league_num_games_check check (num_games >= 1)
);

create table tournament_details(
	event_id int references event(id) on delete cascade,
	start_date date not null,
	end_date date not null,
	earliest_time timestamp,
	latest_time timestamp,
	tournament_type int not null,
	constraint tournament_date_logic_check check (start_date <= end_date),
	constraint tournament_time_logic_check check (earliest_time <= latest_time),
	constraint tournament_type_check check (tournament_type >= 0 and tournament_type <=2)
);

create table team_participates(
	id int auto_increment primary key,
	event_id int references event(id) on delete cascade,
	team_id int references team(id) on delete cascade
);

create table game(
	id int auto_increment primary key,
	event_id int references event(id) on delete cascade,
	game_time timestamp,
	away_team_id int default 0,
	home_team_id int default 0,
	away_score int default 0,
	home_score int default 0,
	finished boolean default false,
	winner_id int
);

