import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BuildingListComponent} from './building-list/building-list.component';
import {FloorListComponent} from './floor-list/floor-list.component';
import {RoomListComponent} from './room-list/room-list.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {CommonModule} from '@angular/common';


const appRoutes: Routes = [
  {path: 'buildings', component: BuildingListComponent},
  {path: 'floors', component: FloorListComponent},
  {path: 'rooms', component: RoomListComponent},
  {path: '', redirectTo: '/buildings', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent }
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    ),
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
