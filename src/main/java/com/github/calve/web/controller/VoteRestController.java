package com.github.calve.web.controller;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.User;
import com.github.calve.repository.VoteRepository;
import com.github.calve.to.UserTo;
import com.github.calve.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/profile";

    @Autowired
    private VoteRepository voteRepository;

    @PutMapping("/vote/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void vote(@PathVariable Integer id) {
        voteRepository.vote(SecurityUtil.authUserId(), id);
    }

    @DeleteMapping("/vote/")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void unlockVote() {
        voteRepository.unlockVote(SecurityUtil.authUserId());
    }

    @GetMapping
    List<Menu> getVoteList() {
        return voteRepository.getVoteList();
    }

    @GetMapping("/history")
    List<HistoryItem> getVoteHistory() {
        return voteRepository.getVoteHistory();
    }

    @GetMapping("/history/{id}")
    List<HistoryItem> getVoteHistoryByRestaurant(@PathVariable Integer id) {
        return voteRepository.getVoteHistoryByRestaurant(id);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        User created = null;//super.create(UserUtil.createNewFromTo(userTo)); //TODO
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
