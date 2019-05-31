/**
 * 
 */
package mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entity.GroubActivity;


@Repository
//@Mapper
public interface GroubActivityMapper {

	public List<GroubActivity> selectOneGroub(@Param(value = "refGroubTrace") String refGroubTrace,
			@Param(value = "refUserWxUnionid") String refUserWxUnionid);

	public GroubActivity selectOne(@Param(value = "groubaTrace") String groubaTrace);

	public int insert(GroubActivity groubActivity);

	public int update(GroubActivity groubActivity);
	
	public int share(@Param(value = "groubaTrace") String groubaTrace);

	public int delete(@Param(value = "refGroubTrace") String refGroubTrace,
			@Param(value = "refUserWxUnionid") String refUserWxUnionid);

	public List<GroubActivity> selectNearGrouba(@Param(value = "province") String province,
			@Param(value = "city") String city,@Param(value = "minLat") double minLat,
			@Param(value = "maxLat") double maxLat, @Param(value = "minLng") double minLng,
			@Param(value = "maxLng") double maxLng);
	
	public List<GroubActivity> selectNearGroubaTop100(@Param(value = "province") String province,
			@Param(value = "city") String city);

}
