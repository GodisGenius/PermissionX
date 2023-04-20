package com.permissionx.god

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permissions:String,callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        //判断传入的Activity参数中是否包含了指定TAG的Fragment
        val fragment = if (existedFragment != null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            //将实例添加到Activity中，并指定一个TAG
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}