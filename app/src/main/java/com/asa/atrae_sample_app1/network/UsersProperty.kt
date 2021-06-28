package com.asa.atrae_sample_app1.network

//data class RandomUsersDataPage(var page:Int , var per_page:Int, var total:Int,var total_pages:Int,var data:UsersData)

data class RandomUsersDataPage(var data_list:List<UsersDataSample>)

//data class UsersData(var id:Int,var email:String,var first_name:String,var last_name:String,var avatar:String)

data class UsersDataSample(var id:Int,var title:String)