package com.celebrities.dao;

import com.celebrities.modal.Celebrities;
import org.slf4j.Logger;

import java.sql.Connection;
import java.util.List;

public interface CelebritiesDao {

    List<Celebrities> getCelebritiesDetails(Connection con, Logger log);

    int saveCelebritiesDetails(Celebrities celebrities, Connection con, Logger log);

    int updateCelebritiesDetails(Celebrities celebrities, Connection con, Logger log);
}
