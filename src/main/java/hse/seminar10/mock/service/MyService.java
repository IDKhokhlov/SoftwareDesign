package hse.seminar10.mock.service;

import hse.seminar10.mock.api.DataService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyService {

  private final DataService dataService;

  public List<String> findDataList(String id, List<String> idList) {
    String dataById = dataService.getDataById(id);
    List<String> resultList;

    if ("list".equals(dataById)) {
      resultList = dataService.getDataListByIds(idList);
    } else {
      resultList = dataService.getData();
    }

    return resultList;
  }

  public void saveData(List<String> dataToSave) {
    dataService.saveData(dataToSave);
  }
}
