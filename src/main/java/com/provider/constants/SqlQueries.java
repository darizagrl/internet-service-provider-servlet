package com.provider.constants;

public final class SqlQueries {
    public static final String CREATE_SERVICE = "INSERT INTO service VALUES (?)";
    public static final String FIND_SERVICE_BY_ID = "SELECT * FROM service WHERE service.id = ?";
    public static final String GET_ALL_SERVICE = "SELECT * FROM service ORDER BY id;";
    public static final String UPDATE_SERVICE_NAME = "UPDATE service SET name=? WHERE service.id=?";
    public static final String DELETE_SERVICE= "DELETE FROM service WHERE service.id = ?";
}
