INSERT INTO BLOGGERS (email,first_name,last_name)VALUES('petertailor@gmail.com','Peter','Tailor');
INSERT INTO BLOGGERS (email,first_name,last_name)VALUES('mester@gmail.com','Lars','Gronberg');
INSERT INTO BLOGGERS (email,first_name,last_name)VALUES('wizard@gmail.com','John','Snow');
INSERT INTO BLOGGERS (email,first_name,last_name)VALUES('lord@gmail.com','Tim','Denem');

INSERT INTO STORIES (story_line,blogger_id) 
values(
'uasygfuisaf asiuhfosiarrg oadihgoi oidsfgod oiisdifg oidg uhfiuasygfuisaf asiuhfosiarrg oadihgoi oidsfgod oiisdifg oidg uhfi.',
(select ID from BLOGGERS where first_name ='John'));

INSERT INTO STORIES (story_line,blogger_id) 
values(
'uasygfuisaf asiuhfosiarrg oadihgoi.',
(select ID from BLOGGERS where first_name ='Peter'));

INSERT INTO STORIES (story_line,blogger_id) 
values(
'uasygfuisduhfosiarrg oadihgoi oidsfgod oiisdifg oidg uhfi.',
(select ID from BLOGGERS where first_name ='Peter'));

INSERT INTO STORIES (story_line,blogger_id) 
values(
'uasygfuisaf asiuhfosiarrg oadihgouasygfuisaf asiuhfosiarrg oadihgoi oidsfgod oiisdifg oidg uhfi.',
(select ID from BLOGGERS where first_name ='Tim'));