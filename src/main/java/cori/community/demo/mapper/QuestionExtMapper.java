package cori.community.demo.mapper;

import cori.community.demo.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 3plus2
 * @data 2019/10/16 19 57
 * @desercription
 */
@Mapper
public interface QuestionExtMapper {


    @Update("update question set view_count=view_count+1 where id=#{id} ")
    void addViewCount(@Param("id") Integer id);

    @Update("update question set comment_count=comment_count+1 where id=#{id} ")
    void addQuestionComment(@Param("id")Long id);
}
