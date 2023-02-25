package hse.seminar10.mock.api;

import hse.seminar10.mock.dto.DataSearchRequest;
import java.util.List;
import java.util.function.Supplier;

public interface DataService {

  void saveData(List<String> dataToSave);

  String getDataById(String id);

  String getDataById(String id, Supplier<String> calculateIfAbsent);

  List<String> getData();

  List<String> getDataListByIds(List<String> idList);

  List<String> getDataByRequest(DataSearchRequest request);
}
