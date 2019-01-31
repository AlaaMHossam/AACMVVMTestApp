package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoriesModule {

    @Provides
    internal fun flightsRepository(
        webservice: Webservice,
        arrivalDao: ArrivalDao
    ):
            FlightsRepository = FlightsRepository(webservice, arrivalDao)
}