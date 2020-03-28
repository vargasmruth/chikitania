import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { EventController } from './event/event.controller';
import { ProductEventController } from './product-event/product-event.controller';

@Module({
  imports: [],
  controllers: [AppController, EventController, ProductEventController],
  providers: [AppService],
})
export class AppModule {}
