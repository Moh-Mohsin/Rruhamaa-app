package net.ruhamaa.mobile.di

import kotlinx.coroutines.Dispatchers
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepositoryImpl
import net.ruhamaa.mobile.data.source.RuhamaDataSource
import net.ruhamaa.mobile.data.source.remote.FakeRemoteDataSource
import net.ruhamaa.mobile.domain.GetCaseUseCase
import net.ruhamaa.mobile.domain.GetCasesUseCase
import net.ruhamaa.mobile.domain.LoginUseCase
import net.ruhamaa.mobile.domain.VerifyUseCase
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

    /**
     * Repositories
     */
    bind<RuhamaaRepository>() with singleton { RuhamaaRepositoryImpl(instance()) } //TODO use instance

    /**
     * DataSources
     */
//    bind<NetworkChecker>() with provider { NetworkChecker() }
    bind<RuhamaDataSource>() with singleton { FakeRemoteDataSource() } //TODO use instance

    /**
     * Network
     */

//    bind<Mapper<, >>()
}