package pl.edu.kopalniakodu.pickide.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer_criteria")
@Getter
@Setter
public class AnswerCriteria implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    private double weight;

    private double consistencyRatio;

    public AnswerCriteria() {

    }

    public AnswerCriteria(Answer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerCriteria that = (AnswerCriteria) o;

        if (Double.compare(that.weight, weight) != 0) return false;
        if (Double.compare(that.consistencyRatio, consistencyRatio) != 0) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(consistencyRatio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
