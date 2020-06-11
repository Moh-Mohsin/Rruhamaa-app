package net.ruhamaa.mobile.util

import net.ruhamaa.mobile.data.model.*
import net.ruhamaa.mobile.data.source.dto.*
import net.ruhamaa.mobile.data.source.remote.requests.DonationRequest

fun UserDto.toUser() = User(id, fullName, phoneNum, walletId)
fun User.toUserDto() = UserDto(id, fullName, phoneNum, walletId)

fun CaseDto.toCase() = Case(id, title, description, date, emergency, imgUrl, donorsCount,
    shareCount, targetDonations, currentDonations, otherImages?.map { it.toImage() })
fun Case.toCaseDto() = CaseDto(id, title, description, date, emergency, imgUrl, donorsCount,
    shareCount, targetDonations, currentDonations, otherImages?.map { it.toImageDto() })

fun ImageDto.toImage() = Image(id, url)
fun Image.toImageDto() = ImageDto(id, url)

fun DonationDto.toDonation(donor: User, case: Case?) = Donation(amount, info, donor, case, donationDate?: "")
fun DonationRequest.toDonateDto(donor: User) = DonateDto(amount, case.id, donor.id, donor.id)

fun TransactionDto.toTransaction() = Transaction(walletId, amount, operation, descrtption, fromDate, toDate, createdById, createdAt)

