package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * В классе Base реализована дополнительная логика для работы приложения
 */
public class Base {

    /**
     * В методе dateParser() осуществляется преобразование даты к нужному формату
     *
     * @param date дата, которую нужно преобразовать
     * @return дата, преобразованная к нкжному формату
     * @throws ParseException Сигнализирует, что при синтаксическом анализе произошла непредвиденная ошибка
     */
    public Date dateParser(String date) throws ParseException {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("d MMM yyyy", Locale.US);
        return formatForDateNow.parse(date);
    }

    /**
     * В методе getSystemDate() осуществляется получение системной даты
     *
     * @return системное время
     * @throws ParseException Сигнализирует, что при синтаксическом анализе произошла непредвиденная ошибка.
     */
    public Date getSystemDate() throws ParseException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("d MMM yyyy", Locale.US);
        String localDate = formatForDateNow.format(dateNow);
        return formatForDateNow.parse(localDate);
    }

    /**
     * В меторде getStartEventDate() осуществляется преобразование строки, с целью получения даты начала события
     *
     * @param eventDates диапазон дат
     * @return дата начала события
     */
    public String getStartEventDate(String eventDates) {
        return eventDates.split(" - ")[0] + " " + eventDates.substring(eventDates.lastIndexOf(" ") + 1);
    }

    /**
     * В меторде getEndEventDate() осуществляется преобразование строки, с целью получения даты окончания события
     *
     * @param eventDates диапазон дат
     * @return дату окончания события
     */
    public String getEndEventDate(String eventDates) {
        return eventDates.split(" - ")[1];
    }
}
