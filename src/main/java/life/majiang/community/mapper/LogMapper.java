package life.majiang.community.mapper;

import life.majiang.community.model.Log;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select * from log where name = #{name}")
    Log findByName(@Param("name") String name);

    @Insert("insert into log(name, password) values(#{name}, #{password})")
    void create(Log log);

    @Select("select * from log")
    List<Log> list();

    @Update("update log set password = #{password} where name = #{name}")
    void update(Log log1);
}
