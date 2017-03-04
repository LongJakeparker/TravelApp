package com.greenacademy.travelapp.Activity.Constant;

import android.app.Activity;

/**
 * Created by Administrator on 10/01/2017.
 */

public class Constant extends Activity{
    public static String Check_First_Time = "check";
    public static String Status_Check_True = "True";
    public static String Status_Check_False = "False";
    public static int REQUEST_CODE_GOOGLE_SIGN_IN = 1564;
    public static boolean INTERNET_CONNECTION; //biến này thay đổi


//    Tag fragment màn hình chính
    public static final String TAG_FRAGMENT_DIADIEM = "Fragment_DiaDiem";
    public static final String TAG_FRAGMENT_QUANAN = "Fragment_QuanAn";
    public static final String TAG_FRAGMENT_HANHTRINH = "Fragment_HanhTrinh";
    public static final String TAG_FRAGMENT_BANTHAN= "Fragment_BanThan";

    public static final String URL_DANG_NHAP = "http://103.237.147.137:9045/TaiKhoan/DangNhap";
    public static final String DESCRIPTION_LOGIN = "OK";
    public static final int STATUS_LOGIN = 1;
    public static int ID_LOAIQUAN = 1;

    // TYPE GET DATA
    public static final String TYPE_DATA_KHU_VUC = "khuvuc";
    public static final String TYPE_DATA_DIA_DIEM = "diadiem";
    public static final String TYPE_DATA_DIA_DIEM_CHI_TIET = "chitiet_diadiem";

    //Constant Bundle key for Schedule
    public static final String KEY_ID_DIADIEM ="IdDiaDiem";
    public static final String KEY_ID_LOAIDIADIEM ="IdLoaiDiaDiem";
    public static final String KEY_NAME_LOCATION ="NameLocation";

    public static final String TITLE_DIALOG_WAITTING = "Waiting...";
    public static final String TITLE_DIALOG_WAITTING_PROCESS = "Đang xử lí...";

//    Constant cho setItemClickRecyclerView Quán ăn
    public static final int TATCAQUANAN_ADAPTER = 7859;
    public static final int LOAIQUANAN_ADAPTER = 9856;
    public static final int QUANGANTOI_ADAPTER = 8561;
    public static final int TOPCHECKIN_ADAPTER = 6231;
    public static final int FULLLIST_ADAPTER = 6020;
    public static final String FULLLIST_QUANAN = "FULLLIST";
    public static final String CHITIET_QUANAN = "CTQA";

    public static final int TYPE_LOGIN_NORMAL = 0;
    public static final int TYPE_LOGIN_FACEBOOK = 1;
    public static final int TYPE_LOGIN_GOOGLE = 2;
}
