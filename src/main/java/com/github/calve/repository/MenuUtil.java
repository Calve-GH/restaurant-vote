package com.github.calve.repository;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.to.MenuTo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class MenuUtil {

    public static Menu createFromTo(MenuTo menuTo) {
        Menu menu = createNewFromTo(menuTo);
        menu.setId(menuTo.getId());
        return menu;
    }

    public static Menu createNewFromTo(MenuTo menuTo) {
        Menu newMenu = new Menu(menuTo.getDate(), menuTo.getRestaurant());
        for (MenuItem item : menuTo.getMenuItems()) {
            newMenu.addMenuItem(item);
        }
        return newMenu;
    }

    public static Menu createNewFromToNoRef(MenuTo menuTo) {
        return new Menu(menuTo.getDate(), menuTo.getRestaurant());
    }

    public static HistoryItem convertMenuToHistoryItem(Menu menu) {
        return new HistoryItem(menu.getDate(), menu.getRestaurant(), concatMenuItems(menu), menu.getVoteCount());
    }

    public static List<HistoryItem> convertMenuListToHistoryList(List<Menu> menus) {
        if (menus == null) return new ArrayList<>();
        return menus.stream().map(MenuUtil::convertMenuToHistoryItem).collect(Collectors.toList());
    }

    private static String concatMenuItems(Menu menu) {
        if (menu.getItems().isEmpty()) return "Empty";
        StringJoiner sj = new StringJoiner(" ");
        for (MenuItem item : menu.getItems()) {
            sj.add(item.getDish().getName());
            sj.add(":");
            sj.add(item.getPrice().toString());
        }
        return sj.toString();
    }
}
