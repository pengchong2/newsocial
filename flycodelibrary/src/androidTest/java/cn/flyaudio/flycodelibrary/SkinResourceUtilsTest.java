package cn.flyaudio.flycodelibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import cn.flyaudio.flycodelibrary.utils.SkinResourceUtils;

import static org.junit.Assert.assertEquals;

/**
 * @author newtrekWang
 * @fileName SkinResourceUtilsTest
 * @createDate 2018/10/29 15:32
 * @email 408030208@qq.com
 * @desc 皮肤包工具类单元测试,可以在logcat中查看输出log
 */
@RunWith(AndroidJUnit4.class)
public class SkinResourceUtilsTest {
    /**
     * 专门用于存放皮肤包资源的应用包名
     */
    private static final String skinAppPackageName="cn.flyaudio.filedemo";
    /**
     * log标签
     */
    private static final String TAG = "SkinResourceUtilsTest";

    /**
     * 测试context
     * @throws Exception
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.d(TAG, "useAppContext: "+appContext.getPackageName());
    }

    private void init(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SkinResourceUtils.initSkinResourceUtil(appContext,skinAppPackageName);
    }

    /**
     * 测试初始化
     */
    @Test
    public void testInit(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        SkinResourceUtils.initSkinResourceUtil(appContext,skinAppPackageName);
        Context skinContext = SkinResourceUtils.getSkinContext();
        assertEquals(skinAppPackageName, skinContext.getPackageName());
    }
    /**
     * 测试获取各种资源id值,不为0就是获取成功
     */
    @Test
    public void testGetResourceId(){
        init();
        Log.d(TAG, "getLayoutId:"+SkinResourceUtils.getSkinLayoutIdByName("file_main"));
        Log.d(TAG, "getDrawableId: "+SkinResourceUtils.getSkinDrawableIdByName("paste_dialog"));
        Log.d(TAG, "getMipmapDrawableId: "+SkinResourceUtils.getSkinMipmapIdByName("filemanager_sd"));
        Log.d(TAG, "getMenuId: "+SkinResourceUtils.getSkinMenuIdByName("test"));
        Log.d(TAG, "getAnimId: "+SkinResourceUtils.getSkinAnimIdByName("in_lefttoright"));
        Log.d(TAG, "getAnimatorId: "+SkinResourceUtils.getSkinAnimatorIdByName("design_appbar_state_list_animator"));
        Log.d(TAG, "getViewId: "+SkinResourceUtils.getSkinViewIdByName("tv"));
        Log.d(TAG, "getStringId: "+SkinResourceUtils.getSkinStringIdByName("file_copy"));
        Log.d(TAG, "getColorId: "+SkinResourceUtils.getSkinColorIdByName("filemanager_mainFont"));
        Log.d(TAG, "getDimenId: "+SkinResourceUtils.getSkinDimenIdByName("filemanager_toptxt_dimen"));
        Log.d(TAG, "getIntegerId: "+SkinResourceUtils.getSkinIntegerIdByName("test_int"));
        Log.d(TAG, "getBoolId: "+SkinResourceUtils.getSkinBoolIdByName("test_bool"));
        Log.d(TAG, "getXmlId: "+SkinResourceUtils.getSkinXmlIdByName("test"));
        Log.d(TAG, "getStyleId: "+SkinResourceUtils.getSkinStyleIdByName("nightTheme"));
        Log.d(TAG, "getStringArrayId: "+SkinResourceUtils.getSkinStringArrayIdByName("test_string_list"));
        Log.d(TAG, "getIntegerArrayId: "+SkinResourceUtils.getSkinIntegerArrayIdByName("test_int_array"));
        Log.d(TAG, "getAttrId test_text,test_color :"+Arrays.toString(SkinResourceUtils.getSkinAttrsIdByName(new String[]{"test_text","test_color"})));
    }

