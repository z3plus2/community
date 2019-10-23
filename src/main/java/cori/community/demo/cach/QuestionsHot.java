package cori.community.demo.cach;

import cori.community.demo.model.Question;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 3plus2
 * @data 2019/10/23 17 47
 * @desercription
 */
@Component
@Data
public class QuestionsHot {
    private int max = 3;
    private PriorityQueue<Question> questionsHot = new PriorityQueue<>(max, cmp);

    public void update(List<Question> questions) {



        if (questions.size() > 0) {

                for (int i = 0; i < questions.size(); i++) {
                    Question question = questions.get(i);
                    if (questionsHot.size()<max){
                        questionsHot.add(question);
                         continue;
                    }
                    if (cmp.compare(question, questionsHot.peek()) > 0) {
                        questionsHot.poll();
                        questionsHot.add(question);
                    }

                }

        }
    }

    static Comparator<Question> cmp = new Comparator<Question>() {
        @Override
        public int compare(Question o2, Question o1) {
            return 3 * o2.getCommentCount() + o2.getViewCount() - 3 * o1.getCommentCount() - o1.getViewCount();
        }
    };

}
