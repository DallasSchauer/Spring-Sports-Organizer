# Spring Boot Sports Organizer Project
## Created by Dallas Schauer

This project acts as a full-stack web site acting as a sports organization tool, meaning
a web site that emulates the real life applications used by many recreational centers to
organize local sporting events. This app lets users create accounts, organize and join 
different sporting events divided into leagues and tournaments, and create and join teams.
The program takes care of scheduling, maintaining rules, and providing a convenient
user interface.

- Frontend: HTML/CSS/JS
- Backend: Java Spring Boot
- DB: H2/MySQL

### How to Run

1. Install an IDE that can run Spring Boot applications. There are many IDEs that can do this,
but the one I recommend is Spring Tool Suite.
2. Download the project.
3. In Spring Tool Suite (or whichever IDE you choose), select File -> Open Projects from File System
4. Run the project as a Spring Boot App.
5. Open a web browser and navigate to localhost:8080


### TO DO

- HTML Tourney page
- Fix advancement logic:
	- If no teams -> set winner as home team
	- If winner == home or away -> no change
	- If only home team and hometeam = current loser -> replace home with winner 
	- If only home team and winner > home -> set home as away, set winner as home
	- If only home team and winner < home -> set winner as away
	- If both teams and current loser == home AND away > winner -> set away as home, set winner as away
	- If both teams and current loser == home AND away < winner -> set winner as home
	- If both teams and current loser == away AND home < winner -> set home as away, set winner as home
	- If both teams and current loser == away AND home > winner -> set winner as away
	- THROW ERROR, CASE NOT COVERED
- Make more games, some finished, some not.
- Make more events, teams, and managers