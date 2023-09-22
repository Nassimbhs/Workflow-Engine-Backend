package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.example.workflow.entity.JsonData;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.repository.JsonDataRepository;
import workflow.example.workflow.repository.TacheAtraiteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class JsonDataService {

    private final JsonDataRepository jsonDataRepository;
    private final TacheAtraiteRepository tacheAtraiterRepository;

    @Autowired
    public JsonDataService(JsonDataRepository jsonDataRepository, TacheAtraiteRepository tacheAtraiterRepository) {
        this.jsonDataRepository = jsonDataRepository;
        this.tacheAtraiterRepository = tacheAtraiterRepository;
    }

    @Transactional
    public void addJsonDataAndAssociateTaches(Long responsable, String jsonData) {
        JsonData newJsonData = new JsonData();
        newJsonData.setData(jsonData);
        newJsonData.setResponsable(responsable);

        JsonData savedJsonData = jsonDataRepository.save(newJsonData);

        Iterable<TacheAtraiter> tachesAtraiter = tacheAtraiterRepository.findAll();
        for (TacheAtraiter tacheAtraiter : tachesAtraiter) {
            tacheAtraiter.getJsonDatas().add(savedJsonData); // Associate with the saved JsonData
        }
    }

    public List<JsonData> getJsonDataByTacheAtraiterId(Long tacheAtraiterId) {
        return jsonDataRepository.findByTacheAtraiterId(tacheAtraiterId);
    }

    @Transactional
    public JsonData updateStateToTreated(Long id) {
        JsonData jsonData = jsonDataRepository.findById(id).orElse(null);
        if (jsonData != null) {
            jsonData.setEtat("traité");
            return jsonDataRepository.save(jsonData);
        }
        return null;
    }

    public JsonData findById(Long id) {
        return jsonDataRepository.findById(id).orElse(null);
    }

}
