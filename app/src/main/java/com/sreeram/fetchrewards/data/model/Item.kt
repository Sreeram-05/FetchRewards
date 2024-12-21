package com.sreeram.fetchrewards.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: Int? = null,
    val listId: Int? = null,
    val name: String? = null
) : Parcelable
