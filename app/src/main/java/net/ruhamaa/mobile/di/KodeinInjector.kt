package net.ruhamaa.mobile.di

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import net.ruhamaa.mobile.RuhamaaApplication
import net.ruhamaa.mobile.data.repsitory.*
import net.ruhamaa.mobile.data.source.DonationDataSource
import net.ruhamaa.mobile.data.source.RuhamaDataSource
import net.ruhamaa.mobile.data.source.UserDataSource
import net.ruhamaa.mobile.data.source.WalletDataSource
import net.ruhamaa.mobile.data.source.fake.FakeDonationDataSource
import net.ruhamaa.mobile.data.source.fake.FakeWalletDataSource
import net.ruhamaa.mobile.data.source.local.LocalUserDataSource
import net.ruhamaa.mobile.data.source.remote.*
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaApi
import net.ruhamaa.mobile.data.source.remote.network.RuhamaaService
import net.ruhamaa.mobile.domain.*
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext

val KodeinInjector = Kodein {

    bind<CoroutineContext>() with provider { Dispatchers.Default }

    /**
     * UseCases
     */
    bind<LoginUseCase>() with provider { LoginUseCase(instance()) }
    bind<VerifyUseCase>() with provider { VerifyUseCase(instance()) }
    bind<GetCasesUseCase>() with provider { GetCasesUseCase(instance()) }
    bind<GetCaseUseCase>() with provider { GetCaseUseCase(instance()) }
    bind<GetBalanceUseCase>() with provider { GetBalanceUseCase(instance()) }
    bind<GetTransactionsUseCase>() with provider { GetTransactionsUseCase(instance()) }
    bind<DonateUseCase>() with provider { DonateUseCase(instance()) }
    bind<DepositUseCase>() with provider { DepositUseCase(instance()) }

    /**
     * Repositories
     */
    bind<RuhamaaRepository>() with singleton { RuhamaaRepositoryImpl(instance()) }
    bind<UserRepository>() with singleton { UserRepositoryImpl(instance("local"), instance("remote")) }
    bind<WalletRepository>() with singleton { WalletRepositoryImpl(instance()) }
    bind<DonationRepository>() with singleton { DonationRepositoryImpl(instance(), instance(), instance()) }

    /**
     * DataSources
     */
//    bind<NetworkChecker>() with provider { NetworkChecker() }
    bind<RuhamaDataSource>() with singleton { RemoteDataSource(instance()) }
    bind<UserDataSource>("local") with singleton { LocalUserDataSource(instance()) }
    bind<UserDataSource>("remote") with singleton { RemoteUserDataSource(instance()) }
    bind<SharedPreferences>() with singleton { RuhamaaApplication.sharedPreferences } // TODO: this is a hack, fix later
    bind<WalletDataSource>() with singleton { RemoteWalletDataSource(instance(), instance()) }
    bind<DonationDataSource>() with singleton { RemoteDonationDataSource(instance(), instance()) }


    /**
     * NETWORK API
     */
    bind<RuhamaaApi>() with singleton { RuhamaaService().getService() }

    /**
     * Network
     */

//    bind<Mapper<, >>()
}