package com.example.tobi.springtobi.es1_6.Dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
  Connection makerNewConnection() throws SQLException, ClassNotFoundException;
}
