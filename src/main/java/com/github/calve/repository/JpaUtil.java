package com.github.calve.repository;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache() {
        Session s = (Session) em.getDelegate();
        SessionFactory sf = s.getSessionFactory();
        sf.getCache().evictAllRegions();
    }

    public static HistoryItem convertMenuToHistoryItem(Menu menu) {
        return new HistoryItem(menu.getDate(), menu.getRestaurant(), concatMenuItems(menu), menu.getVoteCount());
    }

    public static List<HistoryItem> convertMenuListToHistoryList(List<Menu> menus) {
        return menus.stream().map(JpaUtil::convertMenuToHistoryItem).collect(Collectors.toList());
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
