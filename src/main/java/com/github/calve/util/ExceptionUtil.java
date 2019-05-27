package com.github.calve.util;

public class ExceptionUtil {
    public static final String UNIQUE_EMAIL_IDX = "users_unique_email_idx";
    public static final String UNIQUE_DATETIME_IDX = "meals_unique_user_datetime_idx";

    public static final String UNIQUE_EMAIL_IDX_MSG = "User with this email already exists";
    public static final String UNIQUE_DATETIME_IDX_MSG = "Meal with this date already exists";

    public static final String DISH_UNIQUE_NAME_IDX = "DISH_UNIQUE_NAME_IDX";
    public static final String DISH_UNIQUE_NAME_IDX_MSG = "Dish with this name already exists";

    public static final String RESTAURANT_UNIQUE_NAME_IDX = "RESTAURANT_UNIQUE_NAME_IDX";
    public static final String RESTAURANT_UNIQUE_NAME_IDX_MSG = "Restaurant with this name already exists";

    public static final String MENU_UNIQUE_RESTAURANT_DATE_IDX = "MENU_UNIQUE_RESTAURANT_DATE_IDX";
    public static final String MENU_UNIQUE_RESTAURANT_DATE_IDX_MSG = "Menu with this restaurant and date already exists";

    public static final String VOTE_LOG_UNIQUE_USER_DATE_IDX = "USER_DATE_IDX";
    public static final String VOTE_LOG_UNIQUE_USER_DATE_IDX_MSG = "Vote with this user and date already exists";

    private ExceptionUtil() {}

    public static Throwable getCause(Throwable e) {
        Throwable cause = null;
        Throwable result = e;

        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
        }
        return result;
    }
}
