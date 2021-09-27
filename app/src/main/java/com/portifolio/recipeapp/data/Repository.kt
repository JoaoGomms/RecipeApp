package com.portifolio.recipeapp.data

import com.portifolio.recipeapp.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
){

    val remote = remoteDataSource

}