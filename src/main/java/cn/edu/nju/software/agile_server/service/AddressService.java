package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;

public interface AddressService {
    public Result listProvinces();

    public Result listCitiesByProvinceId(String provinceId);

    public Result listAreasByCityId(String cityId);
}
