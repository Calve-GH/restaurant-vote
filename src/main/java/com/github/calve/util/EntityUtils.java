package com.github.calve.util;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;

import java.util.StringJoiner;

public class EntityUtils {
    private EntityUtils entityUtils = new EntityUtils();

    private EntityUtils() {
    }

    public static HistoryItem convertMenuToHistoryItem(Menu menu) {
        return new HistoryItem(menu.getDate(), menu.getRestaurant(), concatMenuItems(menu), menu.getVoteCount());
    }

    private static String concatMenuItems(Menu menu) {
        StringJoiner sj = new StringJoiner(" ");
        for (MenuItem item : menu.getItems()) {
            sj.add(item.getDish().getName());
            sj.add(item.getPrice().toString());
        }
        return sj.toString();
    }

    public EntityUtils getInstance() {
        return entityUtils;
    }


}
