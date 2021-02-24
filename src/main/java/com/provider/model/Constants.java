package com.provider.model;

public class Constants {

    //Service
    public static final String CREATE_SERVICE = "INSERT INTO service(name) VALUES (?)";
    public static final String FIND_SERVICE_BY_ID = "SELECT * FROM service WHERE id = ?";
    public static final String GET_ALL_SERVICE = "SELECT * FROM service ORDER BY id;";
    public static final String UPDATE_SERV_NAME = "UPDATE service SET name=? WHERE id=?";
    public static final String DELETE_SERVICE = "DELETE FROM service WHERE id = ?";

    //Tariff
    public static final String FIND_TARIFFS_BY_SERVICE = "SELECT * FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.service_id=?;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_ASC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.name;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_DESC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.name DESC;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_ASC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.price ASC;";
    public static final String FIND_TARIFFS_BY_SERVICE_SORTED_BY_PRICE_DESC = "Select * from tariff inner join service on tariff.service_id = service.id where tariff.service_id=? order by tariff.price DESC";
    public static final String CREATE_TARIFF = "INSERT INTO tariff (name, description, price, service_id) VALUES (?,?,?,?)";
    public static final String FIND_TARIFF = "SELECT * FROM tariff INNER JOIN service ON tariff.service_id = service.id WHERE tariff.id=?;";
    public static final String FIND_ALL_TARIFFS = "Select * from tariff inner join service on tariff.service_id = service.id;";
    public static final String UPDATE_TARIFF = "UPDATE tariff SET name=?, description=?, price=?, service_id=? WHERE tariff.id=?;";
    public static final String DELETE_TARIFF = "DELETE FROM tariff WHERE tariff.id = ?;";

    //User
    public static final String CREATE_USER = "insert into users (firstname, lastname, email, password) values (?,?,?,?)";
    public static final String INSERT_USER_ROLE = "insert into users_roles (user_id, role_id) values (?,?)";
    public static final String FIND_USER = "SELECT * FROM public.users WHERE users.id = ?";
    public static final String FIND_ALL_USERS_AND_ROLES = "select usr.*, rl.* FROM public.users usr join public.users_roles ur on usr.id=ur.user_id join public.role rl on rl.id=ur.role_id;";
    public static final String UPDATE_USER = "UPDATE users SET firstname=?,lastname=?, email=?,password=?, isblocked=?,balance=? WHERE users.id=?;";
    public static final String DELETE_USER = "DELETE FROM users WHERE users.id = ?;";
    public static final String FIND_USER_TARIFFS = "SELECT * FROM users_tariffs INNER JOIN tariff ON users_tariffs.tariff_id = tariff.id inner join service on tariff.service_id= service.id where user_id = ?;";
    public static final String SUBSCRIBE_TARIFF = "INSERT INTO users_tariffs(user_id, tariff_id) VALUES (?,?);";
    public static final String UNSUBSCRIBE_TARIFF = "DELETE FROM users_tariffs where user_id = ? AND tariff_id=?;";
}
