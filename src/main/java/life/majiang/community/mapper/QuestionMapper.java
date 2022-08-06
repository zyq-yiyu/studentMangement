package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag,qqnum) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{qqnum})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id")Integer id);

    @Update("update question set title = #{title}, description = #{description},gmt_modified = #{gmtModified}, tag = #{tag}, qqnum = #{qqnum} where id = #{id}")
    void update(Question question);

    @Delete("delete from question where id=#{id}")
    void delete(Integer id);

    @Select("select * from question where description = #{search}")
    Question getBySearch(String search);

    @Select("select * from question where description = #{description}")
    Question findByDescription(String description);

    @Select("select * from question where id = #{id}")
    Question findById(Integer id);
}
