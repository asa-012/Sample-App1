/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.asa.atrae_sample_app1

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asa.atrae_sample_app1.main.MainAdapter
import com.asa.atrae_sample_app1.main.UsersApiStatus
import com.asa.atrae_sample_app1.network.RandomUsersDataPage
import com.asa.atrae_sample_app1.network.UsersProperties
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UsersProperties>?) {
    if(data != null) {
        val adapter = recyclerView.adapter as MainAdapter
        adapter.notifyDataSetChanged()
    }else{
        Log.d("recyclerViewError","recyclerviewError")
    }
}

///**
// * Uses the Glide library to load an image by URL into an [ImageView]
// */
//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        Glide.with(imgView.context)
//                .load(imgUri)
//                .apply(
//                    RequestOptions()
//                        .placeholder(R.drawable.loading_animation)
//                        .error(R.drawable.ic_connection_error))
//                .into(imgView)
//    }
//}

/**
 * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("usersApiStatus")
fun bindStatus(statusImageView: ImageView, status: UsersApiStatus?) {
    when (status) {
        UsersApiStatus.LOADING -> {
            statusImageView.visibility = View.GONE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        UsersApiStatus.ERROR -> {
            statusImageView.visibility = View.GONE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        UsersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("userFirstName")
fun userFirstNameDisplay(textview:TextView,data:UsersProperties?){
    if(data == null) {
        textview.text = "no user first name data"
    }else{
        textview.text = data.first_name
    }
}

@BindingAdapter("userLastName")
fun userLastNameDisplay(textview:TextView,data:UsersProperties?){
    textview.visibility = View.VISIBLE
    if(data == null){
        textview.text = "no user last name data"
    }else {
        textview.text = data.last_name
    }
}

@BindingAdapter("userEmail")
fun userEmailDisplay(textview:TextView,data:UsersProperties?){
    if(data == null) {
        textview.text = "no email data"
    }else{
        textview.text = data.email
    }
}

@BindingAdapter("userImage")
fun userImageDisplay(imageView: ImageView,data:UsersProperties?){
    if(data != null) {
        imageView.let {
            val imgUri = data.avatar.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_connection_error)
                )
                .into(imageView)
        }
    }
}


