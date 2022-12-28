package nss.Helpers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
public class Show {
    /*******************************************************************************************************************
     * �������� ���� � ������������� ��� ������� �� ���� *********************************************************** *
     * *************************************************************************************************************** *
     * @param sAlertMessages ����������� � ���������/������������ *************************************************** *
     ******************************************************************************************************************/
    public static void showAlertBox(String sAlertMessages) {                        // TODO: CUSTOMIZE
        final Stage dialogStage = new Stage();
        GridPane grd_pan = new GridPane();
        grd_pan.setAlignment(Pos.CENTER);
        grd_pan.setHgap(5);
        grd_pan.setVgap(5);
        Scene scene = new Scene(grd_pan);
        dialogStage.setScene(scene);
        dialogStage.setTitle(Values.ERROR_WINDOW_TITLE);                            // ����� �� ���
        dialogStage.initModality(Modality.WINDOW_MODAL);

        ImageView image = new ImageView();
        image.setImage(new Image("img/img_alert.png"));                               // ����������
        image.setFitHeight(50);
        image.setFitWidth(50);
        Label lab_alert = new Label(sAlertMessages);                                // ����� �����������
        grd_pan.add(image, 0,0);
        grd_pan.add(lab_alert, 1, 0);

        Button btn_ok = new Button(Values.BUTTON_CLOSE);
        // ��������� ������ "³����"
        btn_ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                dialogStage.hide();
            }
        });
        grd_pan.add(btn_ok, 0, 2);
        dialogStage.show();
    }

    /*******************************************************************************************************************
     * ³��� ���� ���������� **************************************************************************************** *
     * *************************************************************************************************************** *
     * @param startTime   ������� ���������� ************************************************************************* *
     * @param currentTime �������� ���     ************************************************************************* *
     * @return ��� ����������                ************************************************************************* *
     ******************************************************************************************************************/
    public static String showWorkTime(Date startTime, Date currentTime) {

        long diff = currentTime.getTime() - startTime.getTime();                    // ������ �� �����
        long dSeconds = diff / 1000 % 60;                                           // �������
        long dMinutes = diff / (60 * 1000) % 60;                                    // �������
        long dHours = diff / (60 * 60 * 1000);                                      // ������

        String H = String.valueOf(dHours);
        String M = String.valueOf(dMinutes);
        String S = String.valueOf(dSeconds);

        // ���� ������ ����� �� ������� 10
        if (dHours <= 9)
            H = "0" + H;                                                            // ������ 0 ����� �������
        // ���� ������� ����� �� ������� 10
        if (dMinutes <= 9)
            M = "0" + M;                                                            // ������ 0 ����� ��������
        // ���� ������� ����� �� ������� 10
        if (dSeconds <= 9)
            S = "0" + S;                                                            // ������ 0 ����� ��������

        return H + ":" + M + ":" + S;                                               // ������� ��� ����������
    }


    /*******************************************************************************************************************
     * ���������� ���� �� ��������� ���������� ********************************************************************** *
     * *************************************************************************************************************** *
     * @param iImages ������� ���������� ���������        ********************************************************** *
     * @param iReverseCounter ������� ��������� ��������� ********************************************************** *
     * @param dStartSorting ��� ������� ����������          ********************************************************** *
     * @param dInProgressTime ��� ����������                ********************************************************** *
     * @return ��� �� ����                                 ********************************************************** *
     ******************************************************************************************************************/
    public static String showTimeToFinish(int iImages, int iReverseCounter, Date dStartSorting, Date dInProgressTime) {

        long time = dInProgressTime.getTime() - dStartSorting.getTime();            // ������ �� �����
        long diff = 1;
        // ���� ������ �� ����� ����� 0
        if (time > 0) {
            diff = (time/iImages)* iReverseCounter;                                 // ����������� ��� �� ����
        }
        long dSeconds = diff / 1000 % 60;                                           // �������
        long dMinutes = diff / (60 * 1000) % 60;                                    // �������
        long dHours = diff / (60 * 60 * 1000);                                      // ������

        String H = String.valueOf(dHours);
        String M = String.valueOf(dMinutes);
        String S = String.valueOf(dSeconds);

        // ���� ������ ����� �� ������� 10
        if (dHours <= 9)
            H = "0" + H;                                                            // ������ 0 ����� �������
        // ���� ������� ����� �� ������� 10
        if (dMinutes <= 9)
            M = "0" + M;                                                            // ������ 0 ����� ��������
        // ���� ������� ����� �� ������� 10
        if (dSeconds <= 9)
            S = "0" + S;                                                            // ������ 0 ����� ��������

        return H + ":" + M + ":" + S;                                               // ������� ��� �� ����
    }
}