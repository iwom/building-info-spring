import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { BuildingListComponent } from './building-list/building-list.component';
import { FloorListComponent } from './floor-list/floor-list.component';
import { RoomListComponent } from './room-list/room-list.component';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {BuildingService} from './building-list/building.service';
import {AppEndpoints} from './app.endpoints';
import {MaterialModule} from './material/material.module';
import { BuildingComponent } from './building-list/building/building.component';
import {RoomService} from './room-list/room.service';
import {FloorService} from './floor-list/floor.service';
import { FloorComponent } from './floor-list/floor/floor.component';
import { RoomComponent } from './room-list/room/room.component';
import { PostDialogComponent } from './post-dialog/post-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    BuildingListComponent,
    FloorListComponent,
    RoomListComponent,
    PageNotFoundComponent,
    BuildingComponent,
    FloorComponent,
    RoomComponent,
    PostDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule
  ],
  entryComponents: [PostDialogComponent],
  providers: [HttpClientModule, BuildingService, RoomService, FloorService, AppEndpoints],
  bootstrap: [AppComponent]
})
export class AppModule { }
