package net.ruhamaa.mobile.util

import net.ruhamaa.mobile.data.model.Case
import net.ruhamaa.mobile.data.model.Donation
import net.ruhamaa.mobile.data.model.Image
import net.ruhamaa.mobile.data.model.User
import net.ruhamaa.mobile.data.source.dto.CaseDto
import net.ruhamaa.mobile.data.source.dto.DonationDto
import net.ruhamaa.mobile.data.source.dto.ImageDto
import net.ruhamaa.mobile.data.source.dto.UserDto

fun UserDto.toUser() = User(phoneNum)
fun User.toUserDto() = UserDto(phoneNum)

fun CaseDto.toCase() = Case(id, title, description, date, emergency, imgUrl, donorsCount,
    shareCount, targetDonations, currentDonations, otherImages?.map { it.toImage() })
fun Case.toCaseDto() = CaseDto(id, title, description, date, emergency, imgUrl, donorsCount,
    shareCount, targetDonations, currentDonations, otherImages?.map { it.toImageDto() })

fun ImageDto.toImage() = Image(id, url)
fun Image.toImageDto() = ImageDto(id, url)

fun DonationDto.toDonation() = Donation(amount, info, donorDto.toUser(), caseDto.toCase(), date)
fun Donation.toDonationDto() = DonationDto(amount, info, donor.toUserDto(), case.toCaseDto(), date)

