create table races
(
    id    smallserial primary key,
    label text not null unique
);

create table factions
(
    id     smallserial primary key,
    name   text     not null unique,
    race   smallint not null references races (id),
    banner text     not null
);

create table lords
(
    id                     smallserial primary key,
    name                   text     not null unique,
    race                   smallint not null references races (id),
    faction                smallint not null references factions (id),
    cost                   smallint not null,
    upkeep                 smallint not null,
    health                 smallint not null,
    leadership             smallint not null,
    speed                  smallint not null,
    melee_attack           smallint not null,
    melee_defence          smallint not null,
    charge_bonus           smallint not null,
    missile_resistance     smallint not null,
    magic_resistance       smallint not null,
    armor_protection       smallint not null,
    weapon_type            smallint references weapon_types (id),
    weapon_damage          smallint not null,
    armour_piercing_damage smallint not null,
    melee_interval         smallint not null,
    magical_attack         smallint not null,
    range                  smallint not null,
    unit_size              smallint not null,
    turns                  smallint not null
);

create table heroes
(
    id                     smallserial primary key,
    name                   text     not null unique,
    race                   smallint not null references races (id),
--     checkme
    faction                smallint not null references factions (id),
    cost                   smallint not null,
    upkeep                 smallint not null,
    health                 smallint not null,
    leadership             smallint not null,
    speed                  smallint not null,
    melee_attack           smallint not null,
    melee_defence          smallint not null,
    charge_bonus           smallint not null,
    missile_resistance     smallint not null,
    magic_resistance       smallint not null,
    armor_protection       smallint not null,
    weapon_type            smallint references weapon_types (id),
    weapon_damage          smallint not null,
    armour_piercing_damage smallint not null,
    melee_interval         smallint not null,
    magical_attack         smallint not null,
    range                  smallint not null,
    unit_size              smallint not null,
    turns                  smallint not null
);

create table heroes
(
    id                     smallserial primary key,
    name                   text     not null unique,
    --     checkme
    race                   smallint not null references races (id),
    --     checkme
    faction                smallint not null references factions (id),
    category               smallint references categories (id),
    cost                   smallint not null,
    upkeep                 smallint not null,
    health                 smallint not null,
    leadership             smallint not null,
    speed                  smallint not null,
    melee_attack           smallint not null,
    melee_defence          smallint not null,
    charge_bonus           smallint not null,
    missile_resistance     smallint not null,
    magic_resistance       smallint not null,
    armor_protection       smallint not null,
    weapon_type            smallint references weapon_types (id),
    weapon_damage          smallint not null,
    armour_piercing_damage smallint not null,
    melee_interval         smallint not null,
    magical_attack         smallint not null,
    range                  smallint not null,
    unit_size              smallint not null,
    turns                  smallint not null
);

create table weapon_types
(
    id   smallserial primary key,
    type text not null
);

create table attributes
(
    id          smallserial primary key,
    description text not null unique
);

create table categories
(
    id   smallserial primary key,
    name text not null
);

create table attributes_lords
(
    attribute_id smallint references attributes (id),
    lord_id      smallint references lords (id)
);

create table attributes_heroes
(
    attribute_id smallint references attributes (id),
    lord_id      smallint references heroes (id)
);

create table attributes_heroes
(
    attribute_id smallint references attributes (id),
    lord_id      smallint references heroes (id)
);




