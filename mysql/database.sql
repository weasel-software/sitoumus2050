-- Liferay portal db template
create database sitoumus_lportal character set utf8;
CREATE USER ***mysql_username*** IDENTIFIED BY '***mysql_password***';
GRANT ALL PRIVILEGES ON sitoumus_lportal.* TO '***mysql_username***'@'%' WITH GRANT OPTION;
