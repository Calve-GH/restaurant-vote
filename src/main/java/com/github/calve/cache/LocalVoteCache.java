package com.github.calve.cache;

//import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalVoteCache {
    private boolean enabled = true;
    private final static LocalVoteCache localVoteCache = new LocalVoteCache();
    private final Map<Integer, Integer> localVoteCacheMap = new ConcurrentHashMap<>();
//@Autowired
//    private dbvotecache;

    private LocalVoteCache() {
    }

    public static LocalVoteCache getInstance() {
        return localVoteCache;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean addUserVote(Integer userId, Integer restaurantId) {
        //dbvotecache increment
        return localVoteCacheMap.putIfAbsent(userId, restaurantId) == null;
    }

    public boolean isVoited(Integer userId) {
        return localVoteCacheMap.containsKey(userId);
    }

    public boolean resetUserVote(Integer userId) {//SR
        if(isVoited(userId)) {
            //dbvotecache decrement
            localVoteCacheMap.remove(userId);
        }
        return true;
    }





}
