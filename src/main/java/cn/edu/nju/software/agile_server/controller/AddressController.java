package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/address")
@Api("AddressController")
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping("/provinces")
    @ApiOperation(value = "/provinces", notes = "获取省份列表")
    public Result findProvinces() {
        return addressService.listProvinces();
    }

    @GetMapping("/cities")
    @ApiOperation(value = "/cities", notes = "获取对应省份的城市列表")
    @ApiImplicitParam(paramType = "query", name = "province_id", value = "省份编号", required = true, dataType = "String")
    public Result findCitiesByProvinceId(@RequestParam("province_id") String provinceId) {
        return addressService.listCitiesByProvinceId(provinceId);
    }

    @GetMapping("/areas")
    @ApiOperation(value = "/areas", notes = "获取对应城市的区列表")
    @ApiImplicitParam(paramType = "query", name = "city_id", value = "城市编号", required = true, dataType = "String")
    public Result findAreasByCityId(@RequestParam("city_id") String cityId) {
        return addressService.listAreasByCityId(cityId);
    }
}
