package services;

/**
 * В классе TestData осуществдляется генерация тестовых данных
 */
public class TestData {

    /**
     * В методе getTestData() осуществляется генерация уникальных значений
     * по средствам добавления системного времени к наименованию входного параметра
     * @param param наименование параметра для которого будет сгенерировано уникальное значение
     * @return уникальноге значение параметра
     */
    public String getTestData(String param){
        return param + System.currentTimeMillis();
    }
}
