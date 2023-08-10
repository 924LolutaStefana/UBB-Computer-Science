create table multimediafiles
(
    id   int          not null primary key AUTO_INCREMENT ,
    title    varchar(100) not null,
    format_type varchar(100) not null,
    genre varchar(100) not null,
    path    varchar(100) not null,
    
);