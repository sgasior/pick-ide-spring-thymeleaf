package pl.edu.kopalniakodu.pickide.domain.util;

import pl.edu.kopalniakodu.pickide.domain.Criteria;

import java.util.ArrayList;
import java.util.List;

public class RatingProvider {

    public List<Rating> getRatings() {
        return ratings;
    }

    private List<Rating> ratings = new ArrayList<>();


    public RatingProvider() {

        ratings.add(new Rating("BEG_CRITERIA1", "BEG_IDE1", 50.0));
        ratings.add(new Rating("BEG_CRITERIA1", "BEG_IDE2", 70.0));
        ratings.add(new Rating("BEG_CRITERIA1", "MID_IDE1", 30.0));
        ratings.add(new Rating("BEG_CRITERIA1", "MID_IDE2", 100.0));
        ratings.add(new Rating("BEG_CRITERIA1", "PRO_IDE1", 30.0));
        ratings.add(new Rating("BEG_CRITERIA1", "PRO_IDE2", 10.0));


        ratings.add(new Rating("BEG_CRITERIA2", "BEG_IDE1", 40.0));
        ratings.add(new Rating("BEG_CRITERIA2", "BEG_IDE2", 60.0));
        ratings.add(new Rating("BEG_CRITERIA2", "MID_IDE1", 20.0));
        ratings.add(new Rating("BEG_CRITERIA2", "MID_IDE2", 10.0));
        ratings.add(new Rating("BEG_CRITERIA2", "PRO_IDE1", 0.0));
        ratings.add(new Rating("BEG_CRITERIA2", "PRO_IDE2", 70.0));


        ratings.add(new Rating("MID_CRITERIA1", "BEG_IDE1", 20.0));
        ratings.add(new Rating("MID_CRITERIA1", "BEG_IDE2", 20.0));
        ratings.add(new Rating("MID_CRITERIA1", "MID_IDE1", 30.0));
        ratings.add(new Rating("MID_CRITERIA1", "MID_IDE2", 30.0));
        ratings.add(new Rating("MID_CRITERIA1", "PRO_IDE1", 60.0));
        ratings.add(new Rating("MID_CRITERIA1", "PRO_IDE2", 50.0));

        ratings.add(new Rating("MID_CRITERIA2", "BEG_IDE1", 50.0));
        ratings.add(new Rating("MID_CRITERIA2", "BEG_IDE2", 300.0));
        ratings.add(new Rating("MID_CRITERIA2", "MID_IDE1", 50.0));
        ratings.add(new Rating("MID_CRITERIA2", "MID_IDE2", 100.0));
        ratings.add(new Rating("MID_CRITERIA2", "PRO_IDE1", 60.0));
        ratings.add(new Rating("MID_CRITERIA2", "PRO_IDE2", 90.0));


        ratings.add(new Rating("PRO_CRITERIA1", "BEG_IDE1", 40.0));
        ratings.add(new Rating("PRO_CRITERIA1", "BEG_IDE2", 30.0));
        ratings.add(new Rating("PRO_CRITERIA1", "MID_IDE1", 40.0));
        ratings.add(new Rating("PRO_CRITERIA1", "MID_IDE2", 100.0));
        ratings.add(new Rating("PRO_CRITERIA1", "PRO_IDE1", 10.0));
        ratings.add(new Rating("PRO_CRITERIA1", "PRO_IDE2", 90.0));

        ratings.add(new Rating("PRO_CRITERIA2", "BEG_IDE1", 40.0));
        ratings.add(new Rating("PRO_CRITERIA2", "BEG_IDE2", 20.0));
        ratings.add(new Rating("PRO_CRITERIA2", "MID_IDE1", 50.0));
        ratings.add(new Rating("PRO_CRITERIA2", "MID_IDE2", 70.0));
        ratings.add(new Rating("PRO_CRITERIA2", "PRO_IDE1", 50.0));
        ratings.add(new Rating("PRO_CRITERIA2", "PRO_IDE2", 20.0));

    }


    public static List<Rating> findRatingByCriteria(Criteria criteria, List<Rating> ratings) {

        List<Rating> result = new ArrayList<>();

        for (Rating rating : ratings) {
            if (rating.getPreferedCriteria().equals(criteria.getCriteriaName())) {
                result.add(rating);
            }
        }
        return result;

    }

}
