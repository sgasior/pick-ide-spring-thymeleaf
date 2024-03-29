package pl.edu.kopalniakodu.pickide.service.ServiceImpl;

import org.springframework.stereotype.Service;
import pl.edu.kopalniakodu.pickide.domain.*;
import pl.edu.kopalniakodu.pickide.domain.util.PreferedCriteria;
import pl.edu.kopalniakodu.pickide.domain.util.PreferedIDE;
import pl.edu.kopalniakodu.pickide.repository.*;
import pl.edu.kopalniakodu.pickide.service.ServiceInterface.SurveyService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;
    private CriteriaRepository criteriaRepository;
    private AlternativeRepository alternativeRepository;
    private AnswerRepository answerRepository;
    private AnswerAlternativeRepository answerAlternativeRepository;
    private AnswerCriteriaRepository answerCriteriaRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository, CriteriaRepository criteriaRepository, AlternativeRepository alternativeRepository, AnswerRepository answerRepository, AnswerAlternativeRepository answerAlternativeRepository, AnswerCriteriaRepository answerCriteriaRepository) {
        this.surveyRepository = surveyRepository;
        this.criteriaRepository = criteriaRepository;
        this.alternativeRepository = alternativeRepository;
        this.answerRepository = answerRepository;
        this.answerAlternativeRepository = answerAlternativeRepository;
        this.answerCriteriaRepository = answerCriteriaRepository;
    }

    @Override
    public Survey save(Survey survey) {
        surveyRepository.save(survey);
        return survey;
    }

    @Override
    public void addCriterias(String[] selectedCriterias, Survey survey) {
        for (String criteriaName : selectedCriterias) {
            survey.addCriteria(new Criteria(criteriaName));
        }
    }

    @Override
    public void addAlternatives(String[] selectedAlternative, Survey survey) {
        for (String alternativeName : selectedAlternative) {
            survey.addAlternative(new Alternative(alternativeName));
        }
    }

    @Override
    public boolean isNewCriteriaOrAlternativeAdded(Survey survey) {

        List<String> allPredefiniedCriteriaAndAlternative = new ArrayList<>();
        boolean result = false;

        allPredefiniedCriteriaAndAlternative.addAll(PreferedCriteria.BEGINNER.getReponse());
        allPredefiniedCriteriaAndAlternative.addAll(PreferedCriteria.MID_EXP.getReponse());
        allPredefiniedCriteriaAndAlternative.addAll(PreferedCriteria.PRO_EXP.getReponse());

        allPredefiniedCriteriaAndAlternative.addAll(PreferedIDE.BEGINNER.getReponse());
        allPredefiniedCriteriaAndAlternative.addAll(PreferedIDE.MID_EXP.getReponse());
        allPredefiniedCriteriaAndAlternative.addAll(PreferedIDE.PRO_EXP.getReponse());

        List<Criteria> criteriaList = survey.getCriterias();
        for (Criteria criteria : criteriaList) {
            if (allPredefiniedCriteriaAndAlternative.contains(criteria.getCriteriaName())) {

            } else {
                return true;
            }
        }

        List<Alternative> alternativeList = survey.getAlternatives();
        for (Alternative alternative : alternativeList) {
            if (allPredefiniedCriteriaAndAlternative.contains(alternative.getAlternativeName())) {

            } else {
                return true;
            }
        }
        return result;
    }

    @Override
    public String generateRandomURIParam() {
        int minASCI = 97;
        int maxASCINumber = 122;
        int targetStringLength = 10;


        StringBuilder stringBuilder = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            stringBuilder.append((char) randomInRange(minASCI, maxASCINumber));
        }
        return stringBuilder.toString();
    }


    /**
     * @param min include
     * @param max exclude
     * @return random number in range min and max
     */
    private static int randomInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


    @Override
    public Optional<Survey> findSurveyBySurveyURIParam(String surveyURIParam) {
        return surveyRepository.findSurveyBySurveyURIParam(surveyURIParam);
    }

    @Override
    public Survey findById(Long surveyID) {
        return surveyRepository.findById(surveyID).get();
    }

    @Override
    public String generateSurveyURL(String surveyURI, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.substring(0, url.lastIndexOf("/")) + "/" + surveyURI;

    }

    @Override
    public Optional<Criteria> findCriteriaById(Long criteriaID) {
        return criteriaRepository.findById(criteriaID);
    }

    @Override
    public List<Survey> findAllSharedSurveys(User user) {
        return surveyRepository.findAllByUserAndSurveyURIParamNotNull(user);
    }

    @Override
    public void deleteSurvey(Survey survey) {

        survey.setUser(null);

        for (Answer answer : survey.getAnswers()) {
            answerAlternativeRepository.deleteByAnswer(answer);
            answerCriteriaRepository.deleteByAnswer(answer);
        }

        criteriaRepository.deleteCriteriaBySurvey(survey);
        alternativeRepository.deleteAlternativeBySurvey(survey);

        answerRepository.deleteAnswerBySurvey(survey);
        surveyRepository.deleteById(survey.getId());
    }


}
