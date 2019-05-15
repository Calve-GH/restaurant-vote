package com.github.calve.web.controller;

import com.github.calve.UserUtil;
import com.github.calve.model.*;
import com.github.calve.repository.SystemRepository;
import com.github.calve.repository.datajpa.*;
import com.github.calve.service.UserService;
import com.github.calve.to.UserTo;
import com.github.calve.util.ValidationUtil;
import com.github.calve.util.exception.IllegalRequestDataException;
import com.github.calve.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.github.calve.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@PropertySource("classpath:restaurant-vote.properties")
public class VoteRestController {
    public static final String REST_URL = "/rest/profile";

    private static Boolean CHANGE_VOTING_ENABLE = true;

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudUserRepository userRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemRepository systemRepository;

    @PutMapping("/vote/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    void vote(@PathVariable Integer id) {
        User user = userRepo.getOne(SecurityUtil.authUserId());
        ValidationUtil.checkNotFound(user, "current user id");
        Restaurant restaurant = restaurantRepo.findById(id).orElse(null);
        ValidationUtil.checkNotFound(restaurant, "current restaurant id");
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.save(voteLog);
    }

    @DeleteMapping("/vote")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void unlockVote() {
        if (CHANGE_VOTING_ENABLE)
            voteLogRepo.delete(SecurityUtil.authUserId());
        else
            throw new IllegalRequestDataException("Period of change vote is expired.");
    }

    @GetMapping
    List<Menu> getVoteList() {
        return menuRepo.findAllByDate(LocalDate.now());
    }

    @GetMapping("/vote/history")
    List<HistoryItem> getVoteHistory() {
        return historyRepo.findAll();
    }

    @GetMapping("/vote/history/{id}")
    List<HistoryItem> getVoteHistoryByRestaurant(@PathVariable Integer id) {
        return historyRepo.findByRestaurantId(id);
    }

    @GetMapping("/vote/restaurants")
    List<Restaurant> getRestaurants() {
        return restaurantRepo.getAll();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) throws Exception {
        User created = createUser(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Transactional
    User createUser(@RequestBody UserTo userTo) throws Exception {
        if (!userTo.isRestaurantEmpty()) {
            Restaurant restaurant = restaurantRepo.save(new Restaurant(userTo.getNewRestaurantName()));
            userTo.setRestaurant(restaurant);
        }
        User user = UserUtil.createNewFromTo(userTo);
        checkNew(user);
        return userService.create(user);
    }

    @Scheduled(cron = "${system.change.vote.time}")
    private void blockChangeVotingState() {
        CHANGE_VOTING_ENABLE = false;
    }

    @Scheduled(cron = "${system.restart.time}")
    private void systemRestart() {
        systemRepository.resetAndLogVoteSystem();
        CHANGE_VOTING_ENABLE = true;
    }
}
