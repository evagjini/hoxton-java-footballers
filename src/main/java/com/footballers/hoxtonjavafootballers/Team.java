package com.footballers.hoxtonjavafootballers;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Entity
public class Team {
    @Id
    @GeneratedValue

    public Integer id;
    public String name;

    @OneToMany(mappedBy = "team")
    public Set<Player> players;

    public Team() {

    }

}

@RestController

class TeamController {
    @Autowired

    private TeamRepo teamRepo;

    @GetMapping("/teams")
    public List<Team> getAllPlayers() {
        return teamRepo.findAll();
    }

    @PostMapping("/teams")
    public Team createTeams(@RequestBody Team teamData) {
        return teamRepo.save(teamData);
    }
}

interface TeamRepo extends JpaRepository<Team, Integer> {

}

// not done