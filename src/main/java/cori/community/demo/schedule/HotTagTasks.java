package cori.community.demo.schedule;

import cori.community.demo.cach.QuestionsHot;
import cori.community.demo.model.Question;
import cori.community.demo.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 3plus2
 * @data 2019/10/23 15 58
 * @desercription
 */
@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionsHot questionsHot;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        log.info("The time is now {}", dateFormat.format(new Date()));
        questionsHot.getQuestionsHot().clear();
        Integer offset = 0;
        Integer size = 20;
        Integer max ;
        max = questionService.countAllQustion();
        while (offset < max) {
            List<Question> list = questionService.hotList(offset, size);
            questionsHot.update(list);
            offset+=size;

        }
    }
}
