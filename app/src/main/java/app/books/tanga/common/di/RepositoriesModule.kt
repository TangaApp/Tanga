package app.books.tanga.common.di

import app.books.tanga.common.data.UserRepository
import app.books.tanga.common.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun UserRepositoryImpl.provideUserRepository(): UserRepository
}