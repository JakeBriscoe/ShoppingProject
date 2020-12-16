create table Products (
    productId varchar(10),
    name varchar(20) not null,
    description varchar(100),
    category varchar(20) not null,
    price decimal(6,2) not null,
    quantityInStock integer not null,

    constraint Products_PK primary key (productId)
);

create table Customers (
    customerId bigint auto_increment (1000),
    username varchar(10) not null unique,
    firstname varchar(20) not null,
    surname varchar(20) not null,
    password varchar(20) not null,
    emailAddress varchar(30) not null,
    shippingAddress varchar(100) not null,

    constraint Customers_PK primary key (customerId)    
);

create table Sale (
    saleId bigint auto_increment (10000),
    status varchar(15) not null,
    date timestamp not null,
    customerId bigint,
    
    constraint Sale_PK primary key (saleId),

    constraint Sale_Customer foreign key (customerId) references Customers
);

create table SaleItem (
    quantityPurchased integer not null,
    salePrice decimal(6,2) not null,
    productId varchar(10),
    saleId bigint,

    constraint SaleItem_PK primary key (productId, saleId),

    constraint SaleItem_Products_FK foreign key (productId) references Products,
    constraint SaleItem_Sale_FK foreign key (saleId) references Sale
)
