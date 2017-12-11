package com.zhongke.weiduo.library.easyPermission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by ${xinGen} on 2017/11/29.
 * <p>
 * EasyPermission库使用封装
 * GitHub上项目介绍: https://github.com/googlesamples/easypermissions
 */

public class PermissionManager {
    /**
     * 打开设置程序权限的界面，请求code
     */
    public static final int DEFAULT_SETTINGS_REQ_CODE = 1314;
    /**
     * 开启程序权限的弹窗提示语
     */
    private static final String TITLE = "温馨提示";
    private static final String CONTENT = "需要授予相关权限，才能正常使用功能，请您点击确定按钮，设置权限";
    private static final String NAVIGATION_BUTTON_CONTENT = "确定";

    /**
     * 检查权限
     *
     * @param context return true:已经获取权限
     *                return false: 未获取权限，主动请求权限
     */
    public static boolean checkPermission(Context context, String[] perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    public static boolean checkPermission(Fragment fragment, String[] perms) {
        return checkPermission(fragment.getActivity(), perms);
    }

    /**
     * Activity中使用
     * 请求权限
     *
     * @param context
     */
    public static void requestPermission(Activity context, String tip, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(context, tip, requestCode, perms);
    }
    /**
     * 请求权限
     *
     * @param fragment
     */
    public static void requestPermission(Fragment fragment, String tip, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(fragment, tip, requestCode, perms);
    }
    /**
     * 权限请求结果传递EasyPermission库处理
     * 具体判断逻辑会在EasyPermission内部完成，
     * 成功或者拒绝会在PermissionCallbacks 接口中回调。
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param receivers
     */
    public static void handlerPermissionResult(int requestCode, String[] permissions, int[] grantResults, EasyPermissions.PermissionCallbacks receivers) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, receivers);
    }
    /**
     * 当权限弹窗中，用户勾选不再提示，且拒绝的时候，这时候需要针对特殊处理。
     * <p>
     * 弹窗设置弹窗，转跳app设置界面，让用户手动开启权限。
     *
     * @param activity
     * @param permissions
     */
    public static void handlerPermissionRefuseNotTip(Activity activity, List<String> permissions) {
        handlerPermissionRefuse(activity, permissions);
    }
    public static void handlerPermissionRefuseNotTip(Fragment fragment, List<String> permissions) {
        handlerPermissionRefuse(fragment, permissions);
    }
    private static <T> void handlerPermissionRefuse(T t, List<String> permissions) {
        if (t instanceof Activity) {
            if (EasyPermissions.somePermissionPermanentlyDenied((Activity) t, permissions)) {
                AppSettingsDialog appSettingsDialog = createAppSettingDialog(t, TITLE, CONTENT, NAVIGATION_BUTTON_CONTENT, DEFAULT_SETTINGS_REQ_CODE);
                appSettingsDialog.show();
            }
        } else if (t instanceof Fragment) {
            if (EasyPermissions.somePermissionPermanentlyDenied((Fragment) t, permissions)) {
                AppSettingsDialog appSettingsDialog = createAppSettingDialog(t, TITLE, CONTENT, NAVIGATION_BUTTON_CONTENT, DEFAULT_SETTINGS_REQ_CODE);
                appSettingsDialog.show();
            }
        }
    }

    /**
     * 构建一个开启程序权限设置界面的Dialog
     *
     * @param t
     * @param title
     * @param content
     * @param negativeButtonContent
     * @return
     */
    private static <T> AppSettingsDialog createAppSettingDialog(T t, String title, String content, String negativeButtonContent, int requestCode) {
        AppSettingsDialog.Builder builder;
        if (t instanceof Activity) {
            builder = new AppSettingsDialog.Builder((Activity) t);
        } else {
            builder = new AppSettingsDialog.Builder((Fragment) t);
        }
        builder.setTitle(title);
        builder.setRationale(content);
        builder.setNegativeButton(negativeButtonContent);
        builder.setRequestCode(requestCode);
        return builder.build();
    }
}
