package com.celebrities.service;

import com.celebrities.modal.Celebrities;
import org.slf4j.Logger;

import java.util.List;

public interface CelebritiesService {

    List<Celebrities> getCelebritiesDetails(Logger log);

    String saveCelebritiesDetails(Celebrities celebrities, Logger log);

    String updateCelebritiesDetails(Celebrities celebrities, Logger log);

    String getBestTime(Logger log);
}
