import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BuildingListComponent} from './building-list/building-list.component';
import {FloorListComponent} from './floor-list/floor-list.component';
import {RoomListComponent} from './room-list/room-list.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {CommonModule} from '@angular/common';
import {BuildingComponent} from './building-list/building/building.component';
import {FloorComponent} from './floor-list/floor/floor.component';


const appRoutes: Routes = [
  {path: 'buildings', component: BuildingListComponent},
  {path: 'buildings/:id', component: BuildingComponent},
  {path: 'buildings/:id/floors', component: FloorListComponent},
  {path: 'buildings/:id/floors/:floorId', component: FloorComponent},
  {path: 'buildings/:id/floors/:floorId/rooms', component: RoomListComponent},
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
