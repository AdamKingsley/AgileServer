package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.AddressEntity;
import cn.edu.nju.software.agile_server.entity.Area;
import cn.edu.nju.software.agile_server.entity.City;
import cn.edu.nju.software.agile_server.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    @Query(value = "select * from t_province", nativeQuery = true)
    List<Province> findProvinces();

    @Query(value = "select * from t_city where f_province_id=?1", nativeQuery = true)
    List<City> findCitiesByProvinceId(String province_id);

    @Query(value = "select * from t_area where f_city_id=?1", nativeQuery = true)
    List<Area> findAreasByCityId(String city_id);
}
