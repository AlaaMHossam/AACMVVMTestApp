package com.lifetimecode.aacmvvmtestapp.data.datasources.network

import java.io.IOException

class NoConnectivityException : IOException()
class InternalServerException : IOException()
class NotFoundException : IOException()