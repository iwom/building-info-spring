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


const appRoutes: Routes = [
  {path: 'buildings', component: BuildingListComponent},
  {path: 'floors', component: FloorListComponent},
  {path: 'rooms', component: RoomListComponent},
  {path: '', redirectTo: '/buildings', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    BuildingListComponent,
    FloorListComponent,
    RoomListComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [HttpClientModule, BuildingService, AppEndpoints],
  bootstrap: [AppComponent]
})
export class AppModule { }
