# use ganymede_spring_db;
use ganymede_spring_blog;

insert into users (username, email, password) values
('bob123', 'bob@email.com', 'getmein'),
('plant_grl', 'plant@email.com', 'iloveplants'),
('bluecloud', 'blue@email.com', 'clouds');

# insert into posts (title, body, author_id) values
# ('Title 1', 'Body 1 asdf asdf asd fasdf as dfa sdfa sdf', 1),
# ('Title 2', 'Body 2 asdf asdf asd fasdf as dfa sdfa sdf', 1),
# ('Title 3', 'Body 3 asdf asdf asd fasdf as dfa sdfa sdf', 1),
# ('Title 4', 'Body 4 asdf asdf asd fasdf as dfa sdfa sdf', 1),
# ('Title 5', 'Body 5 asdf asdf asd fasdf as dfa sdfa sdf', 1);

insert into posts (title, body, author_id) values
('Environmental Tech', 'semper rutrum nulla nunc purus phasellus in felis donec semper sapien', 1),
('Structural Analysis Engineer', 'curabitur convallis duis consequat dui nec nisi volutpat eleifend donec ut dolor morbi vel lectus', 1),
('Senior Editor', 'vivamus in felis eu sapien cursus vestibulum proin eu mi', 1),
('Programmer II', 'libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu', 1),
('Health Coach II', 'vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum', 1),
('Social Worker', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat', 1),
('Speech Pathologist', 'felis sed interdum venenatis turpis enim blandit mi in porttitor pede justo eu massa donec dapibus duis at velit', 1),
('VP Sales', 'proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue', 1),
('Senior Sales Associate', 'hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat', 1),
('Structural Engineer', 'eu sapien cursus vestibulum proin eu mi nulla ac enim', 1);
