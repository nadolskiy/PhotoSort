package nss.MetaData;

import com.drew.metadata.exif.ExifSubIFDDirectory;
import nss.Helpers.Values;

import java.util.Calendar;
import java.util.Date;

/***********************************************************************************************************************
 * 10000000101111111110111111111 * Author: Sergei Nadolskiy       **************************************************** *
 * 10100000101000000010100000001 * @mail:  s.nadolskiy@gmail.com  **************************************************** *
 * 10010000101000000000100000000 * skype:  s.nadolskiy            **************************************************** *
 * 10001000101111111110111111111 ************************************************************************************* *
 * 10000100100000000010000000001 ************************************************************************************* *
 * 10000010101000000010100000001 * Project Name: PSMini   ************************************************************ *
 * 10000000101111111110111111111 **************************************************************** �������� � ����� * *
 **********************************************************************************************************************/
public class DateInfo {
    private static final int[] iaImageCreatingDate = new int[]{0, 0, 0, 0, 0, 0};           // ����� � ��������� �����
    /*******************************************************************************************************************
     * �������� ���� ��������� ���������� ExifSubIFDDirectory ******************************************************* *
     * *************************************************************************************************************** *
     * @param exifSIFDD ExifSubIFDDirectory              ************************************************************* *
     * @return ����� � ������ ��� ���� ������ ���������� ************************************************************* *
     * ****************************************************************************************************************/
    public static int[] getImageCreatingDate(ExifSubIFDDirectory exifSIFDD) {
        // ���� ����-��������� � ������ ��� ���� �� �������
        if (exifSIFDD != null) {
            Date imageCreatingDate;                                                         // ���� ��������� �����
            // ���� � ���� ��������� �����
            if (exifSIFDD.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) != null)
                imageCreatingDate =
                        exifSIFDD.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);       // �������� ���� ������
                // ������ (���� � ���� ������ �� ����)
            else
                imageCreatingDate =
                        exifSIFDD.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);      // �������� ���� ������
            // ���� �������� ���� �� �������
            if (imageCreatingDate != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(imageCreatingDate);

                int[] creatingDate = new int[6];
                creatingDate[Values.IMAGE_CREATING_YEAR]  = calendar.get(Calendar.YEAR);          // �� ������
                creatingDate[Values.IMAGE_CREATING_MONTH] = calendar.get(Calendar.MONTH) + 1;     // ����� ������
                creatingDate[Values.IMAGE_CREATING_DAY]   = calendar.get(Calendar.DAY_OF_MONTH);  // ���� ������

                int imageHours = calendar.get(Calendar.HOUR);                                     // ������ ������
                // ���� ����� ��� ��������� � ����� �������� ���
                if (calendar.get(Calendar.AM_PM) == 1)
                    imageHours += 12;                                                             // ������ 12 �����

                creatingDate[Values.IMAGE_CREATING_HOURS]   = imageHours;                         // ������������� ���
                creatingDate[Values.IMAGE_CREATING_MINUTES] = calendar.get(Calendar.MINUTE);      // ������� ������
                creatingDate[Values.IMAGE_CREATING_SECONDS] = calendar.get(Calendar.SECOND);      // ������� ������

                // ���� ��� ������ ����� = 0
                if (creatingDate[Values.IMAGE_CREATING_HOURS]   == 0 &&
                    creatingDate[Values.IMAGE_CREATING_MINUTES] == 0 &&
                    creatingDate[Values.IMAGE_CREATING_SECONDS] == 0)
                    return iaImageCreatingDate;                                             // ������� ������� ����

                return creatingDate;                                                        // ������� ���� ������
                // ������ (���� ������� �������� ����)
            } else
                return iaImageCreatingDate;                                                 // ������� ������� ����
            // ������ (���� ����-��������� ������� (�������))
        } else
            return iaImageCreatingDate;                                                     // ������� ������� ����
    }
}