package com.test.app.neo4j.athlete.controller;

import com.test.app.neo4j.athlete.model.Team;
import com.test.app.neo4j.athlete.repository.TeamRepository;
import com.test.app.neo4j.athlete.service.TeamService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/teams")
public class TeamController {

    @Inject
    private TeamRepository teamRepository;

    @Get("/")
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

}
