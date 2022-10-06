package com.footballers.hoxtonjavafootballers;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player {

    @Id
    @GeneratedValue
    public Integer id;
    public String name;
    public String nationality;
    public Integer scoreOutOfTen;
    public Boolean isReplacement;

    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "teamId", nullable = false)

    public Team team;

    public Player() {

    }

}

@RestController

class PlayerController {
    @Autowired

    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getSinglePlayer(@PathVariable Integer Id) {
        Optional<Player> match = playerRepo.findById(Id);

        if (match.isPresent()) {
            return new ResponseEntity<>(match.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teams/{teamId}/players")
    public Player createPlayer(@RequestBody Player playerData, @PathVariable Integer teamId) {
        playerData.team = teamRepo.findById(teamId).get();
        return playerRepo.save(playerData);
    }

}

interface PlayerRepo extends JpaRepository<Player, Integer> {

}

interface TeamRepo extends JpaRepository<Team, Integer> {

}
