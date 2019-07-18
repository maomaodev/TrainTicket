package com.example.trainticket_01.model.bean;

import java.util.List;

/**
 * Created by maomao on 2019/4/6.
 * <p>
 * 站点实体类
 */

public class StationBean
{
    private List<StationInfoEntity> station_info;

    public List<StationInfoEntity> getStation_info()
    {
        return station_info;
    }

    public static class StationInfoEntity
    {
        private String station;
        private String code;

        public String getStation()
        {
            return station;
        }
    }
}
