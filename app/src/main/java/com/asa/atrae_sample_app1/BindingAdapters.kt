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

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UsersProperties>?) {
    if(data != null) {
        val adapter = recyclerView.adapter as MainAdapter
        adapter.notifyDataSetChanged()
    }else{
        Log.d("recyclerViewError","recyclerviewError")
    }
}

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
    textview.text = data?.first_name ?: "no user first name data"
}

@BindingAdapter("userLastName")
fun userLastNameDisplay(textview:TextView,data:UsersProperties?){
    textview.text = data?.last_name ?: "no user last name data"
}

@BindingAdapter("userEmail")
fun userEmailDisplay(textview:TextView,data:UsersProperties?){
    textview.text = data?.email ?: "no user email data"
}

@BindingAdapter("userImage")
fun userImageDisplay(imageView: ImageView,data:UsersProperties?){
    if(data != null) {
        imageView.let {
            val imgUri = data.avatar.toUri().buildUpon().scheme("https").build()
            Glide.with(it.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_connection_error)
                )
                .into(it)
        }
    }
}


