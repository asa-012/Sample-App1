package com.asa.atrae_sample_app1.network

data class RandomUsersDataPage(var data:List<UsersProperties>)

data class UsersProperties(var id:Int,var email:String,var first_name:String,val last_name:String,var avatar:String)