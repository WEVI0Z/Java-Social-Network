create table users (
    id bigserial not null,
    email varchar(255) not null,
    primary key(id)
);

create table posts (
   id bigserial not null,
   content text not null,
   user_id int not null,
   creation_date timestamp not null,
   primary key(id),
   CONSTRAINT fk_user FOREIGN KEY (user_id) references users(id) on delete cascade
);

create table groups (
   id bigserial not null,
   name varchar not null,
   primary key(id)
);

create table user_group (
    user_id int not null,
    group_id int not null,
    primary key(user_id, group_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) references users(id) on delete cascade,
    CONSTRAINT fk_group FOREIGN KEY (group_id) references groups(id) on delete cascade
);

create table comments (
   id bigserial not null,
   post_id int not null,
   user_id int not null,
   content text not null,
   creation_time timestamp not null,
   primary key(id),
   CONSTRAINT fk_user FOREIGN KEY (user_id) references users(id) on delete cascade,
   CONSTRAINT fk_post FOREIGN KEY (post_id) references posts(id) on delete cascade
);