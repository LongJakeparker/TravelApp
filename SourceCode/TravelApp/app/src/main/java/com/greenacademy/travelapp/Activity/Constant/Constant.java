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

//    Constant cho các tag fragment màn hình chính
    public static final String TAG_FRAGMENT_DIADIEM = "Fragment_DiaDiem";
    public static final String TAG_FRAGMENT_QUANAN = "Fragment_QuanAn";
    public static final String TAG_FRAGMENT_HANHTRINH = "Fragment_HanhTrinh";
    public static final String TAG_FRAGMENT_BANTHAN= "Fragment_BanThan";

    public static final String URL_DANG_NHAP = "http://103.237.147.137:9045/TaiKhoan/DangNhap";
    public static final String DESCRIPTION_LOGIN = "OK";
    public static final int STATUS_LOGIN = 1;

    public static int ID_LOAIQUAN = 1;

//    Constant cho setItemClickRecyclerView Quán ăn
    public static final int TATCAQUANAN_ADAPTER = 7859;
    public static final int LOAIQUANAN_ADAPTER = 9856;
    public static final int QUANGANTOI_ADAPTER = 8561;
    public static final int TOPCHECKIN_ADAPTER = 6231;
    public static final int FULLLIST_ADAPTER = 6020;
    public static final String FULLLIST_QUANAN = "FULLLIST";
    public static final String CHITIET_QUANAN = "CTQA";

}
