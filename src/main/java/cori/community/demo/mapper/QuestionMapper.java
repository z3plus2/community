package cori.community.demo.mapper;

import cori.community.demo.dto.QuestionDTO;
import cori.community.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 3plus2
 * @data 2019/10/3 15 41
 * @desercription
 */
@Mapper
public interface QuestionMapper {
    @Select("select * from  question")
     List<Question> list() ;


    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

}
