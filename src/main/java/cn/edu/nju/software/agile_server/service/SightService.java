package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.PageResult;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.SightForm;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SightService {

    Result addSight(SightForm sightForm);

    Result addSightBatch(Collection<SightForm> sightForms);

    PageResult findSights(String cityId, List<String> orderColumns, int pageNum, int pageSize);

    Result sightDetail(Long sightId);

    PageResult findSights(String cityId, List<String> orderColumns, Integer pageNum, Integer pageSize, String name);

    Result findAllSightsByCityId(String cityId);

    Result findSightsWithConditions(String cityId, Map<String, String> filter_map);
}
