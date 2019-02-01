package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.PageResult;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.SightForm;
import cn.edu.nju.software.agile_server.service.SightService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sight")
public class SightController {
    @Resource
    private SightService sightService;

    @PostMapping("/")
    public Result addSight(@RequestBody SightForm sightForm) {
        return sightService.addSight(sightForm);
    }

    @PostMapping("/batch")
    public Result addBatch(@RequestBody List<SightForm> sights) {
        return sightService.addSightBatch(sights);
    }

    @GetMapping("/{cityId}")
    public PageResult findSightsByCityId(@PathVariable("cityId") String cityId, @RequestParam("orderColumns") List<String> orderColumns,
                                         @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return sightService.findSights(cityId, orderColumns, pageNum, pageSize);
    }

    @GetMapping("/detail/{sightId}")
    public Result findSightDetail(@PathVariable("sightId") Long sightId) {
        return sightService.sightDetail(sightId);
    }


    @GetMapping("/match/{cityId}")
    public PageResult findSightsByCityId(@PathVariable("cityId") String cityId, @RequestParam("orderColumns") List<String> orderColumns,
                                         @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam("name") String name) {
        if (StringUtils.isEmpty(name)) {
            return sightService.findSights(cityId, orderColumns, pageNum, pageSize);
        } else {
            return sightService.findSights(cityId, orderColumns, pageNum, pageSize, name);
        }
    }

    @GetMapping("/all/{cityId}")
    public Result findSightsByCityId(@PathVariable("cityId") String cityId) {
        return sightService.findAllSightsByCityId(cityId);
    }
}
