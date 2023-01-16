create table customer
(
    id         bigint       not null auto_increment,
    birth_date date         not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table leasing_contract
(
    contract_number varchar(255)   not null,
    monthly_rate    decimal(19, 2) not null,
    vehicle_id      bigint         not null,
    customer_id     bigint         not null,
    primary key (customer_id, vehicle_id)
) engine=InnoDB;


create table vehicle
(
    id                    bigint           not null auto_increment,
    brand                 varchar(255)     not null,
    identification_number varchar(255),
    model_year            smallint         not null,
    price                 double precision not null,
    primary key (id)
) engine=InnoDB;


alter table leasing_contract
    add constraint UK_srimp5tauqe3elaqxctstvkpn unique (vehicle_id);


alter table leasing_contract
    add constraint UK_1319aj6bgnoc84r2wq9jn4ppb unique (contract_number);


alter table leasing_contract
    add constraint FKqpkf9hkk4ps9mg91ifuc16qni
        foreign key (vehicle_id)
            references vehicle (id);

alter table leasing_contract
    add constraint FKpxeeq63s4vh9272vr2tipsh2s
        foreign key (customer_id)
            references customer (id);