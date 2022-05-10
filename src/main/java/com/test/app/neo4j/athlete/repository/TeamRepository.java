package com.test.app.neo4j.athlete.repository;

import com.test.app.neo4j.athlete.model.Team;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Node;

import java.util.List;

@Singleton
public class TeamRepository {

    private final Driver driver;

    TeamRepository(Driver driver) {
        this.driver = driver;
    }

    public List<Team> findAll() {
        try (var session = driver.session()) {
            var query = ""
                    + "MATCH (t:Team)\n"
                    + "RETURN t";

            return session.readTransaction(tx -> tx.run(query).list(r -> {
                var teamNode = r.get("t").asNode();

                Team t = asTeam(teamNode);
                return t;
            }));
        }
    }

    static Team asTeam(Node teamNode) {
        var t = new Team();
        t.setOwnerName(teamNode.get("ownweName").asString());
        t.setName(teamNode.get("name").asString());
        return t;
    }

}
