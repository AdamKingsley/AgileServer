package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Sight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SightRepository extends PagingAndSortingRepository<Sight, Long> {
    //默认根据创建时间
    //Pageable的字段可以设置的字段自行进行排序
    Page<Sight> findAllByCity(String cityId, Pageable pageable);

    List<Sight> findAllByCityId(String cityId);

    List<Sight> findAllById(List<Long> ids);

    Page<Sight> findAllByCityAndNameLike(String cityId, String name, PageRequest pageRequest);
}
