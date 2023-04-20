package com.permissionx.god

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

typealias PermissionCallback = (Boolean,List<String>) -> Unit

class InvisibleFragment : Fragment(){

    //作为运行时权限申请结果的回调通知方式
    private var callback:PermissionCallback? = null

    //实现由外部调用自主指定要申请的权限功能
    fun requestNow(cb : PermissionCallback , vararg permission : String){
        callback = cb
        requestPermissions(permission,1)
    }

    //处理运行时权限申请的结果
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            //记录被用户拒绝的权限
            val deniedList =ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
                if(result != PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            //标识申请权限是否被授权
            val  allGranted = deniedList.isEmpty()
            //对权限申请的结果进行回调
            callback?.let { it(allGranted,deniedList) }
        }
    }
}