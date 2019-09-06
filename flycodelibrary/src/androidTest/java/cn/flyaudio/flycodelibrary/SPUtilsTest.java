package cn.flyaudio.flycodelibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import cn.flyaudio.flycodelibrary.utils.AppUtils;
import cn.flyaudio.flycodelibrary.utils.SPUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author newtrekWang
 * @fileName SPUtilsTest
 * @createDate 2018/11/7 9:34
 * @email 408030208@qq.com
 * @desc TODO
 */
@RunWith(AndroidJUnit4.class)
public class SPUtilsTest {

    private static final String testString = "string";
    private static final int testInt = 300;
    private static final long testLong = 200L;
    private static final float testFloat = 3.0f;
    private static final boolean testBool = true;

    @Test
    public void testSp() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        AppUtils.init(appContext);

        SPUtils.getInstance().put("string",testString);
        SPUtils.getInstance().put("long",testLong);
        SPUtils.getInstance().put("int",testInt);
        SPUtils.getInstance().put("float",testFloat);
        SPUtils.getInstance().put("boolean",testBool);

        Set<String> strings = new HashSet<>();
        strings.add("test1");
        strings.add("test2");
        strings.add("test3");
        SPUtils.getInstance().put("stringSet",strings);
        assertEquals(testString,SPUtils.getInstance().getString("string"));
        assertTrue(testLong == SPUtils.getInstance().getLong("long"));
        assertTrue(testInt == SPUtils.getInstance().getInt("int"));
        assertTrue(testFloat == SPUtils.getInstance().getFloat("float"));
        assertEquals(testBool,SPUtils.getInstance().getBoolean("boolean"));



    }
}
