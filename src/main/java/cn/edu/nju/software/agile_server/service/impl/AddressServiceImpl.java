package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.dao.AddressRepository;
import cn.edu.nju.software.agile_server.service.AddressService;
import cn.edu.nju.software.agile_server.vo.AddressVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressRepository addressDao;

    @Override
    public Result listProvinces() {
        List<AddressVO> addresses = addressDao.findProvinces().stream().map(address -> {
            AddressVO vo = new AddressVO();
            vo.setId(address.getProvince_id());
            vo.setItemName(address.getProvince());
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取省份信息成功！").withData(addresses);
    }

    @Override
    public Result listCitiesByProvinceId(String provinceId) {
        List<AddressVO> addresses = addressDao.findCitiesByProvinceId(provinceId).stream().map(address -> {
            AddressVO vo = new AddressVO();
            vo.setId(address.getCity_id());
            vo.setItemName(address.getCity());
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取对应城市信息成功！").withData(addresses);
    }

    @Override
    public Result listAreasByCityId(String cityId) {
        List<AddressVO> addresses = addressDao.findAreasByCityId(cityId).stream().map(address -> {
            AddressVO vo = new AddressVO();
            vo.setId(address.getArea_id());
            vo.setItemName(address.getArea());
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取对应城市的区市信息成功！").withData(addresses);
    }
}
