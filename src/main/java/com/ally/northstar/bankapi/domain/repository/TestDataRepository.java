package com.ally.northstar.bankapi.domain.repository;


import com.ally.northstar.bankapi.domain.model.Schedule;

import java.util.HashMap;
import java.util.List;

public class TestDataRepository {


    static HashMap<Long, Schedule> schedules = new HashMap<Long, Schedule>();

    public static void create(Schedule toAdd) {
        schedules.put(toAdd.getId(), toAdd);
    }

    public static Schedule find(Schedule toFind) {
        return schedules.get(toFind.getId());
    }

    public static List<Schedule> findAll() {
        return (List<Schedule>) schedules.values();
    }
}
