package com.github.calve.web.controller;

/*@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
@ActiveProfiles("datajpa")*/
public class DataJpaRepositoryTest {

/*    @Autowired
    private TempRestController restController;

    @Test
    void getAllDishes() { // GET ALL DISHES FOR DATE AND RESTAURANT ID
        Set<MenuItem> allDishes = restController.getAllDishes(LocalDate.now(), 100001);
        allDishes.forEach(System.out::println);
    }

    @Test
    void getVoteItems() { //GET ALL DAILY RESTAURANTS for VOTE
        List<Menu> menus = restController.getMenus(LocalDate.now());
        System.out.println(menus);
        assertEquals(2, menus.size());
    }

    @Test
        //USER VOTE INCREMENT             DEPRECATED
    void vote() {
        Integer expected = restController.getMenu(LocalDate.now(), 100001).getVoteCount() + 1;

        restController.vote(LocalDate.now(), 100001);

        Integer actual = restController.getMenu(LocalDate.now(), 100001).getVoteCount();
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    void getAllVoteLog() {
        List<VoteLog> history = restController.getVotes();
        history.forEach(System.out::println);
    }

    @Test
    void unlockVotingForUserById() {
        restController.unlockVoiting(100014);
        List<VoteLog> history = restController.getVotes();
        history.forEach(System.out::println);
    }

    @Test
    void updateHistoryAndClearPreviousMenu() {
        System.out.println("ALL HISTORY");
        restController.getHistory().forEach(System.out::println);
        System.out.println("UPDATE HISTORY");
        restController.updateHistory();
        System.out.println("NEW HISTORY");
        restController.getHistory().forEach(System.out::println);
        System.out.println("MENUS:");
        restController.getMenus(LocalDate.now()).forEach(System.out::println);
    }*/


/*    void test() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        Cache<Long, String> writeBehindCache = cacheManager.createCache("writeBehindCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))
                        .withLoaderWriter(new SampleLoaderWriter<Long, String>(singletonMap(41L, "zero")))
                        .add(WriteBehindConfigurationBuilder
                                .newBatchedWriteBehindConfiguration(1, TimeUnit.SECONDS, 3)
                                .queueSize(3)
                                .concurrencyLevel(1)
                                .enableCoalescing())
                        .build());

        assertThat(writeBehindCache.get(41L), is("zero"));
        writeBehindCache.put(42L, "one");
        writeBehindCache.put(43L, "two");
        writeBehindCache.put(42L, "This goes for the record");
        assertThat(writeBehindCache.get(42L), equalTo("This goes for the record"));

        cacheManager.close();
    }*/
}