    /**
     * 测试通过名字获取各种资源，不是null或者0就是获取成功
     */
    @Test
    public void testGetResourceByName(){
        init();
        Log.d(TAG, "getApplicationTheme : isNull"+(SkinResourceUtils.getSkinApplicationTheme()==null));
        Log.d(TAG, "getString: "+SkinResourceUtils.getSkinStringByName("file_copy"));
        Log.d(TAG, "getStringArray :"+Arrays.toString(SkinResourceUtils.getSkinStringArrayByName("test_string_list")));
        Log.d(TAG, "getInt : "+SkinResourceUtils.getSkinIntegerByName("test_int"));
        Log.d(TAG, "getIntArray : "+Arrays.toString(SkinResourceUtils.getSkinIntegerArrayByName("test_int_array")));
        Log.d(TAG, "getColor: "+SkinResourceUtils.getSkinColorByName("filemanager_mainFont"));
        Log.d(TAG, "getDimen: "+SkinResourceUtils.getSkinDimenByName("filemanager_toptxt_dimen"));
        Log.d(TAG, "getXmlParser isNull : "+(SkinResourceUtils.getSkinXmlResourceParserByName("test")==null));
        Log.d(TAG, "getAnimationParser isNull : "+(SkinResourceUtils.getSkinAnimationParserByName("design_appbar_state_list_animator")==null));
        Log.d(TAG, "getDrawable isNull :"+(SkinResourceUtils.getSkinDrawableByName("paste_dialog")==null));
        Log.d(TAG, "getMipmap isNull: "+(SkinResourceUtils.getSkinMipmapByName("filemanager_sd")==null));
        // 获取布局文件View
        View layoutView = SkinResourceUtils.getSkinLayoutViewByName("file_main");
        Log.d(TAG, "getLayoutView isNull: "+(layoutView == null));
        // 获取布局View里面的子View
        TextView tv = layoutView.findViewById(SkinResourceUtils.getSkinViewIdByName("file_tv_appname"));
        Log.d(TAG, "testView: tv's text: "+tv.getText().toString());
        Log.d(TAG, "getRawInputStream isNull: "+(SkinResourceUtils.getSkinRawInputStream("app_list") == null));
        // test Assets
        InputStream inputStream1 = null;
        try {
            inputStream1 = SkinResourceUtils.getSkinAssetsInputStream("android.jpg");
        } catch (IOException e) {
            Log.e(TAG, "getAssetsInputStream android.jpg  exception:"+e.toString());
        }
        Log.d(TAG, "getAssetsInputStream android.jpg isNull: "+(inputStream1 == null));

        InputStream inputStream2 = null;
        try {
            inputStream2 = SkinResourceUtils.getSkinAssetsInputStream("images/mvp.png");
        } catch (IOException e) {
            Log.e(TAG, "getAssetsInputStream images/mvp.png  exception:"+e.toString());
        }
        Log.d(TAG, "getAssetsInputStream images/mvp.png isNull: "+(inputStream2 == null));
        // 文件列表
        String[] list1 = null;
        try {
            list1 = SkinResourceUtils.getSkinAssetsFileNames("");
        } catch (IOException e) {
            Log.e(TAG, "getAssetsFileList  exception:"+e.toString());
        }
        Log.d(TAG, "getAssetsFileList assets目录下 : "+ Arrays.toString(list1));

        String[] list2 = null;
        try {
            list2 = SkinResourceUtils.getSkinAssetsFileNames("images");
        } catch (IOException e) {
            Log.e(TAG, "getAssetsFileList  exception:"+e.toString());
        }
        Log.d(TAG, "getAssetsFileList : assets/images目录下"+ Arrays.toString(list2));

        // getColorStateList
        ColorStateList colorStateList = SkinResourceUtils.getSkinColorStateListByName("test_color_selector");
        Log.d(TAG, "getColorStateList: "+colorStateList.toString());
        Log.d(TAG, "getIdAndContext :"+SkinResourceUtils.getIdentifierAndContext("test_text",SkinResourceUtils.RESOURCE_TYPE_ATTR));

    }






}
