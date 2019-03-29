package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        for (UserMealWithExceed i : filteredWithExceeded) {
            //System.out.println(i.toString());
            System.out.println(i.getDateTime() + " " + i.getDescription() + " " + i.getCalories() + " " + i.isExceed());

        }
        //System.out.println("===================");


    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

//        System.out.println("===================");


        //  List<UserMealWithExceed> newUserMealList=Arrays.asList();
        //  for (int i = 0; i < mealList.size(); i++) {
        //      UserMeal element = mealList.get(i);
        //newUserMealList.add(element.getDateTime());

        //  newUserMealList = new UserMealWithExceed(element.getDateTime(), element.getDescription(), element.getCalories(), true);
        //  }


        Map<LocalDate, Integer> myMap = new HashMap<LocalDate, Integer>();
        for (int i = 0; i < mealList.size(); i++) {
            UserMeal element = mealList.get(i);
            LocalDate localDate = element.getDateTime().toLocalDate();
            myMap.merge(localDate, element.getCalories(), (ov, nv) -> ov + nv);
        }
        List<UserMealWithExceed> resultList = new ArrayList<>();
        for (int i = 0; i < mealList.size(); i++) {
            UserMeal element = mealList.get(i);

            if (TimeUtil.isBetween(element.getDateTime().toLocalTime(), startTime, endTime))
                resultList.add(new UserMealWithExceed(element.getDateTime(), element.getDescription(), element.getCalories(), myMap.get(element.getDateTime().toLocalDate()) > caloriesPerDay));
        }


        return resultList;
    }
}
