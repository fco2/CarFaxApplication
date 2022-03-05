package com.app.carfaxapplication.data.remote.dto

data class MonthlyPaymentEstimateDto(
    val downPaymentAmount: Double,
    val downPaymentPercent: Double,
    val interestRate: Double,
    val loanAmount: Double,
    val monthlyPayment: Double,
    val price: Double,
    val termInMonths: Int
)