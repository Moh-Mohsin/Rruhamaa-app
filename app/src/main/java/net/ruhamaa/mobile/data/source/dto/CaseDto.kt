package net.ruhamaa.mobile.data.source.dto

data class CaseDto(
        val id: String,
        val title: String,
        val description: String,
        val date: String?,
        val emergency: Boolean,
        val imgUrl: String,
        val donorsCount: Int,
        val shareCount: Int,
        val targetDonations: Double,
        val currentDonations: Double,
        val otherImages: List<ImageDto>?
)
data class ImageDto(
        val id: Int,
        val url: String
)