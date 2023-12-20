package deu.ac.kr.csw.chatting.chat

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DialogModule {

    @Singleton
    @Provides
    fun provideDialogRepository(): DialogRepository {
        return DialogRepositoryImpl()
    }
}