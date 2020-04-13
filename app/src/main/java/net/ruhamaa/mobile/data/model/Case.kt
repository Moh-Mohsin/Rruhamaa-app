package net.ruhamaa.mobile.data.model

data class Case(
        val id: String,
        val title: String,
        val description: String,
        val date: String,
        val emergency: Boolean,
        val imgUrl: String,
        val donorsCount: Int,
        val shareCount: Int,
        val targetDonations: Double,
        val currentDonations: Double
)