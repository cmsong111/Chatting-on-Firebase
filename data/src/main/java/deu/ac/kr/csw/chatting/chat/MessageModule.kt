package deu.ac.kr.csw.chatting.chat

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MessageModule {

    @Singleton
    @Provides
    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImpl()
    }
}