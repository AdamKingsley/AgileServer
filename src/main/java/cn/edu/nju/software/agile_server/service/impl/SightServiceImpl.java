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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
        //TOD 将sight对象转为sightSimpleVO对象
        return PageResult.success().message("获取分页景点数据成功!").withData(sightResults);
    }


    @Override
    public Result sightDetail(Long sightId) {
        Optional<Sight> sight_optional = sightDao.findById(sightId);
        Sight sight = sight_optional.orElse(new Sight());
        SightDetailVO sightDetailVO = new SightDetailVO();
        BeanUtils.copyProperties(sight, sightDetailVO, "pics", "labels");
        sightDetailVO.setPics(StringUtil.getList(sight.getPics(), ","));
        sightDetailVO.setLabels(StringUtil.getList(sight.getLabels(), ","));
        return Result.success().message("获取景点详情成功！").withData(sightDetailVO);
    }

    @Override
    public PageResult findSights(String cityId, List<String> orderColumns, Integer pageNum, Integer pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, orderColumns);
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        Page<Sight> sightResults = sightDao.findAllByCityAndNameLike(cityId, "%" + name + "%", pageRequest);
        //TOD 将sight对象转为sightSimpleVO对象
        return PageResult.success().message("获取分页景点数据成功!").withData(sightResults);
    }

    @Override
    public Result findAllSightsByCityId(String cityId) {
        List<Sight> sights = sightDao.findAllByCityId(cityId);
        List<SightSimpleVO> sightSimpleVOS = sights.stream().map(sight -> {
            SightSimpleVO vo = new SightSimpleVO();
            BeanUtils.copyProperties(sight, vo, "pics", "labels");
            vo.setPics(StringUtil.getList(sight.getPics(), ","));
            vo.setLabels(StringUtil.getList(sight.getLabels(), ","));
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取该地区全部景点成功！").withData(sightSimpleVOS);
    }

    @Override
    public Result findSightsWithConditions(String cityId, Map<String, String> filter_map) {
        //先写成固定格式，暂不重构
        //scoreOrder priceOrder minScore maxScore minPrice maxPrice
        Specification<Sight> specification = new Specification<Sight>() {
            @Override
            public Predicate toPredicate(Root<Sight> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(criteriaBuilder.equal(root.get("cityId"), cityId));
                if (null != filter_map.get("maxScore")) {
                    double max = Integer.parseInt(filter_map.get("maxScore"));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("score"), max));
                }
                if (null != filter_map.get("maxPrice")) {
                    double max = Integer.parseInt(filter_map.get("maxPrice"));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), max));
                }
                if (null != filter_map.get("minScore")) {
                    double min = Double.parseDouble(filter_map.get("minScore"));
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("score"), min));
                } else {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("score"), 0));
                }

                if (null != filter_map.get("minPrice")) {
                    double min = Double.parseDouble(filter_map.get("minPrice"));
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min));
                } else {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), 0));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        String scoreOrder = filter_map.get("scoreOrder");
        String priceOrder = filter_map.get("priceOrder");
        if (priceOrder != null) {
            sort = new Sort(Integer.parseInt(priceOrder) == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, "price").and(sort);
        }
        if (scoreOrder != null) {
            // 1 ASE 2DESC
            sort = new Sort(Integer.parseInt(scoreOrder) == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, "score").and(sort);
        }


        List<Sight> sights = sightDao.findAll(specification, sort);
        List<SightSimpleVO> sightSimpleVOS = sights.stream().map(sight -> {
            SightSimpleVO vo = new SightSimpleVO();
            BeanUtils.copyProperties(sight, vo, "pics", "labels");
            vo.setPics(StringUtil.getList(sight.getPics(), ","));
            vo.setLabels(StringUtil.getList(sight.getLabels(), ","));
            return vo;
        }).collect(Collectors.toList());
        return Result.success().message("获取筛选排序的景点信息成功！").withData(sightSimpleVOS);
    }
}
