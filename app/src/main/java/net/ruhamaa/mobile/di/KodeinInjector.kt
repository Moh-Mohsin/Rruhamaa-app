package net.ruhamaa.mobile.di

import kotlinx.coroutines.Dispatchers
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepositoryImpl
import net.ruhamaa.mobile.data.source.RuhamaDataSource
import net.ruhamaa.mobile.data.source.remote.FakeRemoteDataSource
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
    bind<LoginUseCase>() with singleton { LoginUseCase(instance()) }
    bind<VerifyUseCase>() with singleton { VerifyUseCase(instance()) }

    /**
     * Repositories
     */
    bind<RuhamaaRepository>() with provider { RuhamaaRepositoryImpl(instance()) } //TODO use instance

    /**
     * DataSources
     */
//    bind<NetworkChecker>() with provider { NetworkChecker() }
    bind<RuhamaDataSource>() with provider { FakeRemoteDataSource() } //TODO use instance

    /**
     * Network
     */

//    bind<Mapper<, >>()
}