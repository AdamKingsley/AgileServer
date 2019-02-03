package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.PageResult;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.dao.SightRepository;
import cn.edu.nju.software.agile_server.entity.Sight;
import cn.edu.nju.software.agile_server.form.SightForm;
import cn.edu.nju.software.agile_server.service.SightService;
import cn.edu.nju.software.agile_server.util.ClassUtil;
import cn.edu.nju.software.agile_server.util.StringUtil;
import cn.edu.nju.software.agile_server.vo.SightDetailVO;
import cn.edu.nju.software.agile_server.vo.SightSimpleVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SightServiceImpl implements SightService {
    @Resource
    private SightRepository sightDao;


    @Override
    public Result addSight(SightForm sightForm) {
        Sight sight = new Sight();
        BeanUtils.copyProperties(sightForm, sight, ClassUtil.getNullPropertyNames(sightForm));
        sightDao.save(sight);
        return Result.success().message("新增景点信息成功！");
    }

    @Override
    public Result addSightBatch(Collection<SightForm> sightForms) {
        List<Sight> sightList = sightForms.stream().map(sightForm -> {
            Sight sight = new Sight();
            BeanUtils.copyProperties(sightForm, sight, ClassUtil.getNullPropertyNames(sightForm));
            return sight;
        }).collect(Collectors.toList());
        sightDao.saveAll(sightList);
        return Result.success().message("批量新增景点信息成功");
    }

    @Override
    public PageResult findSights(String cityId, List<String> orderColumns, int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, orderColumns);
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        Page<Sight> sightResults = sightDao.findAllByCity(cityId, pageRequest);
        //TODO 将sight对象转为sightSimpleVO对象
        return PageResult.success().message("获取分页景点数据成功!").withData("");
    }


    @Override
    public Result sightDetail(Long sightId) {
        Optional<Sight> sight_optional = sightDao.findById(sightId);
        Sight sight = sight_optional.orElse(null);
        SightDetailVO sightDetailVO = new SightDetailVO();
        BeanUtils.copyProperties(sight, sightDetailVO, "pics", "labels");
        sightDetailVO.setPics(StringUtil.getList(sight.getPics(),","));
        sightDetailVO.setLabels(StringUtil.getList(sight.getLabels(),","));
        return Result.success().message("获取景点详情成功！").withData(sightDetailVO);
    }

    @Override
    public PageResult findSights(String cityId, List<String> orderColumns, Integer pageNum, Integer pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, orderColumns);
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        Page<Sight> sightResults = sightDao.findAllByCityAndNameLike(cityId, "%" + name + "%", pageRequest);
        //TODO 将sight对象转为sightSimpleVO对象
        return PageResult.success().message("获取分页景点数据成功!").withData("");
    }

    @Override
    public Result findAllSightsByCityId(String cityId) {
        List<Sight> sights = sightDao.findAllByCityId(cityId);
        List<SightSimpleVO> sightSimpleVOS = sights.stream().map(sight -> {
            SightSimpleVO vo = new SightSimpleVO();
            BeanUtils.copyProperties(sight, vo, "pics", "labels");
            vo.setPics(StringUtil.getList(sight.getPics(),","));
            vo.setLabels(StringUtil.getList(sight.getLabels(),","));
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取该地区全部景点成功！").withData(sightSimpleVOS);
    }
}
